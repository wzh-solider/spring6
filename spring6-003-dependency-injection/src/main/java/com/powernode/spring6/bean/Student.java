package com.powernode.spring6.bean;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Student {
    private String name;

    // 级联属性赋值
    private Clazz clazz;

    public Clazz getClazz() {
        return clazz;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", clazz=" + clazz +
                '}';
    }
}
