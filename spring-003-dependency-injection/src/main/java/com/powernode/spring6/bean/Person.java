package com.powernode.spring6.bean;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Person {

    private List<String> names;

    private Set<String> addresses;

    private Map<Integer, String> sessions;

    // 本质上，也是一个Map集合
    // 继承了Hashtable<Object,Object>
    // 规定其key-value都必须为String
    private Properties properties;

    @Override
    public String toString() {
        return "Person{" +
                "names=" + names +
                ", addresses=" + addresses +
                ", sessions=" + sessions +
                ", properties=" + properties +
                '}';
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setSessions(Map<Integer, String> sessions) {
        this.sessions = sessions;
    }

    public void setAddresses(Set<String> addresses) {
        this.addresses = addresses;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

}
