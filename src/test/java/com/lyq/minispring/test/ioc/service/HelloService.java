package com.lyq.minispring.test.ioc.service;


import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.beans.factory.BeanFactory;
import com.lyq.minispring.beans.factory.BeanFactoryAware;
import com.lyq.minispring.context.ApplicationContext;
import com.lyq.minispring.context.ApplicationContextAware;

public class HelloService implements ApplicationContextAware, BeanFactoryAware {

	public String sayHello() {
		System.out.println("hello");
		return "hello";
	}

	private ApplicationContext applicationContext;

	private BeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}
}
