package com.powernode.pojo;

import org.springframework.stereotype.Repository;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/6 10:51
 * @since 1.0
 */
@Repository
public class Account {
    private String actNo;
    private Double balance;

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "actNo='" + actNo + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String getActNo() {
        return actNo;
    }

    public void setActNo(String actNo) {
        this.actNo = actNo;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Account(String actNo, Double balance) {
        this.actNo = actNo;
        this.balance = balance;
    }
}
