# Spring6集成MyBatis3.5

实现步骤：

- 准备数据表
- idea中创建模块，引入依赖
- 基于三层架构实现，准备好相应的包文件
- 编写pojo类
- 编写mapper接口
- 编写mapper配置文件
- 编写service接口，及其实现类
- 编写jdbc.properties配置文件
- 编写mybatis-config.xml配置文件
- 编写spring.xml配置文件
  - 组件扫描
  - 引入外部配置文件
  - SqlSessionFactoryBean配置
    - 注入Mybatis配置文件的路径
    - 指定包名
    - 注入数据源
  - Mapper扫描配置器
  - 事务管理器org.springframework.jdbc.datasource.DataSourceTransactionManager
  - 启用事务注解



## Spring主配置文件中，导入子配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/tx https://www.springframework.org/schema/tx/spring-tx.xsd">

    <!-- 导入子配置文件 -->
    <import resource="common.xml"/>

    <!-- 扫描外部文件 -->
    <context:property-placeholder location="jdbc.properties"/>
    <!-- 注册数据源 -->
    <bean id="datasource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

    <!-- 将数据源注入mybatis中 -->
    <bean class="org.mybatis.spring.SqlSessionFactoryBean">
        <!-- 加载mybatis核心配置文件 -->
        <property name="configLocation" value="mybatis-config.xml"/>
        <!-- 将数据源注入 -->
        <property name="dataSource" ref="datasource"/>
        <!-- 启动别名机制 -->
        <property name="typeAliasesPackage" value="com.powernode.bank.pojo"/>
     </bean>

    <!-- Mapper扫描器 -->
    <bean id="mapper" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.powernode.bank.mapper"/>
    </bean>

    <!-- 事务管理器 -->
    <bean id="tx" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="datasource"/>
    </bean>
    <!-- 开启事务管理 -->
    <tx:annotation-driven transaction-manager="tx"/>
</beans>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 子配置文件的包扫描模块 -->
    <context:component-scan base-package="com.powernode.bank.pojo"/>
    <context:component-scan base-package="com.powernode.bank.service.impl"/>
</beans>
```