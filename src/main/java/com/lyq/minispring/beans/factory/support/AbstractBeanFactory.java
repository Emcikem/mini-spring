package com.lyq.minispring.beans.factory.support;

import com.lyq.minispring.beans.factory.FactoryBean;
import com.lyq.minispring.beans.factory.config.BeanDefinition;
import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.beans.factory.config.BeanPostProcessor;
import com.lyq.minispring.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
		implements ConfigurableBeanFactory {

	private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

	private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

	@Override
	public Object getBean(String name) throws BeansException {
		Object sharedInstance = getSingleton(name);
		if (sharedInstance != null) {
			// 如果是FactoryBean，从FactoryBean.getObject中创建bean
			return getObjectForBeanInstance(sharedInstance, name);
		}

		BeanDefinition beanDefinition = getBeanDefinition(name);
		Object bean = createBean(name, beanDefinition);
		return getObjectForBeanInstance(bean, name);
	}

	/**
	 * factoryBean,从FactoryBean.getObject中创建bean
	 */
	private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
		Object object = beanInstance;
		if (beanInstance instanceof FactoryBean) {
			FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
			try {
				if (factoryBean.isSingleton()) {
					// singleton作用域bean，从缓存中读取
					object = this.factoryBeanObjectCache.get(beanName);
					if (object == null) {
						object = factoryBean.getObject();
						this.factoryBeanObjectCache.put(beanName, object);
					}
				} else {
					// prototype作用域，新创建bean
					object = factoryBean.getObject();
				}
			} catch (Exception ex) {
				throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", ex);
			}
		}
		return object;
	}

	@Override
	public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return ((T) getBean(name));
	}

	protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

	protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;


	@Override
	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
		// 有则覆盖 TODO:什么鬼
		this.beanPostProcessors.remove(beanPostProcessor);
		this.beanPostProcessors.add(beanPostProcessor);
	}

	public List<BeanPostProcessor> getBeanPostProcessors() {
		return beanPostProcessors;
	}
}
