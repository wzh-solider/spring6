package com.powernode.dao;

import com.powernode.pojo.Account;
import org.springframework.stereotype.Repository;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/6 10:55
 * @since 1.0
 */
@Repository
public interface AccountDao {

    /**
     * 查询账户
     */
    Account selectActNo(String actNo);

    /**
     * 更新数据
     */
    int updateAct(Account account);

    /**
     * 保存数据
     */

    int insert(Account account);
}
