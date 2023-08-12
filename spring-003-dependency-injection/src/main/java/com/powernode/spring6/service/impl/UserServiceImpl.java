package com.powernode.spring6.service.impl;

import com.powernode.spring6.dao.UserDao;
import com.powernode.spring6.dao.VipDao;
import com.powernode.spring6.service.UserService;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private VipDao vipDao;

    // 通过set注入，实现IoC
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setVipDao(VipDao vipDao) {
        this.vipDao = vipDao;
    }


//    public UserServiceImpl(UserDao userDao, VipDao vipDao) {
//        this.userDao = userDao;
//        this.vipDao = vipDao;
//    }

    @Override
    public void save() {
        userDao.insert();
        vipDao.insert();
    }
}
