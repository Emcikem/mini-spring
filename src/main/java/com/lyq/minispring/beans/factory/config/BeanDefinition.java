package com.lyq.minispring.beans.factory.config;

import com.lyq.minispring.beans.PropertyValues;

/**
 * BeanDefinition实例保存bean的信息，包括class类型、方法构造参数、bean属性、bean的scope等，此处简化只包含class类型和bean属性
 */
public class BeanDefinition {

	private Class<?> beanClass;

	private PropertyValues propertyValues;

	/**
	 * bean自定义初始化方法
	 */
	private String initMethodName;

	/**
	 * bean自定义摧毁方法
	 */
	private String destroyMethodName;

	public BeanDefinition(Class<?> beanClass) {
		this(beanClass, null);
	}

	public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
		this.beanClass = beanClass;
		this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
	}

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}

	public String getInitMethodName() {
		return initMethodName;
	}

	public void setInitMethodName(String initMethodName) {
		this.initMethodName = initMethodName;
	}

	public String getDestroyMethodName() {
		return destroyMethodName;
	}

	public void setDestroyMethodName(String destroyMethodName) {
		this.destroyMethodName = destroyMethodName;
	}
}
