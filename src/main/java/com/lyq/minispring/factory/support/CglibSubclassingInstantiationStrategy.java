package com.lyq.minispring.factory.support;

import com.lyq.minispring.factory.config.BeanDefinition;

public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{

    /**
     * 使用CGLIB动态生成子类
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) {
        throw new UnsupportedOperationException("CGLIB instantiation strategy is not supported");
    }
}
