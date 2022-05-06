package com.lyq.minispring.test.ioc;

import com.lyq.minispring.context.support.ClassPathXmlApplicationContext;
import com.lyq.minispring.test.service.HelloService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AwareInterfaceTest {

    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring1.xml");
        HelloService helloService = applicationContext.getBean("helloService", HelloService.class);
        assertThat(helloService.getApplicationContext()).isNotNull();
        assertThat(helloService.getBeanFactory()).isNotNull();
    }
}
