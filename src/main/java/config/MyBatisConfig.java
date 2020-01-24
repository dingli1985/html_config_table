package config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {

    /**
     *
     * @return
     */
    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/hrms?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false");
        config.setUsername("root");
        config.setPassword("1qaz2wsx");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return config;
    }

    /**
     *
     * @param hikariConfig
     * @return
     */
    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource(HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public MybatisConfiguration mybatisConfiguration(){
        MybatisConfiguration mybatisConfiguration=new MybatisConfiguration();
        mybatisConfiguration.setLogImpl(Log4jImpl.class);
        return mybatisConfiguration;
    }

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource,MybatisConfiguration mybatisConfiguration){
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean=new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
        return sqlSessionFactoryBean;
    }
}
