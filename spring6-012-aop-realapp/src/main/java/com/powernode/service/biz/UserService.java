package com.powernode.service.biz;

import org.springframework.stereotype.Service;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/5 17:15
 * @since 1.0
 */
@Service
public class UserService {

    public void saveUser() {
        System.out.println("新增用户信息");
    }

    public void updateUser() {
        System.out.println("修改用户信息");
    }

    public void deleteUser() {
        System.out.println("删除用户信息");
    }

    public void getUser() {
        System.out.println("查询用户信息");
    }
}
