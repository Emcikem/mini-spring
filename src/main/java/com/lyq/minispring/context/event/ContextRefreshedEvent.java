package com.lyq.minispring.context.event;

import com.lyq.minispring.context.ApplicationContext;

public class ContextRefreshedEvent extends ApplicationContextEvent{

    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }
}
