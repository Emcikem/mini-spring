package com.lyq.minispring.aop;

/**
 * @author Emcikem
 * @create 2022/5/6
 * @desc
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
