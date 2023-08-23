package org.myspringframework.core;

/**
 * MySpring框架应用上下文接口
 * @author Solider
 * @version 1.0
 * @Date 2023/8/22 22:10
 * @since 1.0
 */
public interface ApplicationContext {

    /**
     * @param name xml中Bean标签的id属性值
     * @return 对应Bean对象的顶级对象
     */
    Object getBean(String name);
}
