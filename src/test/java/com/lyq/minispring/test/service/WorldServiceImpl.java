package com.lyq.minispring.test.service;

import cn.hutool.extra.tokenizer.Word;

/**
 * @author Emcikem
 * @create 2022/5/7
 * @desc
 */
public class WorldServiceImpl implements WordService {
    @Override
    public void explode() {
        System.out.println("The Earth is going to explode");
    }
}
