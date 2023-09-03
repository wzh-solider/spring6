package com.powernode.proxy.client;

import com.powernode.proxy.service.OrderService;
import com.powernode.proxy.service.TimerInvocationHandler;
import com.powernode.proxy.service.impl.OrderServiceImpl;
import com.powernode.proxy.utils.ProxyUtil;

/**
 * 测试动态代理类
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 9:56
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) {
        // 创建目标对象
        OrderService target = new OrderServiceImpl();

        /**
         * 创建代理对象
         * 1、newProxyInstance ==> 新建代理对象
         *      本质上，这个Proxy.newProxyInstance()方法的执行，做了两件事：
         *          第一件事：在内存中动态生成了一个字节码class文件
         *          第二件事：new对象，通过内存中生成的代理类这个代码，实例化了代理对象
         *
         *  2、newProxyInstance() 方法的三个参数
         *          ClassLoader loader：类加载器，用来指定目标参数
         *          Class<?>[] interfaces：代理类需要实现的接口
         *          InvocationHandler h：调用处理器，是一个接口
         *              在调用处理器中，编写的是增强代码
         *
         *  3、newProxyInstance()方法实现了接口，所以可以向下转型为接口对象
         */

        // OrderService orderService = (OrderService) Proxy.newProxyInstance(target.getClass().getClassLoader(),
        //                     target.getClass().getInterfaces(), new TimerInvocationHandler(target));

        // 通过封装的工具类，简化上述Proxy.newProxyInstance()方法的调用
        OrderService orderService = (OrderService) ProxyUtil.newInstance(target, new TimerInvocationHandler(target));

        // 调用代理对象的代理方法
        orderService.selectOrder();
        orderService.updateOrder();
        orderService.deleteOrder();
        String s = orderService.testReturn();
        System.out.println(s);
    }
}
