package com.lyq.minispring.beans.factory;

import com.lyq.minispring.beans.BeansException;

/**
 * 实现该接口，能感知所属的BeanFactory
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
