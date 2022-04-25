package com.lyq.minispring.ioc;

import com.lyq.minispring.bean.factory.config.BeanDefinition;
import com.lyq.minispring.bean.factory.support.DefaultListableBeanFactory;
import com.lyq.minispring.ioc.service.HelloService;
import org.junit.Test;

public class BeanDefinitionAndBeanDefinitionRegistryTest {

    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        beanFactory.registerBeanDefinitions("helloService", beanDefinition);

        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();

    }
}

