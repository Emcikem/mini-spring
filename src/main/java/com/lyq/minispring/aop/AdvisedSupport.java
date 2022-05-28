package com.lyq.minispring.aop;


import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author Emcikem
 * @create 2022/5/26
 * @desc
 */
public class AdvisedSupport {

    /**
     * 目标代理类
     */
    private TargetSource targetSource;

    /**
     * invoke方法，代理方法
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 代理匹配到哪些方法
     */
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
