package com.lyq.minispring;

import org.junit.jupiter.api.Test;

public class SimpleBeanContainerTest {

    @Test
    public void testGetBean() {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBean("bean1", new String("1111"));
        String str = (String) beanFactory.getBean("bean1");
        assert str.equals("1111");
    }
}
