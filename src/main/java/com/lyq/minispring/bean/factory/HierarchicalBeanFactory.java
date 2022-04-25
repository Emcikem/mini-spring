package com.lyq.minispring.bean.factory;

/**
 * 分层工厂,beanFactory的二级工厂
 * Hierarchical:有等级限制的
 */
public interface HierarchicalBeanFactory extends BeanFactory{

    /**
     * 是否包含指定名称的BeanDefinition
     */
    boolean containsBeanDefinition(String beanName);

}
