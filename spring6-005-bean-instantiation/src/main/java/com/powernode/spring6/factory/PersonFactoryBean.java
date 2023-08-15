package com.powernode.spring6.factory;

import com.powernode.spring6.bean.Person;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class PersonFactoryBean implements FactoryBean<Person> {

    @Override
    public Person getObject() throws Exception {
        return new Person();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        // 这个方法，是接口中默认实现的
        // 默认为true，也就是默认单例
        // 如果想更改为多例，则返回false
        return true;
    }
}
