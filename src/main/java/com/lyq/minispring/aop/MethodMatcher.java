package com.lyq.minispring.aop;

import java.lang.reflect.Method;

/**
 * @author Emcikem
 * @create 2022/5/6
 * @desc
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);
}
