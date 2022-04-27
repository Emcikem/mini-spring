package com.lyq.minispring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.beans.PropertyValue;
import com.lyq.minispring.beans.factory.DisposableBean;
import com.lyq.minispring.beans.factory.InitializingBean;
import com.lyq.minispring.beans.factory.config.AutowireCapableBeanFactory;
import com.lyq.minispring.beans.factory.config.BeanDefinition;
import com.lyq.minispring.beans.factory.config.BeanPostProcessor;
import com.lyq.minispring.beans.factory.config.BeanReference;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 创建bean实例并且进行赋值，同时完成bean依赖bean，bean的摧毁方法注册
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

		// 注册有摧毁方法的bean
		registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

		addSingleton(beanName, bean);
		return bean;
	}

	private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
		if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
			registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
		}
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

		try {
			invokeInitMethods(beanName, wrappedBean, beanDefinition);
		} catch (Throwable e) {
			throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
		}

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
	private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Throwable {
		if (bean instanceof InitializingBean) {
			((InitializingBean) bean).afterPropertiesSer();
		}

		// 获取初始化方法，并通过反射执行
		String initMethodName = beanDefinition.getInitMethodName();
		if (StrUtil.isNotEmpty(initMethodName)) {
			Method initMethod = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(), initMethodName);
			if (initMethod == null) {
				throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
			}
			initMethod.invoke(bean);
		}
	}

	public InstantiationStrategy getInstantiationStrategy() {
		return instantiationStrategy;
	}

	public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
		this.instantiationStrategy = instantiationStrategy;
	}

}
