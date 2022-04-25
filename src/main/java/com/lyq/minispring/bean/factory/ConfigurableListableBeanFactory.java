package com.lyq.minispring.bean.factory;

import com.lyq.minispring.bean.BeansException;
import com.lyq.minispring.bean.factory.config.AutowireCapableBeanFactory;
import com.lyq.minispring.bean.factory.config.BeanDefinition;
import com.lyq.minispring.bean.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends
        ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 根据名称查找BeanDefinition
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
