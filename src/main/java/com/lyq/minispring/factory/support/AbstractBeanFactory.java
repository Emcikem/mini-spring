package com.lyq.minispring.factory.support;

import com.lyq.minispring.factory.config.BeanDefinition;
import com.lyq.minispring.factory.factory.BeanFactory;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * 先从bean缓冲池中查找，如果没有，那么就去BeanDefinitions查找，同时进行注册bean
     */
    @Override
    public Object getBean(String name) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);

    protected abstract BeanDefinition getBeanDefinition(String beanName);
}
