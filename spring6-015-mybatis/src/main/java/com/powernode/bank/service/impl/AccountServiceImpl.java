package com.powernode.bank.service.impl;

import com.powernode.bank.mapper.AccountMapper;
import com.powernode.bank.pojo.Account;
import com.powernode.bank.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/10 21:26
 * @since 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED) // REQUIRE值，规定支持当前事务，如果不存在则新建
    public void transfer(String fromActNo, String toActNo, Double money) {
        Account fromAct = accountMapper.selectById(fromActNo);
        if (fromAct.getBalance() < money) {
            throw new RuntimeException("金额不足，无法完成转账！！！");
        }
        Account toAct = accountMapper.selectById(toActNo);
        fromAct.setBalance(fromAct.getBalance() - money);

        // 模拟异常
        String s = null;
        s.toString();

        toAct.setBalance(toAct.getBalance() + money);
        // 更新数据
        int count = accountMapper.update(fromAct);
        count += accountMapper.update(toAct);
        if (count != 2) {
            throw new RuntimeException("转账异常，请联系柜台！！！");
        }
    }
}
