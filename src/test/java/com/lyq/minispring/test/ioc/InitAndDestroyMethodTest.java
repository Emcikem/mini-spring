package com.lyq.minispring.test.ioc;

import com.lyq.minispring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * 定义bean的初始化和摧毁方法有3种
 * 1. xml种定义init-method和destroy-method
 * 2. 继承自initializingBean和DisposableBean
 * 3. 在方法上加注解PostConstruct和PreDestroy
 */
public class InitAndDestroyMethodTest {


    @Test
    public void testInitAndDestroyMethod() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
        applicationContext.registerShutdownHook();  //或者手动关闭 applicationContext.close();
    }
}
