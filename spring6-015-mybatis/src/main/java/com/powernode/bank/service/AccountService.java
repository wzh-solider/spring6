package com.powernode.bank.service;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/10 21:25
 * @since 1.0
 */
public interface AccountService {

    /**
     * 转账业务
     */
    void transfer(String fromActNo, String toActNo, Double money);
}
