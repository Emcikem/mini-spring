package com.lyq.minispring.factory.support;

import com.lyq.minispring.factory.BeansException;
import com.lyq.minispring.factory.config.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        return doCreateBean(beanName, beanDefinition);
    }

    /**
     * 创建实例并且放入bean缓存池中
     */
    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Object bean;
        try {
            bean = beanClass.newInstance();
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }
}
