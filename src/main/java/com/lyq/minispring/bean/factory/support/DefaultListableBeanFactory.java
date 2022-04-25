package com.lyq.minispring.bean.factory.support;

import com.lyq.minispring.bean.BeansException;
import com.lyq.minispring.bean.factory.ConfigurableListableBeanFactory;
import com.lyq.minispring.bean.factory.config.BeanDefinition;
import com.lyq.minispring.bean.factory.config.ConfigurableBeanFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 通过这个实现类去getBean
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
        implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    /**
     * getBeanByType
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> Map<String, T> getBeansOfType(Class<?> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class<?> beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        Set<String> beanNames = beanDefinitionMap.keySet();
        return beanNames.toArray(new String[0]);
    }

    @Override
    public void registerBeanDefinitions(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
