package com.lyq.minispring.beans.factory.config;

import com.lyq.minispring.beans.factory.HierarchicalBeanFactory;


public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 摧毁单例bean
     */
    void destroySingletons();
}
