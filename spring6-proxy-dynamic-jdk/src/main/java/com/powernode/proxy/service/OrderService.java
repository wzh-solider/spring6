package com.powernode.proxy.service;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 8:35
 * @since 1.0
 */
public interface OrderService {

    /**
     * 测试有返回值的方法
     */
    String testReturn();

    /**
     * 查询订单
     */
    void selectOrder();

    /**
     * 修改订单
     */
    void updateOrder();

    /**
     * 删除订单
     */
    void deleteOrder();
}
