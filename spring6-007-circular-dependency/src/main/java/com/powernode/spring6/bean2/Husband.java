package com.powernode.spring6.bean2;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Husband {

    private String name;
    private Wife wife;

    /**
     * @return 返回字符串，注意在打印wife的时候，需要使用wife.getName()不然会造成栈溢出错误
     */
    @Override
    public String toString() {
        return "Husband{" +
                "name='" + name + '\'' +
                ", wife=" + wife.getName() +
                '}';
    }

    public String getName() {
        return name;
    }

    public Husband(String name, Wife wife) {
        this.name = name;
        this.wife = wife;
    }
}
