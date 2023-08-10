package com.powernode.spring6.service.impl;

import com.powernode.spring6.dao.UserDao;
import com.powernode.spring6.dao.impl.UserDaoImpl;
import com.powernode.spring6.service.UserService;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class UserServiceImpl implements UserService {
    // private UserDao userDao = new UserDaoImpl(); ==> 违背DIP原则

    // 修改为
    private UserDao userDao;

    @Override
    public void deleteUser() {
        // 删除用户的业务逻辑

        userDao.deleteById();
    }
}
