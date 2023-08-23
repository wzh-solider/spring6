package com.powernode.spring6.dao.impl;

import com.powernode.spring6.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public void insert() {
        logger.info("MySQL数据库正在保存用户信息。。。");
    }
}
