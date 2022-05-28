package com.lyq.minispring.aop;

import java.lang.reflect.Method;

/**
 * @author Emcikem
 * @create 2022/5/28
 * @desc
 */
public interface MethodBeforeAdvice extends BeforeAdvice{

    void before(Method method, Object[] args, Object target) throws Throwable;
}
