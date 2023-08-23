package com.powernode.spring6.bean;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class SimpleValueType {
    /*
    public static boolean isSimpleValueType(Class<?> type) {
            return Void.class != type && Void.TYPE != type
                    && (ClassUtils.isPrimitiveOrWrapper(type) 八种基本数据类型，及其包装类
                        || Enum.class.isAssignableFrom(type) 枚举类
                        || CharSequence.class.isAssignableFrom(type) 字符串序列(String implements CharSequence)
                        || Number.class.isAssignableFrom(type) 数字类型(Integer... implements Number)
                        || Date.class.isAssignableFrom(type) 日期类型(推荐使用ref类型，如果需要必须按规定格式，否则报异常)
                        || Temporal.class.isAssignableFrom(type) Java8之后提供的时间和时区类型
                        || URI.class == type URI地址
                        || URL.class == type URL地址
                        || Locale.class == type 语言类
                        || Class.class == type); Class类
        }
     */

    private int age;
    private Integer age1;

    private boolean flag;
    private Boolean flag1;

    private char c;
    private Character c1;

    private Season season;

    private String username;

    private Class<?> clazz;

    @Override
    public String toString() {
        return "SimpleValueType{" +
                "age=" + age +
                ", age1=" + age1 +
                ", flag=" + flag +
                ", flag1=" + flag1 +
                ", c=" + c +
                ", c1=" + c1 +
                ", season=" + season +
                ", username='" + username + '\'' +
                ", clazz=" + clazz +
                '}';
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAge1(Integer age1) {
        this.age1 = age1;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setFlag1(Boolean flag1) {
        this.flag1 = flag1;
    }

    public void setC(char c) {
        this.c = c;
    }

    public void setC1(Character c1) {
        this.c1 = c1;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
