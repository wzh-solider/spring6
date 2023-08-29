package org.powernode.service;

import org.powernode.dao.OrderDao;
import org.powernode.dao.impl.OrderDaoImplForMySQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/29 18:51
 * @since 1.0
 */
@Service
public class OrderService {

    // 使用@Autowired时，不需要指定内容
    // @Autowired默认根据类型进行注入
    // @Autowired
    // private OrderDao orderDao;

    // 如果含有多个实现类，需要联合@Qualifier指定具体的实现类
    // @Autowired
    // @Qualifier("orderDaoImplForMySQL")
    private OrderDao orderDao;

    // 将注解放到set方法上
    // @Autowired
    // @Qualifier("orderDaoImplForMySQL")
    // public void setOrderDao(OrderDao orderDao) {
    //     this.orderDao = orderDao;
    // }

    // 注解可以放到构造方法上
    public OrderService(@Autowired @Qualifier("orderDaoImplForMySQL") OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void generate() {
        orderDao.insert();
    }
}
