package com.lyq.minispring.test.aop;

import com.lyq.minispring.aop.aspectj.AspectJExpressionPointcut;
import com.lyq.minispring.test.service.HelloService;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Emcikem
 * @create 2022/5/6
 * @desc
 */
public class PointcutExpressionTest {

    @Test
    public void testPointcutExpress() throws Exception {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.lyq.minispring.test.service.HelloService.*(..))");
        Class<HelloService> clazz = HelloService.class;
        Method method = clazz.getDeclaredMethod("sayHello");

        assertThat(pointcut.matches(clazz)).isTrue();
        assertThat(pointcut.matches(method, clazz)).isTrue();
    }
}
