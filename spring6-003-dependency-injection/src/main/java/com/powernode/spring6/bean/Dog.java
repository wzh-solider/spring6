package com.powernode.spring6.bean;

import java.util.Date;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Dog {
    private String name;
    private int age;
    private Date date;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }

    // 使用p命名空间注入，底层还是set注入
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
