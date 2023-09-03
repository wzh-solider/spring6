package com.powernode.proxy.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 专门负责计时的调用处理器
 * 这个调用处理器，编写计时相关的增强代码
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 15:16
 * @since 1.0
 */
public class TimerInvocationHandler implements InvocationHandler {

    Object target; // 目标对象

    public TimerInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 必须命名为invoke，因为jdk在底层需要调用invoke方法
     * 调用时间？
     *      invoke会在执行接口中的方法时，被调用
     *
     *  invoke方法中的参数：
     *      Object proxy：代理对象 （一般不用）
     *      Method method：目标对象上的目标方法（需要执行的目标方法就是他）
     *      Object[] args：目标对象中的方法的实参
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        // 记录开始时间
        long begin = System.currentTimeMillis();
        // 执行目标对象的方法
        Object resultValue = method.invoke(target, args);
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("时间" + (end - begin));

        // 这里的返回值，就是目标对象中的方法的返回值
        return resultValue;
    }
}
