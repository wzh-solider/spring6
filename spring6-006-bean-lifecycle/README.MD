# Bean的生命周期



## 什么是Bean的生命周期

Spring 其实就是一个管理Bean对象的工厂

负责对象的创建、对象的销毁等

生命周期就是：对象从创建开始到最终销毁的整个过程

- 创建Bean对象的时机
- 创建Bean对象的前后调用的方法
- Bean对象的销毁时机
- Bean对象销毁前后调用的方法



## Bean的5步生命周期

Bean生命周期的管理，可参考Spring源码：`AbstractAutowireCapableBeanFactory`

类中的`doCreateBean()`方法

Bean的生命周期，粗略分为五步：

- 实例化Bean：**调用Bean的无参构造方法**
- Bean属性赋值：**调用Bean对象的set方法**
- 初始化Bean：**调用Bean对象的init方法**（**但是，init方法需要自己写，自己配置**）
- 使用Bean：**调用Bean对象**
- 销毁Bean：**调用Bean对象的destroy方法**（**同样，destroy方法也需要自己写，自己配置**）

![image-20230816000417252](img/fiveStep_beanLifecycle.png)

**测试程序**

```java
package com.powernode.spring6.bean;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class User {
    private String name;

    public User() {
        System.out.println("User的无参构造器被调用");
    }

    public User(String name) {
        this.name = name;
    }

    public void setName(String name) {
        System.out.println("User的set方法被调用，传入参数：" + name);
        this.name = name;
    }

    public void init() {
        System.out.println("User的init方法被调用，Bean对象被初始化");
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public void destroy() {
        System.out.println("User的destroy方法被调用，Bean对象被销毁");
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="user" class="com.powernode.spring6.bean.User" init-method="init" destroy-method="destroy">
        <property name="name" value="张三"/>
    </bean>

</beans>
```

```java
package com.powernode.spring6.test;

import com.powernode.spring6.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class BeanLifecycleTest {

    @Test
    public void testBeanLifecycle() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        User user = applicationContext.getBean("user", User.class);
        System.out.println("使用User对象：" + user);

        // 手动销毁IoC容器
        ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) applicationContext;
        context.close();
    }
}
```

![image-20230815230353212](img/liveStep_beanLifecycle_testResult.png)



## Bean的7步生命周期

> 在初步的5部中，可以在初始化Bean前、后添加代码
>
> 可以加入Bean后处理器

![image-20230815233500787](img/eightStep_beanLifecycle.png)

编写一个类，实现`BeanPostProcessor`类，并重写`before`和`after`方法

```java
package com.powernode.spring6.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class LogBeanPostProcessor implements BeanPostProcessor {
   
    /**
     * @param bean 实例化的Bean对象
     * @param beanName 实例化Bean对象的名字
     * @return Bean对象，默认为Object
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("Bean后处理器的before方法执行，即将开始初始化。。。");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("Bean后处理器的after方法执行，已完成初始化。。。");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="user" class="com.powernode.spring6.bean.User" init-method="init" destroy-method="destroy">
        <property name="name" value="张三"/>
    </bean>

    <!-- 配置Bean后处理器 -->
    <!-- 配置完之后，自动适配所有的Bean对象 -->
    <bean class="com.powernode.spring6.bean.LogBeanPostProcessor"/>

</beans>
```

- 配置完Bean后处理器之后，将作用于当前配置文件中所有的Bean对象



**调用原先的测试模块儿**

![image-20230815232032844](img/eightStep_beanLifecycle_testResult.png)



## Bean的10步生命周期

> 如果根据源码追踪，可以将Bean的生命周期，划分为十步

![image-20230815235534360](img/tenStep_beanLifecycle.png)