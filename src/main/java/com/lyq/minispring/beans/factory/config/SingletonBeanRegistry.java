package com.lyq.minispring.beans.factory.config;

/**
 * 单例注册表接口定义
 */
public interface SingletonBeanRegistry {

	Object getSingleton(String beanName);
}
