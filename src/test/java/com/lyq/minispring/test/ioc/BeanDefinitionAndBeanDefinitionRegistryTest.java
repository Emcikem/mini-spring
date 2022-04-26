package com.lyq.minispring.test.ioc;

import com.lyq.minispring.beans.factory.config.BeanDefinition;
import com.lyq.minispring.beans.factory.support.DefaultListableBeanFactory;
import com.lyq.minispring.test.ioc.service.HelloService;
import org.junit.Test;

/**
 * @author derekyi
 * @date 2020/11/24
 */
public class BeanDefinitionAndBeanDefinitionRegistryTest {

	@Test
	public void testBeanFactory() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
		beanFactory.registerBeanDefinition("helloService", beanDefinition);

		HelloService helloService = (HelloService) beanFactory.getBean("helloService");
		helloService.sayHello();
	}
}
