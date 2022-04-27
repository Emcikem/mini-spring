package com.lyq.minispring.context.support;


import com.lyq.minispring.beans.BeansException;

/**
 * xml的文件的应用上下文
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private final String[] configLocations;


    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation});
    }


    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException{
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return this.configLocations;
    }
}
