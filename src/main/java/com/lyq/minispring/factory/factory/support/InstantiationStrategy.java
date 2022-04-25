package com.lyq.minispring.factory.factory.support;

import com.lyq.minispring.factory.factory.config.BeanDefinition;

/**
 * Bean的实例化策略
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition);
}
