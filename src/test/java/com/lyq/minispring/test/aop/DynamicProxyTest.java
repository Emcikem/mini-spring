package com.lyq.minispring.test.aop;

import com.lyq.minispring.aop.AdvisedSupport;
import com.lyq.minispring.aop.TargetSource;
import com.lyq.minispring.test.service.WordService;
import com.lyq.minispring.test.service.WorldServiceImpl;
import org.junit.Test;

/**
 * @author Emcikem
 * @create 2022/5/7
 * @desc
 */
public class DynamicProxyTest {

    @Test
    public void testJdkDynamicProxy() throws Exception {
        WordService wordService = new WorldServiceImpl();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(wordService);
//        WordServiceIn

    }
}
