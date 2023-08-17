package com.powernode.spring6.reflect.test;

import com.powernode.spring6.reflect.Person;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class ReflectTest {

    @Test
    public void testReflectPersonMethod() throws Exception {
        String className = "com.powernode.spring6.reflect.Person";
        String propertyName = "age";

        Class<?> clazz = Class
                .forName(className);
        Object o = clazz.getDeclaredConstructor().newInstance();

        String setMethodName = "set"
                + propertyName.toUpperCase().charAt(0)
                + propertyName.substring(1);
        Field f = clazz.getDeclaredField("age");
        clazz.getDeclaredMethod(setMethodName, f.getType())
                .invoke(o, 10);

        // 开启反射风暴
        f.setAccessible(true);
        int age = f.getInt(o);
        System.out.println(age);

        System.out.println((Person)o);
    }


    /**
     * 使用反射机制，用来实现相应方法
     */
    @Test
    public void testReflectSomeServiceMethod() throws Exception {
        Class<?> clazz = Class
                .forName("com.powernode.spring6.reflect.SomeService");

        Object o = clazz.getConstructor().newInstance();
        clazz.getMethod("doSome").invoke(o);

        String s = (String) clazz
                .getMethod("doSome", String.class)
                .invoke(o, "张三");
        System.out.println(s);

        String str = (String) clazz
                .getMethod("doSome", String.class, int.class)
                .invoke(o, "李四", 2);
        System.out.println(str);
    }
}
