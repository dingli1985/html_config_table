package config;

import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xhp.hrms.core.shrio.ShiroDbRealm;


@Configuration
public class ShiroConfig {

	/**
	 * 项目自定义的Realm
	 */
	@Bean
	public ShiroDbRealm shiroDbRealm() {
		return new ShiroDbRealm();
	}

	/**
	 * rememberMe管理器, cipherKey生成见{@code Base64Test.java}
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
		CookieRememberMeManager manager = new CookieRememberMeManager();
		manager.setCipherKey(Base64.decode("Z3VucwAAAAAAAAAAAAAAAA=="));
		manager.setCookie(rememberMeCookie);
		return manager;
	}

	/**
	 * 安全管理器
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(ShiroDbRealm shiroDbRealm ,CookieRememberMeManager rememberMeManager,
			DefaultWebSessionManager defaultWebSessionManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//securityManager.setRealm(this.shiroDbRealm());
		securityManager.setRealm(shiroDbRealm);
		securityManager.setRememberMeManager(rememberMeManager);
		securityManager.setSessionManager(defaultWebSessionManager);
		return securityManager;
	}

	/**
	 * session管理器
	 */
	@Bean
	public DefaultWebSessionManager defaultWebSessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
		cookie.setName("shiroCookie");
		cookie.setHttpOnly(true);
		sessionManager.setSessionIdCookie(cookie);
		return sessionManager;
	}

	/**
	 * 记住密码Cookie
	 */
	@Bean
	public SimpleCookie rememberMeCookie() {
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		simpleCookie.setHttpOnly(true);
		simpleCookie.setMaxAge(7 * 24 * 60 * 60);// 7天
		return simpleCookie;
	}

	/**
	 * 在方法中 注入 securityManager,进行代理控制
	 */
	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager) {
		MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
		bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
		bean.setArguments(new Object[] { securityManager });
		return bean;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
		return lifecycleBeanPostProcessor;
	}

	/**
	 * Shiro过滤器
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 默认的登陆访问url
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功之后跳转的URL
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 全局无权限跳转URL
		shiroFilterFactoryBean.setUnauthorizedUrl("/golobal/nopermission");

		/**
		 * 配置shiro拦截器链
		 *
		 * anon 不需要认证 authc 需要认证 user 验证通过或RememberMe登录的都可以
		 *
		 */
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("/login", "anon");
		hashMap.put("/login/do", "anon");
		hashMap.put("/reader/start", "anon");
		hashMap.put("/global/sessionError", "anon");
		hashMap.put("/bootstrap/css/bootstrap.min.css", "anon");
		hashMap.put("/jquery/jquery.min.js", "anon");

		hashMap.put("/**", "user");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(hashMap);

		return shiroFilterFactoryBean;
	}
}
