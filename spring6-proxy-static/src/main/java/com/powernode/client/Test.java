package com.powernode.client;

import com.powernode.service.OrderService;
import com.powernode.service.impl.OrderServiceImpl;
import com.powernode.service.impl.OrderServiceProxy;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 8:41
 * @since 1.0
 */
public class Test {
    public static void main(String[] args) {
        // OrderService orderService = new OrderServiceImpl();
        // orderService.selectOrder();
        // orderService.updateOrder();
        // orderService.deleteOrder();

        // 通过代理类，进行业务方法的调用
        OrderService orderService = new OrderServiceProxy(new OrderServiceImpl());
        orderService.selectOrder();
        orderService.updateOrder();
        orderService.deleteOrder();
    }
}
