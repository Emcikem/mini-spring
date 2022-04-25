package com.lyq.minispring;

import com.lyq.minispring.factory.factory.config.BeanDefinition;
import com.lyq.minispring.factory.factory.support.DefaultListableBeanFactory;
import com.lyq.minispring.service.HelloService;
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

