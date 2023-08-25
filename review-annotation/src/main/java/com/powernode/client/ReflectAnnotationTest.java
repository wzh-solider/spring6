package com.powernode.client;

import com.powernode.annotation.Component;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/25 16:44
 * @since 1.0
 */
public class ReflectAnnotationTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.powernode.bean.User");

        // 判断类上是否有Component注解
        boolean annotation = clazz.isAnnotationPresent(Component.class);
        if (annotation) {
            // 获取注解对象
            Component component = clazz.getAnnotation(Component.class);
            String value = component.value();
            System.out.println(value);
        }
    }
}
