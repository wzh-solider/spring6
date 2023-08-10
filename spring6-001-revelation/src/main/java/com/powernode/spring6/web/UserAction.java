package com.powernode.spring6.web;

import com.powernode.spring6.service.UserService;
import com.powernode.spring6.service.impl.UserServiceImpl;

/**
 * 表示层
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class UserAction {

    // private UserService userService = new UserServiceImpl(); ==> 违背DIP原则

    // 修改
    private UserService userService;

    /**
     * 删除信息请求
     */
    public void deleteRequest() {
        userService.deleteUser();
    }
}
