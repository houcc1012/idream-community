package com.idream.commons.lib.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 手动获取javabean
 * @author hejiang
 */
@Component
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}

	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 根据Bean名称获取实例
	 * @param beanName Bean注册名称
	 * @return bean实例
	 * @throws BeansException
	 */
	public static <T> T getBean(String beanName, Class<T> clazz) throws BeansException {
		return (T) applicationContext.getBean(beanName);
	}

}
