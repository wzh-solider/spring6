package com.powernode.service.transcation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/5 16:47
 * @since 1.0
 */
@Service
public class AccountService {

    // 转账业务的方法
    public void transfer() {
        System.out.println("银行账户正在完成转账操作。。。");
    }

    // 取款的业务方法
    public void withdraw() {
        System.out.println("银行账户正在完成取款操作。。。");
    }

}
