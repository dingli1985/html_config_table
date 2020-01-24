import java.util.EnumSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import com.xhp.hrms.config.MyBatisConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import com.xhp.hrms.config.ShiroConfig;
import com.xhp.hrms.config.ThymeleafConifg;
import com.xhp.hrms.config.WebAppConfig;

public class Initializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebAppConfig.class);
        ctx.register(ShiroConfig.class);
        ctx.register(ThymeleafConifg.class);
        ctx.register(MyBatisConfig.class);
        ctx.setServletContext(servletContext);

        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        servletContext.addListener("org.springframework.web.context.request.RequestContextListener");
        /**
         *添加log4j配置
         */
//		servletContext.setInitParameter("log4jConfigLocation","WEB-INF/classes/log4j.properties");
//		servletContext.setInitParameter("log4jRefreshInterval","20000");
//		servletContext.addListener("org.springframework.web.util.Log4jConfigListener");

        /**
         * 添加ShiroFilter
         */
        FilterRegistration.Dynamic shiroFilter = servletContext.addFilter("shiroFilter", DelegatingFilterProxy.class);
        shiroFilter.setInitParameter("targetFilterLifecycle", "true");
        shiroFilter.addMappingForUrlPatterns(
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/*");
        /**
         *
         *
         * 添加Spring编码过滤器
         */
        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", DelegatingFilterProxy.class);
        Map<String, String> encodeFilterParameter = new HashMap<String, String>();
        encodeFilterParameter.put("encoding", "UTF-8");
        encodeFilterParameter.put("forceEncoding", "true");
        characterEncodingFilter.setInitParameters(encodeFilterParameter);
        characterEncodingFilter.addMappingForUrlPatterns(
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/*");


    }

}