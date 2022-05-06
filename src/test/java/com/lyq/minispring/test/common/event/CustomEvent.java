package com.lyq.minispring.test.common.event;

import com.lyq.minispring.context.ApplicationContext;
import com.lyq.minispring.context.event.ApplicationContextEvent;

public class CustomEvent extends ApplicationContextEvent {
    public CustomEvent(ApplicationContext source) {
        super(source);
    }
}
