package config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan("com.xhp.hrms.web")
@EnableWebMvc
@MapperScan("com.xhp.hrms.mapper")
@PropertySource("classpath:application.properties") // 读取application.properties
@EnableTransactionManagement
public class WebAppConfig {

	@Bean
	public ApplicationContextHelper applicationContextHelper() {
		return new ApplicationContextHelper();
	}

    @Bean
	public CharacterEncodingFilter characterEncodingFilter(){
		CharacterEncodingFilter characterEncodingFilter=new CharacterEncodingFilter();
		return characterEncodingFilter;
	}
}
