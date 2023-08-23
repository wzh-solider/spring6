package com.powernode.bean;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/23 2:11
 * @since 1.0
 */
public class UserDao {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void insert() {
        System.out.println(user);
        System.out.println("数据库进行插入数据");
    }
}
