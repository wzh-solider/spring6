package com.powernode.spring6.reflect;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class SomeService {

    public void doSome() {
        System.out.println("public void doSome()执行。");
    }

    public String doSome(String s) {
        System.out.println("public String doSome(String s)执行。");
        return s;
    }

    public String doSome(String s, int i) {
        System.out.println("public String doSome(String s, int i)执行。");
        return s + i;
    }
}
