package com.powernode.service;

import com.powernode.pojo.Account;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/6 10:59
 * @since 1.0
 */
@Service
public interface AccountService {
    /**
     * 业务方法，转账业务
     */
    void transfer(String actNo1, String actNo2, double money) throws SQLException;

    /**
     * 保存业务
     */
    void save(Account act);

    void withdraw();
}
