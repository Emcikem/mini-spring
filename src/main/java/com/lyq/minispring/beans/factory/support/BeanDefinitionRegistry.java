package com.lyq.minispring.beans.factory.support;

import com.lyq.minispring.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition注册表接口
 *
 */
public interface BeanDefinitionRegistry {

	/**
	 * 向注册表中注BeanDefinition
	 *
	 * @param beanName
	 * @param beanDefinition
	 */
	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

	/**
	 * 是否包含指定名称的BeanDefinition
	 *
	 * @param beanName
	 * @return
	 */
	boolean containsBeanDefinition(String beanName);

}
