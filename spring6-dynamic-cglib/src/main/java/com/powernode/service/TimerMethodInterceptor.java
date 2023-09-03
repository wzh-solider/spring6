package com.powernode.service;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 16:36
 * @since 1.0
 */
public class TimerMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws Throwable {

        // 前面增强
        long start = System.currentTimeMillis();

        // 调用目标方法
        Object retValue = methodProxy.invokeSuper(o, objects);

        // 后面增强
        long end = System.currentTimeMillis();

        System.out.println("目标方法执行时间：" + (end - start) + "毫秒");

        return retValue;
    }
}
