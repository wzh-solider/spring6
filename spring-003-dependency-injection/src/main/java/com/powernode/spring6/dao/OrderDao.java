package com.powernode.spring6.dao;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public interface OrderDao {
    void deleteById();

    /**
     * 生成订单
     */
    void generate();
}
