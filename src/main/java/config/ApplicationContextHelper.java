package config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获得指定上下文和Root上下文环境
 * <p>
 * 通过在*.xml文件中配置
 * 
 * <pre>
 * &lt;bean id="applicationContextHelper" class="net.etongbao.common.ApplicationContextHelper"&gt;&lt;/bean&gt;
 * </pre>
 * 
 * 配置完成后ApplicationContextHelper会获得所在xml文件Context
 */
public class ApplicationContextHelper implements ApplicationContextAware {

	private static ApplicationContext ctx = null;

	/**
	 * 获得Bean对象
	 * 
	 * @param clazz
	 *            Bean类型
	 * @return Bean对象
	 */
	public static <T> T getBean(Class<T> clazz) {
		return ctx.getBean(clazz);
	}

	/**
	 * 获得Bean对象
	 * 
	 * @param className
	 * @return
	 */
	public static Object getBeanObj(String className) {
		return ctx.getBean(className);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String className) {
		return (T) ctx.getBean(className);
	}

	/**
	 * 获得应用所在上下文环境
	 * 
	 * @return
	 */
	public static ApplicationContext getContext() {
		return ctx;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext args0) throws BeansException {
		ctx = args0;
	}

}