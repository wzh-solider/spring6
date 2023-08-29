package com.powernode.bean3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/28 21:40
 * @since 1.0
 */
@Component
public class User {
    @Value("张三")
    private String name;

    @Value("18")
    private int age;

    // // 当使用注解方式进行属性注入时，不需要提供set方法
    //     // @Value注解也可以写在set方法上
    // @Value("李四")
    // public void setName(String name) {
    //     this.name = name;
    // }
    //
    // @Value("20")
    // public void setAge(int age) {
    //     this.age = age;
    // }
    //
    //
    // // @Value注解也可以写在构造方法上
    // public User(@Value("王五") String name, @Value("40") int age) {
    //     this.name = name;
    //     this.age = age;
    // }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
