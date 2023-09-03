package com.powernode.service;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 16:19
 * @since 1.0
 */
public class UserService {

    // 目标方法
    public boolean login(String name, String password) {
        System.out.println("系统正在验证身份。。。");
        return "admin".equals(name) && "123".equals(password);
    }

    // 目标方法
    public void logout() {
        System.out.println("系统正在退出。。。");
    }
}
