package com.lyq.minispring.context.event;

import com.lyq.minispring.context.ApplicationContext;
import com.lyq.minispring.context.ApplicationEvent;

public abstract class ApplicationContextEvent extends ApplicationEvent {


    public ApplicationContextEvent(ApplicationContext source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
