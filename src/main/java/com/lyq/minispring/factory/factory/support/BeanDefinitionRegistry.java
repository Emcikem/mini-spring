package com.lyq.minispring.factory.factory.support;

import com.lyq.minispring.factory.factory.config.BeanDefinition;

/**
 * BeanDefinitionRegistry注册表接口
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinitions(String beanName, BeanDefinition beanDefinition);
}
