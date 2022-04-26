package com.lyq.minispring.beans.factory.support;

import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.core.io.Resource;
import com.lyq.minispring.core.io.ResourceLoader;

/**
 * 读取bean定义信息即BeanDefinition的接口
 *
 */
public interface BeanDefinitionReader {

	BeanDefinitionRegistry getRegistry();

	ResourceLoader getResourceLoader();

	void loadBeanDefinitions(Resource resource) throws BeansException;

	void loadBeanDefinitions(String location) throws BeansException;

	void loadBeanDefinitions(String[] locations) throws BeansException;
}
