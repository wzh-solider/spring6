package com.powernode.service.transcation;

import org.springframework.stereotype.Service;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/5 16:49
 * @since 1.0
 */
@Service
public class OrderService {

    // 生成订单的业务方法
    public void generate() {
        System.out.println("订单正在生成。。。");
    }

    // 取消订单的业务方法
    public void cancel() {
        System.out.println("订单已取消。。。");
    }
}
