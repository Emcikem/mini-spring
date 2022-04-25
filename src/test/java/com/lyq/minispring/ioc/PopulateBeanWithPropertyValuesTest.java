package com.lyq.minispring.ioc;

import com.lyq.minispring.ioc.bean.Car;
import com.lyq.minispring.ioc.bean.Person;
import com.lyq.minispring.bean.PropertyValue;
import com.lyq.minispring.bean.PropertyValues;
import com.lyq.minispring.bean.factory.config.BeanDefinition;
import com.lyq.minispring.bean.factory.config.BeanReference;
import com.lyq.minispring.bean.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PopulateBeanWithPropertyValuesTest {

    @Test
    public void PopulateBeanWithPropertyValuesTest() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册Car实例
        PropertyValues propertyValuesForCar = new PropertyValues();
        propertyValuesForCar.addPropertyValue(new PropertyValue("brand", "porsche"));
        BeanDefinition carBeanDefinition = new BeanDefinition(Car.class, propertyValuesForCar);
        beanFactory.registerBeanDefinitions("car", carBeanDefinition);

        // 注册Person实例
        PropertyValues propertyValuesForPerson = new PropertyValues();
        propertyValuesForPerson.addPropertyValue(new PropertyValue("name", "derek"));
        propertyValuesForPerson.addPropertyValue(new PropertyValue("age", 18));
        // Person实例依赖Car实例
        propertyValuesForPerson.addPropertyValue(new PropertyValue("car", new BeanReference("car")));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValuesForPerson);
        beanFactory.registerBeanDefinitions("person", beanDefinition);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        assertThat(person.getName()).isEqualTo("derek");
        assertThat(person.getAge()).isEqualTo(18);
        Car car = person.getCar();
        assertThat(car).isNotNull();
        assertThat(car.getBrand()).isEqualTo("porsche");
    }
}
