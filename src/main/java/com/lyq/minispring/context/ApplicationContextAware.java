package com.lyq.minispring.context;

import com.lyq.minispring.beans.BeansException;
import com.lyq.minispring.beans.factory.Aware;

/**
 * 实现该接口，能感知所属ApplicationContext
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
