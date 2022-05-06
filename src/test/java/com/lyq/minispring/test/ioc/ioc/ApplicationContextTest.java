package com.lyq.minispring.test.ioc.ioc;

import com.lyq.minispring.context.support.ClassPathXmlApplicationContext;
import com.lyq.minispring.test.ioc.bean.Car;
import com.lyq.minispring.test.ioc.bean.Person;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ApplicationContextTest {

    @Test
    public void testApplicationContext() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);

        //name属性在CustomBeanFactoryPostProcessor中被修改为chenqiang
        assertThat(person.getName()).isEqualTo("chenqiang");

        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car);

        //brand属性在CustomerBeanPostProcessor中被修改为lamborghini
        assertThat(car.getBrand()).isEqualTo("lamborghini");
    }
}
