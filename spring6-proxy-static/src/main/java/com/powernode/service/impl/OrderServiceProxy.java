package com.powernode.service.impl;

import com.powernode.service.OrderService;

/**
 * 通过静态代理模式，实现相关业务方法
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 8:47
 * @since 1.0
 */
public class OrderServiceProxy implements OrderService {
    OrderService target;

    public OrderServiceProxy(OrderService target) {
        this.target = target;
    }

    @Override
    public void selectOrder() {
        long begin = before();
        this.target.selectOrder();
        long end = after();
        System.out.println("耗时：" + (end - begin) + "ms");
    }

    @Override
    public void updateOrder() {
        long begin = before();
        this.target.updateOrder();
        long end = after();
        System.out.println("耗时：" + (end - begin) + "ms");
    }

    @Override
    public void deleteOrder() {
        long begin = before();
        this.target.deleteOrder();
        long end = after();
        System.out.println("耗时：" + (end - begin) + "ms");
    }

    public long before() {
        System.out.println("前置通知");
        return System.currentTimeMillis();
    }

    public long after() {
        System.out.println("后置通知");
        return System.currentTimeMillis();
    }
}
