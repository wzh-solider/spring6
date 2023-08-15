package com.powernode.spring6.bean;

import java.util.Date;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Student {
    private Date birth;

    @Override
    public String toString() {
        return "Student{" +
                "birth='" + birth + '\'' +
                '}';
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
