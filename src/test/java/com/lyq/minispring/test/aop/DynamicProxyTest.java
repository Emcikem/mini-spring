package com.lyq.minispring.test.aop;

import com.lyq.minispring.aop.AdvisedSupport;
import com.lyq.minispring.aop.MethodMatcher;
import com.lyq.minispring.aop.TargetSource;
import com.lyq.minispring.aop.aspectj.AspectJExpressionPointcut;
import com.lyq.minispring.aop.framework.CglibAopProxy;
import com.lyq.minispring.aop.framework.JdkDynamicAopProxy;
import com.lyq.minispring.test.common.WorldServiceInterceptor;
import com.lyq.minispring.test.service.WorldService;
import com.lyq.minispring.test.service.WorldServiceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Emcikem
 * @create 2022/5/7
 * @desc
 */
public class DynamicProxyTest {

    private AdvisedSupport advisedSupport;

    /**
     * 需要精确到某个方法，像普通的jdk代理其实是把整个类都代理了，同时他的所有方法都被代理了。
     * 我们需要的是代理整个类，但是只有部分方法被修改，所以需要在invoke时判断当前类是不是我需要的类
     * 那么久用pointcut表达式，先判断是否是匹配的方法，是的话就走我们写的代理方法
     * 否则就普通反射进行。
     * jdk代理是基于接口实现的，所以只能代理接口
     */
    @Before
    public void setup() {
        WorldService wordService = new WorldServiceImpl();

        advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(wordService);
        WorldServiceInterceptor methodInterceptor = new WorldServiceInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.lyq.minispring.test.service.WorldService.explode(..))").getMethodMatcher();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);
    }

    @Test
    public void testJdkDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testCglibDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

}
