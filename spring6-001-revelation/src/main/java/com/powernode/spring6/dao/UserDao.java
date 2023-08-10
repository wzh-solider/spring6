package com.powernode.spring6.dao;

/**
 * 持久层，用于处理用户数据
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public interface UserDao {

    /**
     * 根据id，删除信息
     */
    void deleteById();
}
