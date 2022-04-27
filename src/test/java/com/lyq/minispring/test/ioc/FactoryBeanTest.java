package com.lyq.minispring.test.ioc;

import com.lyq.minispring.context.support.ClassPathXmlApplicationContext;
import com.lyq.minispring.test.ioc.bean.Car;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class FactoryBeanTest {

    @Test
    public void testFactoryBean() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");

        Car car = applicationContext.getBean("car", Car.class);
        assertThat(car.getBrand()).isEqualTo("porsche");
    }
}
