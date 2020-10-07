package com.newbornhsir.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;

public class BeanProcessor implements BeanPostProcessor, Ordered {

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		// 处理器的执行顺序， 0优先级最高
		return 0;
	}
	
	@Override
	@Nullable
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("before init: " + beanName);
		return bean;
	}
	
	@Override
	@Nullable
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("after init: " + beanName);
		return bean;
	}

}
