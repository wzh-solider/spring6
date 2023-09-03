package com.powernode.proxy.utils;

import com.powernode.proxy.service.TimerInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 15:50
 * @since 1.0
 */
public class ProxyUtil {

    public static Object newInstance(Object target, InvocationHandler h) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(), h);
    }
}
