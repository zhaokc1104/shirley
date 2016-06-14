package cn.com.mv.tds.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware{
	
	private  static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		if (applicationContext == null) {
			applicationContext = context;
			System.out.println("applicationContext init succss!");
		}
		
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
}
