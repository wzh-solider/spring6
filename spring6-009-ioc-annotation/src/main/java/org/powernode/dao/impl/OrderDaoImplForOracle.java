package org.powernode.dao.impl;

import org.powernode.dao.OrderDao;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/29 19:19
 * @since 1.0
 */
@Repository
public class OrderDaoImplForOracle implements OrderDao {

    @Override
    public void insert() {
        System.out.println("Oracle正在存储用户数据。。。");
    }
}
