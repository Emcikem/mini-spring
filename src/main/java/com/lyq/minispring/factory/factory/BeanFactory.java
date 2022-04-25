package com.lyq.minispring.factory.factory;

/**
 * bean创建的工厂模式
 */
public interface BeanFactory {

    Object getBean(String name);
}
