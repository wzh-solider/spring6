package com.powernode.spring6.bean;

/**
 * Bean的生命周期按照粗略的五步：
 * 1、实例化Bean对象（调用无参构造方法）
 * 2、给Bean对象属性赋值（调用set方法）
 * 3、初始化Bean对象（会调用Bean的init方法，但是这个方法需要自己写、自己配）
 * 4、使用Bean对象
 * 5、销毁Bean对象（会调用Bean对象的destroy方法，同样这个方法需要自己写、自己配）
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class User {
    private String name;

    public User() {
        System.out.println("User的无参构造器被调用");
    }

    public User(String name) {
        this.name = name;
    }

    public void setName(String name) {
        System.out.println("User的set方法被调用，传入参数：" + name);
        this.name = name;
    }

    public void init() {
        System.out.println("User的init方法被调用，Bean对象被初始化");
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public void destroy() {
        System.out.println("User的destroy方法被调用，Bean对象被销毁");
    }
}
