package com.lyq.minispring.test.common;

import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.beans.PropertyValue;
import com.lyq.minispring.beans.PropertyValues;
import com.lyq.minispring.beans.factory.ConfigurableListableBeanFactory;
import com.lyq.minispring.beans.factory.config.BeanDefinition;
import com.lyq.minispring.beans.factory.config.BeanFactoryPostProcessor;


/**
 * 加载了BeanDefinition，那么BeanDefinitions里就有这个对象，且实例化之前
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition personBeanDefinition = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = personBeanDefinition.getPropertyValues();

        // 修改 person的name属性
        propertyValues.addPropertyValue(new PropertyValue("name", "chenqiang"));
    }
}
