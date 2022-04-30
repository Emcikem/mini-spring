package com.lyq.minispring.context;


/**
 * 事件发布者接口
 */
public interface ApplicationEventPublisher {


    /**
     * 发布事件
     */
    void publishEvent(ApplicationEvent event);
}
