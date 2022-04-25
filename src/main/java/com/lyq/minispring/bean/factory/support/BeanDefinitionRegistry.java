package com.lyq.minispring.bean.factory.support;

import com.lyq.minispring.bean.factory.config.BeanDefinition;

/**
 * BeanDefinitionRegistry注册表接口
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinitions(String beanName, BeanDefinition beanDefinition);
}
