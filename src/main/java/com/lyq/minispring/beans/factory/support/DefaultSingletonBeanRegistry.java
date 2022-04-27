package com.lyq.minispring.beans.factory.support;

import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.beans.factory.DisposableBean;
import com.lyq.minispring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 单例bean的缓存池
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	private final Map<String, Object> singletonObjects = new HashMap<>();

	private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

	@Override
	public Object getSingleton(String beanName) {
		return singletonObjects.get(beanName);
	}

	protected void addSingleton(String beanName, Object singletonObject) {
		singletonObjects.put(beanName, singletonObject);
	}

	public void registerDisposableBean(String beanName, DisposableBean bean) {
		disposableBeans.put(beanName, bean);
	}

	/**
	 * 遍历disposableBeans，执行所有的DisposableBean的destroy方法
	 */
	public void destroySingletons() {
		for (Iterator<Map.Entry<String, DisposableBean>> it = disposableBeans.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<String, DisposableBean> bean = it.next();
			it.remove();
			try {
				bean.getValue().destroy();
			} catch (Exception e) {
				throw new BeansException("Destroy method on bean with name '" + bean.getKey() + "' threw an exception", e);
			}
		}
	}
}
