package com.powernode.spring6.bean;

import java.util.Date;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Cat {
    private String name;
    private int age;
    private Date birth;

    public Cat(String name, int age, Date birth) {
        this.name = name;
        this.age = age;
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birth=" + birth +
                '}';
    }
}
