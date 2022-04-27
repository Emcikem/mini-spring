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

        //添加ApplicationContextAwareProcessor，让继承自ApplicationContextAware的bean能感知bean
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));


        // 在bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // BeanPostProcessor需要提前与其他bean实例化之前注册
        registerBeanPostProcessors(beanFactory);

        beanFactory.preInstantiateSingletons();
    }

    /**
     * 在bean实例化之前，执行BeanFactoryPostProcessor
     * 程序通过实现BeanFactoryPostProcessor接口，然后配置在xml中
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
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    /**
     * 创建BeanFactory，并加载BeanDefinition
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 获取BeanFactory
     */
    public abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * bean的摧毁
     */
    public void close(){
        doClose();
    }

    /**
     * 为了确保销毁方法在虚拟机关闭之前执行，向虚拟机中注册一个钩子方法
     * 非web应用需要手动调用该方法
     */
    public void registerShutdownHook() {
        Thread shutdownHook = new Thread(this::doClose);
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    protected void doClose() {
        destroyBeans();
    }

    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }
}
