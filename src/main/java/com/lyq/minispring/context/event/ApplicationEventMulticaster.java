package com.lyq.minispring.context.event;

import com.lyq.minispring.context.ApplicationEvent;
import com.lyq.minispring.context.ApplicationListener;

/**
 * 注册监听器和发布事件的抽象接口
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
