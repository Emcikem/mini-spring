package com.lyq.minispring.context.support;

import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.beans.factory.ConfigurableListableBeanFactory;
import com.lyq.minispring.beans.factory.config.BeanFactoryPostProcessor;
import com.lyq.minispring.beans.factory.config.BeanPostProcessor;
import com.lyq.minispring.context.ConfigurableApplicationContext;
import com.lyq.minispring.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * 抽象应用上下文
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader
        implements ConfigurableApplicationContext {


    @Override
    public void refresh() throws BeansException {
        // 创建BanFactory，并加载BeanDefinition
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 在bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // BeanPostProcessor需要提前与其他bean实例化之前注册
        registerBeanPostProcessors(beanFactory);

        beanFactory.preInstantiateSingletons();
    }

    /**
     * 在bean实例化之前，执行BeanFactoryPostProcessor
     * TODO: 检查是在xml中配置BeanFactoryPostProcessor吗？
     */
    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }


    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }


    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return null;
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return null;
    }

    public Object getBean(String name) throws BeansException {
        return null;
    }

    /**
     * 创建BeanFactory，并加载BeanDefinition
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 获取BeanFactory
     */
    public abstract ConfigurableListableBeanFactory getBeanFactory();

}
