package com.lyq.minispring.beans.factory.config;

import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.beans.factory.ConfigurableListableBeanFactory;

/**
 * 允许自定义修改BeanDefinition的属性值
 * 加载完BeanDefinition后，bean实例化之前
 *
 *
 * 其重要的实现类有PropertyPlaceholderConfigurer和CustomEditorConfigurer，
 *
 * PropertyPlaceholderConfigurer的作用是用properties文件的配置值替换xml文件中的占位符
 * CustomEditorConfigurer的作用是实现类型转换
 */
public interface BeanFactoryPostProcessor {


    /**
     * 在所有BeanDefinition加载完后，在bean实例化之前
     * 提供修改BeanDefinition属性值的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
