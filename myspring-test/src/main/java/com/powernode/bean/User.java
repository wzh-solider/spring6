package com.powernode.bean;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/23 2:10
 * @since 1.0
 */
public class User {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
