package com.lyq.minispring.factory.config;

/**
 * 单例注册表
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
