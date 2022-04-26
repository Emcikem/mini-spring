package com.lyq.minispring.context;

import com.lyq.minispring.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     */
    void refresh() throws BeansException;
}
