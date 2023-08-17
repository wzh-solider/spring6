package com.powernode.spring6.bean2;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Wife {

    private String name;
    private Husband husband;

    /**
     * @return 返回字符串，注意在打印husband的时候，需要使用husband.getName()不然会造成栈溢出错误
     */
    @Override
    public String toString() {
        return "Wife{" +
                "name='" + name + '\'' +
                ", husband=" + husband.getName() +
                '}';
    }

    public String getName() {
        return name;
    }

    public Wife(String name, Husband husband) {
        this.name = name;
        this.husband = husband;
    }
}
