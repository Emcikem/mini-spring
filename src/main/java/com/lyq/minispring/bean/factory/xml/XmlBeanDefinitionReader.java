package com.lyq.minispring.bean.factory.xml;

import com.lyq.minispring.bean.factory.support.AbstractBeanDefinitionReader;
import com.lyq.minispring.bean.factory.support.BeanDefinitionRegistry;
import com.lyq.minispring.core.io.Resource;
import com.lyq.minispring.core.io.ResourceLoader;

/**
 * 读取配置在xml文件中的bean定义信息
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";

    protected XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }


    @Override
    public void loadBeanDefinitions(Resource resource) {

    }

    @Override
    public void loadBeanDefinitions(String location) {

    }
}
