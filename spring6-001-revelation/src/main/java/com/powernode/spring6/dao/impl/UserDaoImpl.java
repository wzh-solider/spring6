package com.powernode.spring6.dao.impl;

import com.powernode.spring6.dao.UserDao;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void deleteById() {
        System.out.println("mysql正在删除用户信息。。。");
    }
}
