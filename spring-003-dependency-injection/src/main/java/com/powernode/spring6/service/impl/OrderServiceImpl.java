package com.powernode.spring6.service.impl;

import com.powernode.spring6.dao.OrderDao;
import com.powernode.spring6.dao.UserDao;
import com.powernode.spring6.service.OrderService;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private UserDao userDao;

//    public OrderServiceImpl(OrderDao orderDao) {
//        this.orderDao = orderDao;
//    }
//
//    public OrderServiceImpl(OrderDao orderDao, UserDao userDao) {
//        this.orderDao = orderDao;
//        this.userDao = userDao;
//    }


    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void delete() {
        userDao.insert();
        orderDao.deleteById();
    }

    /**
     * 生成订单的业务方法
     */
    @Override
    public void generate() {
        orderDao.generate();
    }
}
