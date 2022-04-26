package com.lyq.minispring.beans.factory.support;

import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.beans.factory.config.BeanDefinition;

/**
 * CGLIB动态生成
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

	/**
	 * 使用CGLIB动态生成子类
	 *
	 * @param beanDefinition
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
		throw new UnsupportedOperationException("CGLIB instantiation strategy is not supported");
	}
}
