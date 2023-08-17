# Bean的循环依赖问题

> A对象中有B属性，B对象中有A属性
>
> ![image-20230817164538350](D:\code\spring6_projects\spring6\spring6-007-circular-dependency\img\cirularDependency.png)



## setter + singleton模式下的循环依赖

```java
package com.powernode.spring6.bean;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }
}
```

```java
package com.powernode.spring6.bean;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- setter + singleton模式下的循环依赖 -->
    <bean id="wifeBean" class="com.powernode.spring6.bean.Wife">
        <property name="name" value="张三"/>
        <property name="husband" ref="husbandBean"/>
    </bean>

    <bean id="husbandBean" class="com.powernode.spring6.bean.Husband">
        <property name="name" value="李四"/>
        <property name="wife" ref="wifeBean"/>
    </bean>
</beans>
```



**测试程序**

```java
package com.powernode.spring6.test;

import com.powernode.spring6.bean.Husband;
import com.powernode.spring6.bean.Wife;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class CircularDependencyTest {

    @Test
    public void testCircularDepend() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6.xml");

        Husband husbandBean = applicationContext.getBean("husbandBean", Husband.class);
        Wife wifeBean = applicationContext.getBean("wifeBean", Wife.class);

        System.out.println(husbandBean);
        System.out.println(wifeBean);
    }
}
```

![image-20230817173008670](D:\code\spring6_projects\spring6\spring6-007-circular-dependency\img\stter_singleton_result.png)

- singleton + setter模式下的循环依赖，在spring中不会出现问题
- spring解决了这个模式下的循环依赖
  - 主要原因是，在这种模式下Spring对Bean的管理主要分为两个清晰的阶段
    - 1、在Spring容器加载的时候，实例化Bean。只要其中一个Bean实例化后，立马就会被“曝光”【曝光时不会对属性进行赋值】
    - 2、Bean“曝光”之后，再进行属性赋值【调用set方法】
  - 核心解决方案
    - **实例化对象**和**对象的属性赋值**分为两个阶段来完成

- 只有在scope是`singleton`的情况下，Spring容器才会提前进行曝光处理



## prototype下的set注入产生的循环依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- prototype + setter模式下的循环依赖 -->
    <bean id="wifeBean" class="com.powernode.spring6.bean.Wife" scope="prototype">
        <property name="name" value="张三"/>
        <property name="husband" ref="husbandBean"/>
    </bean>
    <bean id="husbandBean" class="com.powernode.spring6.bean.Husband" scope="prototype">
        <property name="name" value="李四"/>
        <property name="wife" ref="wifeBean"/>
    </bean>
</beans>
```

![image-20230817180700166](D:\code\spring6_projects\spring6\spring6-007-circular-dependency\img\stter_prototype_error.png)



**解决方法**

- 将其中的一个Bean设置为singleton，使得循环创建可以终止

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <!-- prototype + setter模式下的循环依赖 -->
      <bean id="wifeBean" class="com.powernode.spring6.bean.Wife" scope="singleton">
          <property name="name" value="张三"/>
          <property name="husband" ref="husbandBean"/>
      </bean>
      <bean id="husbandBean" class="com.powernode.spring6.bean.Husband" scope="prototype">
          <property name="name" value="李四"/>
          <property name="wife" ref="wifeBean"/>
      </bean>
  </beans>
  ```

![image-20230817182840846](D:\code\spring6_projects\spring6\spring6-007-circular-dependency\img\stter_prototype_result.png)



## 通过构造注入，产生的循环依赖问题

```java
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
```

```java
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
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="wife" class="com.powernode.spring6.bean2.Wife">
        <constructor-arg name="name" value="张三"/>
        <constructor-arg name="husband" ref="husband"/>
    </bean>
    <bean id="husband" class="com.powernode.spring6.bean2.Husband">
        <constructor-arg name="name" value="李四"/>
        <constructor-arg name="wife" ref="wife"/>
    </bean>
</beans>
```



**测试**

```java
package com.powernode.spring6.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class CircularDependencyTest {

    /**
     * 构造方法注入
     */
    @Test
    public void testCircularDependByConstructor() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6-constructor.xml");
        com.powernode.spring6.bean2.Wife wife = applicationContext.getBean("wife", com.powernode.spring6.bean2.Wife.class);
        com.powernode.spring6.bean2.Husband husband = applicationContext.getBean("husband", com.powernode.spring6.bean2.Husband.class);
        System.out.println(wife);
        System.out.println(husband);
    }
}
```

![image-20230817191556991](D:\code\spring6_projects\spring6\spring6-007-circular-dependency\img\constructor_error.png)

- 通过构造注入，Spring并没有实现**Bean实例化**和**属性赋值**分离

  - setter的Bean实例化：通过无参构造方法实现

  - setter的属性赋值：通过set方法实现

  - 但是构造注入，只会调用有参构造器，并向构造器中传入参数

    一步完成，并没有实现分离

- 所以，通过构造注入，不能解决循环依赖的问题



# Spring中的三个缓存

***private final Map<String, Object> singletonObjects***

一级缓存：存储单例Bean对象，是完整的单例对象，属性已经赋值

- `key`：Bean对象的名字【id】
- `value`：单例Bean对象



***private final Map<String, Object> earlySingletonObjects***

二级缓存：存储早期的单例Bean对象，这个单例Bean对象的属性没有赋值，只是一个实例对象

- `key`：Bean对象的名字【id】
- `value`：早期单例Bean对象



***private final Map<String, ObjectFactory<?>> singletonFactories***

三级缓存：存储工厂对象，每个单例Bean对象都会对应一个单例工厂对象

- `key`：Bean对象的名字【id】
- `value`：创建该Bean对象的单例工厂对象