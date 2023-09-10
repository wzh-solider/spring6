package com.powernode.service;

import com.powernode.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/10 17:21
 * @since 1.0
 */
@Service
public class UserService {

    @Resource(name = "user")
    User user;

    public void getName() {
        user.setName("张三");
        System.out.println(user);
    }
}
