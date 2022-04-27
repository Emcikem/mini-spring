package com.lyq.minispring.context.support;

import com.lyq.minispring.beans.factory.support.DefaultListableBeanFactory;
import com.lyq.minispring.beans.factory.xml.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 获取加载地址
     */
    protected abstract String[] getConfigLocations();
}
