package com.powernode.spring6.bean;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Clazz {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "name='" + name + '\'' +
                '}';
    }
}
