package com.lyq.minispring.beans.factory.support;

import com.lyq.minispring.beans.factory.config.BeanDefinition;
import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.beans.factory.config.ConfigurableBeanFactory;

/**
 *
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
		implements ConfigurableBeanFactory {

	@Override
	public Object getBean(String name) throws BeansException {
		Object bean = getSingleton(name);
		if (bean != null) {
			return bean;
		}

		BeanDefinition beanDefinition = getBeanDefinition(name);
		return createBean(name, beanDefinition);
	}

	@Override
	public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return ((T) getBean(name));
	}

	protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

	protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}