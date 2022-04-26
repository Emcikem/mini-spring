package com.lyq.minispring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.beans.PropertyValue;
import com.lyq.minispring.beans.factory.config.AutowireCapableBeanFactory;
import com.lyq.minispring.beans.factory.config.BeanDefinition;
import com.lyq.minispring.beans.factory.config.BeanPostProcessor;
import com.lyq.minispring.beans.factory.config.BeanReference;

/**
 * 创建bean实例并且进行赋值，同时完成bean依赖bean
 *
 * 有个BeanPostProcessor，针对bean实例化之后并且赋值后，调用初始化方法之前和之后的操作
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
		implements AutowireCapableBeanFactory {

	private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

	@Override
	protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
		return doCreateBean(beanName, beanDefinition);
	}

	protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
		Object bean = null;
		try {
			bean = createBeanInstance(beanDefinition);
			// 为bean填充属性
			applyPropertyValues(beanName, bean, beanDefinition);
			// bean的初始化方法
			initializeBean(beanName, bean, beanDefinition);
		} catch (Exception e) {
			throw new BeansException("Instantiation of bean failed", e);
		}

		addSingleton(beanName, bean);
		return bean;
	}

	/**
	 * 执行BeanPostProcessor的两个方法和实例初始化方法
	 * @param beanName
	 * @param bean
	 * @param beanDefinition
	 */
	private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
		// 执行BeanPostProcessor的前置处理
		Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

		// TODO: 后面会在此处执行bean的初始化方法
		invokeInitMethods(beanName, wrappedBean, beanDefinition);

		// 执行BeanPostProcessor的后置处理
		wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);

		return wrappedBean;
	}


	/**
	 * 实例化bean
	 * 调用实例化策略实现实例化
	 */
	protected Object createBeanInstance(BeanDefinition beanDefinition) {
		return getInstantiationStrategy().instantiate(beanDefinition);
	}

	/**
	 * 为bean填充属性
	 * 以及bean依赖bean
	 */
	protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
		try {
			for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
				String name = propertyValue.getName();
				Object value = propertyValue.getValue();
				if (value instanceof BeanReference) {
					// beanA依赖beanB，先实例化beanB
					BeanReference beanReference = (BeanReference) value;
					value = getBean(beanReference.getBeanName());
				}

				//通过反射设置属性
				BeanUtil.setFieldValue(bean, name, value);
			}
		} catch (Exception ex) {
			throw new BeansException("Error setting property values for bean: " + beanName, ex);
		}
	}


	/**
	 * bean初始化之前
	 */
	@Override
	public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
			throws BeansException {
		Object result = existingBean;
		for (BeanPostProcessor processor : getBeanPostProcessors()) {
			Object current = processor.postProcessBeforeInitialization(result, beanName);
			if (current == null) {
				return result;
			}
			result = current;
		}
		return result;
	}


	/**
	 * bean初始化之后
	 */
	@Override
	public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
			throws BeansException {
		Object result = existingBean;
		for (BeanPostProcessor processor : getBeanPostProcessors()) {
			Object current = processor.postProcessAfterInitialization(result, beanName);
			if (current == null) {
				return result;
			}
			result = current;
		}
		return result;
	}


	/**
	 * bean的初始化方法
	 */
	private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {
		// TODO: 后面会实现
		System.out.println("执行bean[" + beanName + "]的初始化方法");
	}

	public InstantiationStrategy getInstantiationStrategy() {
		return instantiationStrategy;
	}

	public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
		this.instantiationStrategy = instantiationStrategy;
	}

}
