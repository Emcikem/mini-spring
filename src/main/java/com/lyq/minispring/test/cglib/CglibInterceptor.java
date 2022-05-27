package com.lyq.minispring.test.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Emcikem
 * @create 2022/5/27
 * @desc
 */
public class CglibInterceptor implements MethodInterceptor {


    private Object target;

    public CglibInterceptor(Object target) {
        this.target = target;
    }

    /**
     * 获取代理对象
     * @return
     */
    public Object getInstance() {
        Enhancer enhancer = new Enhancer();
        // 设置父类，就是指定生成谁的代理对象，因为是基于继承去实现的，所以需要设置superclass
        enhancer.setSuperclass(target.getClass());

        // 设置拦截器 回调对象为本身对象
        enhancer.setCallback(this);

        // 生成代理类对象，并返回给调用者
        return enhancer.create();
    }


    /**
     * 拦截器
     * @param o cglib动态生成的代理类实例
     * @param method 实体类中调用的被代理方法
     * @param objects 参数列表
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("方法执行前");

        Object object = methodProxy.invoke(target, objects);

        System.out.println("方法执行后");
        return object;
    }
}
