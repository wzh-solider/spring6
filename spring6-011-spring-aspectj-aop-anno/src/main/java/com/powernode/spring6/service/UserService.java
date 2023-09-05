package com.powernode.spring6.service;

import org.springframework.stereotype.Component;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/5 14:54
 * @since 1.0
 */
// 目标类
@Component
public class UserService {

    // 目标方法
    public void login() {
        System.out.println("系统正在进行身份认证......");
        // if (1 == 1) {
        //     throw new RuntimeException("运行异常");
        // }
    }
}
