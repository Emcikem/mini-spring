package com.lyq.minispring.test.service;

/**
 * @author Emcikem
 * @create 2022/5/7
 * @desc
 */
public class WorldServiceImpl implements WorldService {
    @Override
    public void explode() {
        System.out.println("The Earth is going to explode");
    }

    @Override
    public void test() {
        System.out.println(1);
    }
}
