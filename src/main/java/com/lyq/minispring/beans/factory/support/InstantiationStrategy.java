package com.lyq.minispring.beans.factory.support;

import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.beans.factory.config.BeanDefinition;

/**
 * Bean的实例化策略
 *
 */
public interface InstantiationStrategy {

	Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
