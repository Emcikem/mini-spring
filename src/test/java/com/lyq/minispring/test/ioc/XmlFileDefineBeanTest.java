package com.lyq.minispring.test.ioc;

import com.lyq.minispring.beans.factory.support.DefaultListableBeanFactory;
import com.lyq.minispring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lyq.minispring.test.bean.Person;
import org.junit.Test;
import com.lyq.minispring.test.bean.Car;

import static org.assertj.core.api.Assertions.assertThat;


public class XmlFileDefineBeanTest {

	@Test
	public void testXmlFile() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

		Person person = (Person) beanFactory.getBean("person");
		System.out.println(person);
		assertThat(person.getName()).isEqualTo("derek");
		assertThat(person.getCar().getBrand()).isEqualTo("porsche");

		Car car = (Car) beanFactory.getBean("car");
		System.out.println(car);
		assertThat(car.getBrand()).isEqualTo("porsche");
	}
}
