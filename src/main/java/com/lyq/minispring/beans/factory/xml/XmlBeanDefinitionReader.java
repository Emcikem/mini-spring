package com.lyq.minispring.beans.factory.xml;


import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.lyq.minispring.beans.PropertyValue;
import com.lyq.minispring.beans.factory.config.BeanDefinition;
import com.lyq.minispring.beans.factory.config.BeanReference;
import com.lyq.minispring.beans.factory.support.AbstractBeanDefinitionReader;
import com.lyq.minispring.beans.factory.support.BeanDefinitionRegistry;
import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.core.io.Resource;
import com.lyq.minispring.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

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

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }


    /**
     * 用资源加载器加载出资源，然后根据resource的inputstream去loadBean
     *
     * @param location
     * @throws BeansException
     */
    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException ex) {
            throw new BeansException("IOException parsing XML document from " + resource, ex);
        }
    }


    /**
     * 根据InputStream遍历所有的bean，
     * 先检查class，
     * 然后遍历bean的所有Attribute
     * 检查Attribute的类型是不是bean，即ref，然后创建BeanReference
     * 最后注册BeanDefinition
     */
    private void doLoadBeanDefinitions(InputStream inputStream) {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        // 遍历所有的bean
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i) instanceof Element
                    && BEAN_ELEMENT.equals(childNodes.item(i).getNodeName())) {
                // 解析Bean标签
                Element bean = (Element) childNodes.item(i);
                String id = bean.getAttribute(ID_ATTRIBUTE);
                String name = bean.getAttribute(NAME_ATTRIBUTE);
                String className = bean.getAttribute(CLASS_ATTRIBUTE);

                Class<?> clazz;
                try {
                    clazz = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    throw new BeansException("Cannot find class [" + className + "]");
                }
                // id优先于name
                String beanName = StrUtil.isNotEmpty(id) ? id : name;
                if (StrUtil.isEmpty(beanName)) {
                    // 如果id和name都为空，将类名的第一个字母转为小写后作为bean的名称
                    beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                }

                BeanDefinition beanDefinition = new BeanDefinition(clazz);
                // 遍历bean的所有属性
                for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                    if (bean.getChildNodes().item(j) instanceof Element
                            && PROPERTY_ELEMENT.equals(bean.getNodeName())) {
                        // 解析property标签
                        Element property = (Element) bean.getChildNodes().item(j);
                        String nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
                        String valueAttribute = property.getAttribute(VALUE_ATTRIBUTE);
                        String refAttribute = property.getAttribute(REF_ATTRIBUTE);

                        if (StrUtil.isEmpty(nameAttribute)) {
                            throw new BeansException("The name attribute cannot be null or empty");
                        }

                        Object value = valueAttribute;
                        if (StrUtil.isNotEmpty(refAttribute)) {
                            value = new BeanReference(refAttribute);
                        }
                        PropertyValue propertyValue = new PropertyValue(nameAttribute, value);
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
                if (getRegistry().containsBeanDefinition(beanName)) {
                    // beanName不能重名
                    throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
                }
                // 注册BeanDefinition
                getRegistry().registerBeanDefinition(beanName, beanDefinition);
            }
        }
    }
}
