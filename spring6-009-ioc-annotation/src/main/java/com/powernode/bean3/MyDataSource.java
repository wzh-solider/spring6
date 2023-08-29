package com.powernode.bean3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/28 21:10
 * @since 1.0
 */
@Component
public class MyDataSource implements DataSource {

    @Value("com.mysql.jc.jdbc.Driver")
    private String driver;

    @Value("jdbc:mysql://localhost:3306/spring6")
    private String url;

    @Value("root")
    private String username;

    @Autowired
    @Value("123456")
    private String password;

    // 当使用注解方式进行属性注入时，不需要提供set方法
    // public void setDriver(String driver) {
    //     this.driver = driver;
    // }
    //
    // public void setUrl(String url) {
    //     this.url = url;
    // }
    //
    // public void setUsername(String username) {
    //     this.username = username;
    // }
    //
    // public void setPassword(String password) {
    //     this.password = password;
    // }

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public String toString() {
        return "MyDataSource{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
