package com.lyq.minispring.test.ioc.common.event;

import com.lyq.minispring.context.ApplicationListener;
import com.lyq.minispring.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {

    }
}
