package com.lyq.minispring.beans.factory.support;

import com.lyq.minispring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例bean的缓存池
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	private final Map<String, Object> singletonObjects = new HashMap<>();

	@Override
	public Object getSingleton(String beanName) {
		return singletonObjects.get(beanName);
	}

	protected void addSingleton(String beanName, Object singletonObject) {
		singletonObjects.put(beanName, singletonObject);
	}
}
