package com.powernode.service.impl;

import com.powernode.service.OrderService;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 8:36
 * @since 1.0
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public void selectOrder() {
        // 模拟订单查询耗时操作
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("查询订单");
    }

    @Override
    public void updateOrder() {
        // 模拟订单修改耗时操作
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("修改订单");
    }

    @Override
    public void deleteOrder() {
        // 模拟订单删除耗时操作
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("删除订单");
    }
}
