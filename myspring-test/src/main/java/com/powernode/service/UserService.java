package com.powernode.service;

import com.powernode.bean.UserDao;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/23 2:11
 * @since 1.0
 */
public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save() {
        userDao.insert();
    }
}
