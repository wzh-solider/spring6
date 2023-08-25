package com.powernode.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * @author Solider
 * @version 1.0
 * @Date 2023/8/25 15:41
 * @since 1.0
 */

// 用于标注注解，叫做元注解。@Target()表示该注解可以标注在哪些地方
    // ElementType.TYPE表示可以标注在类上
    // ElementType.FIELD表示可以标注在属性上
@Target(value = {ElementType.TYPE, ElementType.FIELD})

// 用于标注注解，叫做元注解。@Retention()表示该注解可以保留多长时间
    // RetentionPolicy.RUNTIME表示该注解可以保留到运行时(保留到class文件中)
        // 可以被反射机制读取到
    // RetentionPolicy.SOURCE表示该注解可以保留到源码时(不保留到class文件中)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {

    // 注解中的属性，叫做成员变量
    // String 属性类型
    // value 属性名
    String value();
}
