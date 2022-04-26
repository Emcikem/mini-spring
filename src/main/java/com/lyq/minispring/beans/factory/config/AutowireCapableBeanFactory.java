package com.lyq.minispring.beans.factory.config;

import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.beans.factory.BeanFactory;

/**
 * 定义了两个接口，主要是实现BeanPostProcessors的两个方法
 */
public interface AutowireCapableBeanFactory extends BeanFactory {


    /**
     * 执行BeanPostProcessors的postProcessBeforeInitialization方法
     * @param existingBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
            throws BeansException;

    /**
     * 执行BeanPostProcessors的postProcessAfterInitialization方法
     * @param existingBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
            throws BeansException;
}
