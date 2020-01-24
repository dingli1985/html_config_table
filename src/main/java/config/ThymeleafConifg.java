package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class ThymeleafConifg {
	/**
	 * ThymeleafViewResolver配置
	 * <bean class="org.thymeleaf.spring4.view.ThymeleafViewResolver">
	 * <property name="templateEngine" ref="templateEngine" /> </bean>
	 * 
	 * @return
	 */
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine(templateEngine);
		thymeleafViewResolver.setCharacterEncoding("utf-8");
		thymeleafViewResolver.setContentType("text/html");
		return thymeleafViewResolver;
	}

	@Bean(name = "templateResolver")
	public ServletContextTemplateResolver servletContextTemplateResolver() {
		ServletContextTemplateResolver servletContextTemplateResolver = new ServletContextTemplateResolver();
		servletContextTemplateResolver.setTemplateMode("HTML5");
		servletContextTemplateResolver.setPrefix("/WEB-INF/templates/");
		servletContextTemplateResolver.setSuffix(".html");
		servletContextTemplateResolver.setCacheable(false);
		return servletContextTemplateResolver;
	}

	@Bean(name = "templateEngine")
	public SpringTemplateEngine springTemplateEngine(ServletContextTemplateResolver templateResolver) {
		SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
		springTemplateEngine.setTemplateResolver(templateResolver);
		return springTemplateEngine;
	}

	/*
	 * Message externalization/internationalization
	 */
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("Messages");
		// messageSource.setBasenames(new String[]{"",""});
		return messageSource;
	}

	@Bean
	public FormattingConversionService conversionService() {

		// Use the DefaultFormattingConversionService but do not register
		// defaults
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);
		// Ensure @NumberFormat is still supported
		conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());

		// Register date conversion with a specific global format
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("yyyyMMdd"));
		registrar.registerFormatters(conversionService);

		return conversionService;
	}

}
