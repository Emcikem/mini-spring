package com.lyq.minispring.context.event;

import com.lyq.minispring.context.ApplicationContext;

public class ContextClosedEvent extends ApplicationContextEvent{
    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
