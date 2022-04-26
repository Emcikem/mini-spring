package com.lyq.minispring.test.ioc;

import com.lyq.minispring.beans.factory.support.DefaultListableBeanFactory;
import com.lyq.minispring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lyq.minispring.test.ioc.bean.Car;
import com.lyq.minispring.test.ioc.bean.Person;
import com.lyq.minispring.test.ioc.common.CustomBeanFactoryPostProcessor;
import com.lyq.minispring.test.ioc.common.CustomerBeanPostProcessor;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class BeanFactoryPostProcessorAndBeanPostProcessorTest {


    @Test
    public void testBeanFactoryPostProcessor() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");


        // 在所有BeanDefinition加载完后，但在bean实例化之前，修改BeanDefinition的属性值
        CustomBeanFactoryPostProcessor beanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        // name属性在CustomBeanFactoryPostProcess中被修改为chenqiang
        assertThat(person.getName()).isEqualTo("chenqiang");
    }


    @Test
    public void testBeanPostProcessor() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 添加bean实例化后的处理器
        CustomerBeanPostProcessor customerBeanPostProcessor = new CustomerBeanPostProcessor();
        beanFactory.addBeanPostProcessor(customerBeanPostProcessor);

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
        // brand属性在CustomerBeanPostProcessor中被修改为lamborghini
        assertThat(car.getBrand()).isEqualTo("lamborghini");
    }
}
