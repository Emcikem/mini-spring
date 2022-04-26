package com.lyq.minispring.beans.factory.support;

import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.core.io.DefaultResourceLoader;
import com.lyq.minispring.core.io.ResourceLoader;

/**
 * bean读取
 * BeanDefinitionReader需要有获取资源的能力，且读取bean定义信息后需要往容器中注册BeanDefinition，
 * 因此BeanDefinitionReader的抽象实现类AbstractBeanDefinitionReader拥有ResourceLoader和BeanDefinitionRegistry两个属性
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

	private final BeanDefinitionRegistry registry;

	private ResourceLoader resourceLoader;

	protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this(registry, new DefaultResourceLoader());
	}

	public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
		this.registry = registry;
		this.resourceLoader = resourceLoader;
	}

	@Override
	public BeanDefinitionRegistry getRegistry() {
		return registry;
	}

	@Override
	public void loadBeanDefinitions(String[] locations) throws BeansException {
		for (String location : locations) {
			loadBeanDefinitions(location);
		}
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}
}
