package com.lyq.minispring.context;

import com.lyq.minispring.beans.factory.HierarchicalBeanFactory;
import com.lyq.minispring.beans.factory.ListableBeanFactory;
import com.lyq.minispring.core.io.ResourceLoader;

/**
 * 应用上下文
 * 最大的好处就是把beans的内容进行了封装，使得bean具备了一个完整的生命周期
 */
public interface ApplicationContext extends
        ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
