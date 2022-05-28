package com.lyq.minispring.aop.framework;

import com.lyq.minispring.aop.AdvisedSupport;

/**
 * @author Emcikem
 * @create 2022/5/28
 * @desc 一个普通的工厂模式，根据入参决定返回哪个代理方式
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new CglibAopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
