package com.lyq.minispring.beans.factory;

import com.lyq.minispring.beans.factory.config.AutowireCapableBeanFactory;
import com.lyq.minispring.beans.factory.config.BeanDefinition;
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

}
