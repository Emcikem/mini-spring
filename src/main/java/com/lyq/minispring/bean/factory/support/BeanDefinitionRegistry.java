package com.lyq.minispring.bean.factory.support;

import com.lyq.minispring.bean.factory.config.BeanDefinition;

/**
 * BeanDefinitionRegistry注册表接口
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册BeanDefinition
     */
    void registerBeanDefinitions(String beanName, BeanDefinition beanDefinition);

}
