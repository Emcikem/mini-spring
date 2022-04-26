package com.lyq.minispring.beans.factory;

import com.lyq.minispring.beans.factory.config.AutowireCapableBeanFactory;
import com.lyq.minispring.beans.factory.config.BeanDefinition;
import com.lyq.minispring.beans.factory.config.BeanPostProcessor;
import com.lyq.minispring.beans.factory.config.ConfigurableBeanFactory;
import com.lyq.minispring.beans.BeansException;

/**
 *
 */
public interface ConfigurableListableBeanFactory extends
		ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

	/**
	 * 根据名称查找BeanDefinition
	 *
	 * @param beanName
	 * @return
	 * @throws BeansException 如果找不到BeanDefinition
	 */
	BeanDefinition getBeanDefinition(String beanName) throws BeansException;


	/**
	 * 提前实例化所有单例实例，应用于ApplicationContext
	 */
	void preInstantiateSingletons() throws BeansException;

	/**
	 * 应用于ApplicationContext
	 */
	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
