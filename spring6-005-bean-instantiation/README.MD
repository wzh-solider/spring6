# Bean的实例化

> - 通过构造方法实例化
> - 通过简单工厂实例化
> - 通过factory-bean实例化
> - 通过FactoryBean接口实例化



## 通过构造方法实例化

> 默认调用Bean对象的无参构造方法

```java
// User
package com.powernode.spring6.bean;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class User {

    public User() {
        System.out.println("User的无参构造器执行");
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userBean" class="com.powernode.spring6.bean.User"/>
</beans>
```

```java
// 测试程序
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
public class UserTest {

    @Test
    public void testBean() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        // User userBean = applicationContext.getBean("userBean", User.class);
    }
}
```

![image-20230813214423391](C:\Users\旧瘾\AppData\Roaming\Typora\typora-user-images\image-20230813214423391.png)



## 通过简单工厂模式实例化

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 通过简单工厂模式进行实例化 -->
    <!-- factory-method: 指定工厂类中的静态方法，告诉spring框架执行这个方法可以获取Bean对象 -->
    <!-- 但是此方法，只能用于获取静态方法 -->
    <bean id="vipBean" class="com.powernode.spring6.factory.VipFactory" factory-method="get"/>
</beans>
```

```java
// vip
package com.powernode.spring6.bean;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Vip {
}
```

```java
// VipFactory
package com.powernode.spring6.factory;

import com.powernode.spring6.bean.Vip;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class VipFactory {

    public static Vip get() {
        return new Vip();
    }
}
```

```java
// 测试程序
package com.powernode.spring6.test;

import com.powernode.spring6.bean.Vip;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class BeanInstantiationTest {
    /**
     * 通过简单工厂类实例化Bean对象
     */
    @Test
    public void TestInstantiation_02() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        Vip vip = applicationContext.getBean("vipBean", Vip.class);
    }
}

```



## 通过工厂方法模式实例化Bean对象

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 通过工厂方法模式进行实例化 -->
    <!-- factory-bean: 告诉spring框架，需要找到哪个工厂对象 -->
    <!-- factory-method: 告诉spring框架，需要实现这个工厂对象的哪个方法 -->
    <bean id="gunFactory" class="com.powernode.spring6.factory.GunFactory"/>
    <bean id="gun" factory-bean="gunFactory" factory-method="get"/>
    
</beans>
```

```java
package com.powernode.spring6.bean;

/**
 * 工厂方法模式中的具体产品角色
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Gun {

    public Gun() {
        System.out.println("Gun的无参数构造方法执行。。。");
    }
}
```

```java
package com.powernode.spring6.factory;

import com.powernode.spring6.bean.Gun;

/**
 * 工厂方法模式中的具体工厂角色
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class GunFactory {

    public Gun get() {
        return new Gun();
    }
}
```

```java
// 测试程序
package com.powernode.spring6.test;

import com.powernode.spring6.bean.Gun;
import com.powernode.spring6.factory.GunFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class BeanInstantiationTest {

    /**
     * 通过工厂方法设计模式实例化Bean对象
     */
    @Test
    public void TestInstantiation_03() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        Gun gun = applicationContext.getBean("gun", Gun.class);
        System.out.println(gun);
    }
}
```

![image-20230815160658012](C:\Users\旧瘾\AppData\Roaming\Typora\typora-user-images\image-20230815160658012.png)



## 通过FactoryBean接口进行实例化

> 以上三种方式中，factory-bean 和 factory-method都是我们自定义的
>
> 在Spring中，当我们编写的类直接实现FactoryBean接口之后，factory-bean 和 factory-method都可以不指定了
>
> factory-bean会自动指向实现FactoryBean接口的类，
>
> factory-method会自动指向getObject()方法

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 通过实现FactoryBean接口的方法进行实例化 -->
    <bean id="personFactory" class="com.powernode.spring6.factory.PersonFactoryBean"/>
</beans>
```

```java
package com.powernode.spring6.bean;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Person {
}
```

```java
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
        return FactoryBean.super.isSingleton();
    }
}
```

```java
// 测试程序
package com.powernode.spring6.test;

import com.powernode.spring6.bean.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class BeanInstantiationTest {
    
    /**
     * 通过实现FactoryBean接口的方法，实例化Bean对象
     */
    @Test
    public void TestInstantiation_04() throws Exception {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        Person person = applicationContext.getBean("personFactory", Person.class);
        System.out.println(person);
    }
}
```

![image-20230815171351508](C:\Users\旧瘾\AppData\Roaming\Typora\typora-user-images\image-20230815171351508.png)



### 使用FactoryBean注入自定义Date

```java
package com.powernode.spring6.bean;

import java.util.Date;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Student {
    private Date birth;

    @Override
    public String toString() {
        return "Student{" +
                "birth='" + birth + '\'' +
                '}';
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
```

```java
package com.powernode.spring6.factory;

import org.springframework.beans.factory.FactoryBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class DateFactoryBean implements FactoryBean<Date> {
    private String strDate;

    public DateFactoryBean(String strDate) {
        this.strDate = strDate;
    }

    @Override
    public Date getObject() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(this.strDate);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dateFactoryBean" class="com.powernode.spring6.factory.DateFactoryBean">
        <constructor-arg name="strDate" value="2003-2-10"/>
    </bean>
    <bean id="student" class="com.powernode.spring6.bean.Student">
        <property name="birth" ref="dateFactoryBean"/>
    </bean>
</beans>
```

```java
// 测试程序
package com.powernode.spring6.test;

import com.powernode.spring6.bean.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class BeanInstantiationTest {

    /**
     * 使用factoryBean注入Date
     */
    @Test
    public void testInstantiationDateByFactoryBean() throws Exception{
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        Student student = applicationContext.getBean("student", Student.class);
        System.out.println(student);
    }
}

```

![image-20230815181836551](C:\Users\旧瘾\AppData\Roaming\Typora\typora-user-images\image-20230815181836551.png)



# BeanFactory 和 FactoryBean有什么区别



## BeanFactory

Spring IoC 容器的 顶级对象，BeanFactory被称为：Bean工厂

在IoC容器中，负责创建Bean对象

是一个工厂



## FactoryBean

FactoryBean是一个Bean对象

是一个辅助Spring实例化其他Bean对象的一个Bean

在Spring中，Bean分为两类：

- 普通Bean
- 工厂Bean（工厂Bean，也是Bean，只不过他比较特殊【可以辅助Spring实例化其他Bean对象】



