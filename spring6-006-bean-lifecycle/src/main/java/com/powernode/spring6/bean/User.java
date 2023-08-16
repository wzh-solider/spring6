package com.powernode.spring6.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * Bean的生命周期按照粗略的五步：
 * 1、实例化Bean对象（调用无参构造方法）
 * 2、给Bean对象属性赋值（调用set方法）
 * 3、初始化Bean对象（会调用Bean的init方法，但是这个方法需要自己写、自己配）
 * 4、使用Bean对象
 * 5、销毁Bean对象（会调用Bean对象的destroy方法，同样这个方法需要自己写、自己配）
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class User implements
        BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
        InitializingBean, DisposableBean {
    private String name;

    public User() {
        System.out.println("第一步、User的无参构造器被调用");
    }

    public User(String name) {
        this.name = name;
    }

    public void setName(String name) {
        System.out.println("第二步、User的set方法被调用，传入参数：" + name);
        this.name = name;
    }

    public void initBean() {
        System.out.println("第六步、User的initBean方法被调用，Bean对象被初始化");
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public void destroyBean() {
        System.out.println("第十步、User的destroyBean方法被调用，Bean对象被销毁");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("    ②设置Bean的类加载器");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("    ③设置Bean工厂");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("第三步、");
        System.out.println("    ①设置Bean的名字");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("第五步、Bean初始化中执行");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("第九步、Bean对象销毁之前 | Bean对象使用之后执行了");
    }
}
