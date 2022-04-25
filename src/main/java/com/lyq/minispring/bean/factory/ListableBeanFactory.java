package com.lyq.minispring.bean.factory;

import com.lyq.minispring.bean.BeansException;

import java.util.Map;

/**
 * getBeanByType的接口定义
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 返回指定类型的所有实例
     */
    <T> Map<String, T> getBeansOfType(Class<?> type) throws BeansException;

    /**
     * 返回定义的所有bean的名称
     */
    String[] getBeanDefinitionNames();
}
