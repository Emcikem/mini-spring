package com.lyq.minispring.beans.factory;

/**
 * bean的初始化方法接口定义
 */
public interface InitializingBean {

    void afterPropertiesSer() throws Exception;
}
