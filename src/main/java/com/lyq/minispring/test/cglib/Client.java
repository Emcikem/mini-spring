package com.lyq.minispring.test.cglib;

/**
 * @author Emcikem
 * @create 2022/5/27
 * @desc
 */
public class Client {

    public static void main(String[] args) {
        Hello hello = new Hello();
        CglibInterceptor cglibInterceptor = new CglibInterceptor(hello);
        Hello instance = (Hello) cglibInterceptor.getInstance();
        instance.cal();
    }
}
