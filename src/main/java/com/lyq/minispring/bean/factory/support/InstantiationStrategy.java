package com.lyq.minispring.bean.factory.support;

import com.lyq.minispring.bean.factory.config.BeanDefinition;

/**
 * Bean的实例化策略
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition);
}
