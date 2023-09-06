# Spring对事务的支持

> 什么是事务？
>
> - 在一个业务流程中，通常需要使用多条DML语句联合才能成功，这多条DML语句必须同时成功，或者同时失败，*才能保持数据的安全*
> - 事务就是：多条DML语句，同时成功，同时失败



事务的四个处理过程：

- 开启事务
- 执行业务代码
- 提交业务
- 回滚事务


**事务的四个特性：**

- 原子性（A）
- 一致性（C）
- 隔离性（I）
- 持久性（D）



## Spring对事务的两种支持方式

- 编程式事务（基本不使用）
  -  通过编写代码的方式来实现事务的管理
- 声明式方式
  - 基于注解的方式
  - 基于XML配置的方式



`PlatformTransactionManager`接口：`spring`事务管理器的核心接口，在**Spring6**中有两个实现：

- `DataSourceTransactionManager`：支持JdbcTemplate、MyBatis、Hibernate等事务管理
- `JtaTransactionManager`：支持分布式事务管理

【如果在spring中需要使用JdbcTemplate，需要使用`DataSourceTransactionManager`来管理事务】



### 声明式事务之注解方式实现

1. spring配置文件中配置事务管理器

   ```xml
   <!-- 配置spring事务管理器 -->
       <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
           <property name="dataSource" ref="druid"/>
       </bean>
   ```

2. spring配置文件中引入tx命名空间

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:tx="http://www.springframework.org/schema/tx"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                              http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
                              http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">
   ```

3. spring配置文件中配置“事件注解驱动器”，通过注解的方式控制事务

   ```xml
   <!-- 配置事件注解驱动器 -->
       <tx:annotation-driven transaction-manager="transactionManager"/>
   ```

4. 在service类，或方法上，添加`@Transcational`注解



### @Transcational

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springframework.transaction.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.aot.hint.annotation.Reflective;
import org.springframework.core.annotation.AliasFor;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Reflective
public @interface Transactional {
    @AliasFor("transactionManager")
    String value() default "";

    @AliasFor("value")
    String transactionManager() default "";

    String[] label() default {};

    // 事务的传播行为
    Propagation propagation() default Propagation.REQUIRED;

    // 事务之间的隔离级别
    Isolation isolation() default Isolation.DEFAULT;

    // 事务的超时时间，默认不限时
    int timeout() default -1;

    String timeoutString() default "";

    // 当前事务的性质，默认为只读
    boolean readOnly() default false;

    // 设置遇到什么异常的时候，才回滚事务
    Class<? extends Throwable>[] rollbackFor() default {};

    // 设置遇到什么异常的时候，不会回滚事务
    String[] rollbackForClassName() default {};

    Class<? extends Throwable>[] noRollbackFor() default {};

    String[] noRollbackForClassName() default {};
}
```



#### 事务的传播行为

> 什么是事务的传播行为？
>
> 在Service中有a()方法 和 b()方法，且两个方法上都存在事务，
>
> 当a()方法中含有b()方法，那么事务是如何传递？
>
> 合并？还是开启新的事务？
>
> 这就是事务的传播行为

事务的传播行为在Spring框架中被定义为枚举类型：

```java
package org.springframework.transaction.annotation;

public enum Propagation {
    REQUIRED(0),
    SUPPORTS(1),
    MANDATORY(2),
    REQUIRES_NEW(3),
    NOT_SUPPORTED(4),
    NEVER(5),
    NESTED(6);

    private final int value;

    private Propagation(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
```

一共包含七种事务的传播：

- `REQUIRED`：支持当前事务，如果不存在就新建一个【**没有就新建，有就加入**】
- `SUPPORTS`：支持当前事务，如果当前没有事务，就以非事务的方式执行【**有就加入，没有就不管**】
- `MANDATORY`：必须运行在一个事务中，如果当前没有事务正在发生，就抛出异常【**有就加入，没有就抛异常**】
- `REQUIRES_NEW`：开启一个新事务，如果事务已经存在，则将这个事务挂起【**不管有没有，直接开启新事务，开启的新事务和之前的事务不存在嵌套关系，之前的事务被挂起**】
- `NOT_SUPPORTED`：以非事务的方式运行，如果有事务存在，挂起当前事务【**不支持事务，存在就挂起**】
- `NEVER`：以非事务的方式运行，如果有事务存在，抛出异常【**不支持事务，存在就抛异常**】
- `NESTED`：如果当前正在有一个事务进行中，则该方法应当运行在一个嵌套式事务中。被嵌套的事务可以独立于外层事务进行提交或回滚。如果外部事务不存在，行为和`REQUIRED`一样【**有事务的话，就在这个事务中嵌套另一个独立的事务，可以独立提交和回滚。没有事务和`REQUIRED`一样**】

```java
// 设置传播行为
@Transactional(propagation = Propagation.REQUIRED)
```



#### 事务的隔离级别

数据库中读取数据的三大问题：

- **脏读：读取到没有提交到数据库的数据**
- **不可重复读：在同一个事务中，相邻两次读取到的数据不同**
- **幻读：读取到的数据是假的**



事务的隔离级别分为四个：

- 读未提交：`READ_UNCOMMITTED`
  - 这种隔离级别，存在脏读的现象
- 读已提交：`READ_COMMITTED`
  - 解决了脏读问题，但存在不可重复读的问题
- 可重复读：`REPEATABLE_READ`
  - 解决了脏读问题，并且达到可重复读效果，但是存在幻读问题
- 序列化：`SERIALIZABLE`
  - 解决了幻读问题，事务排队执行，不支持并发



```java
// 设置隔离级别
@Transactional(isolation = Isolation.READ_COMMITTED)
```



#### 事务超时

```java
// 设置超时时间
@Transactional(timeout = 10)
```

