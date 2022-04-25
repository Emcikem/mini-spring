package com.lyq.minispring.bean.factory.support;

import com.lyq.minispring.core.io.Resource;
import com.lyq.minispring.core.io.ResourceLoader;

/**
 * 读取bean定义信息即BeanDefinition的接口定义
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource);

    void loadBeanDefinitions(String location);

    void loadBeanDefinitions(String[] locations);
}
