package com.lyq.minispring.test.jdk;

import com.sun.org.apache.xpath.internal.objects.XObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Emcikem
 * @create 2022/5/26
 * @desc
 */
public class HelloProxy implements InvocationHandler {

    private Object subject;

    public HelloProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before" + method.getName());
        System.out.println(proxy.getClass().getName());
        Object invoke = method.invoke(subject, args);
        System.out.println("after" + method.getName());
        return invoke;
    }


    public Object getInstance() {
        Object object = Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), this);
        System.out.println(object.getClass().getName());
        return object;
    }

}
