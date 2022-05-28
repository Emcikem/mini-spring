package com.lyq.minispring.test.common;

import com.lyq.minispring.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author Emcikem
 * @create 2022/5/28
 * @desc
 */
public class WorldServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("BeforeAdvice: do something before the earth explodes");
    }
}
