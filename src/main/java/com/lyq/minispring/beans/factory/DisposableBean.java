package com.lyq.minispring.beans.factory;

/**
 * bean的摧毁方法接口定义
 */
public interface DisposableBean {

    void destroy() throws Exception;
}
