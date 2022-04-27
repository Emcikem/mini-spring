package com.lyq.minispring.context.support;

import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;


    /**
     * 创建beanFactory并加载BeanDefinition
     */
    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    /**
     * 创建bean工厂
     */
    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    /**
     * 加载BeanDefinition
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);


    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
