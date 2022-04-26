package com.lyq.minispring.beans.factory.support;

import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * bean生成策略
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

	/**
	 * 简单的bean实例化策略，根据bean的无参构造函数实例化对象
	 *
	 * @param beanDefinition
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
		Class<?> beanClass = beanDefinition.getBeanClass();
		try {
			Constructor<?> constructor = beanClass.getDeclaredConstructor();
			return constructor.newInstance();
		} catch (Exception e) {
			throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
		}
	}
}
