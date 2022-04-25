package com.lyq.minispring.factory.factory.support;

import com.lyq.minispring.factory.BeansException;
import com.lyq.minispring.factory.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public class SimpleInstantiationStrategy implements InstantiationStrategy{


    /**
     * 简单的Bean实例化策略，根据bean的无惨构造函数实例化对象
     * @param beanDefinition
     * @return
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        try {
            Constructor<?> constructor = beanClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
    }
}
