package com.lyq.minispring.test.jdk;

/**
 * @author Emcikem
 * @create 2022/5/26
 * @desc
 */
public class Hello implements HelloInterface{
    @Override
    public void sayHello() {
        System.out.println("hello");
    }

    @Override
    public void sayNo() {
        System.out.println("omg");
    }
}
