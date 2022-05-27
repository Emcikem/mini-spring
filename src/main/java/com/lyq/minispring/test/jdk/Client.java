package com.lyq.minispring.test.jdk;

/**
 * @author Emcikem
 * @create 2022/5/26
 * @desc
 */
public class Client {
    public static void main(String[] args) {
        Hello hello = new Hello();
        HelloProxy proxy = new HelloProxy(hello);

        HelloInterface helloInterface = (HelloInterface) proxy.getInstance();
        helloInterface.sayHello();
    }
}
