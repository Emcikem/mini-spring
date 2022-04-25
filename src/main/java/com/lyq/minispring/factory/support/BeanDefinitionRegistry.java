package com.lyq.minispring.factory.support;

import com.lyq.minispring.factory.config.BeanDefinition;

/**
 * BeanDefinitionRegistry注册表接口
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinitions(String beanName, BeanDefinition beanDefinition);
}
