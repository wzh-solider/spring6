package com.powernode.jdbc;

import com.powernode.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/29 23:15
 * @since 1.0
 */
public class Spring6JDBCTest {

    @Test
    public void testCallback() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

        // 书写jdbc代码，Spring提供了回调函数
        String sql = "select * from t_user where id = ?";
        User user = jdbcTemplate.execute(sql, new PreparedStatementCallback<User>() {
            @Override
            public User doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                User user = null;
                // 这里执行JDBC代码
                ps.setInt(1, 1);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String realName = resultSet.getString("real_name");
                    int age = resultSet.getInt("age");
                    user = new User(id, realName, age);
                }
                return user;
            }
        });
        System.out.println(user);
    }

    @Test
    public void testDeleteBatch() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

        String sql = "delete from t_user where id = ?";
        Object[] obj1 = {2};
        Object[] obj2 = {3};
        Object[] obj3 = {4};
        Object[] obj4 = {5};
        List<Object[]> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        list.add(obj4);
        int[] count = jdbcTemplate.batchUpdate(sql, list);
        if (count.length >= list.size()) {
            System.out.println("批量删除成功");
        } else {
            System.out.println("批量删除失败");
        }
    }

    @Test
    public void testUpdateBatch() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

        String sql = "update t_user set real_name = ? where id = ?";
        Object[] obj1 = {"卢本伟", 2};
        Object[] obj2 = {"卢本伟", 3};
        Object[] obj3 = {"卢本伟", 4};
        Object[] obj4 = {"卢本伟", 5};
        List<Object[]> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        list.add(obj4);
        int[] count = jdbcTemplate.batchUpdate(sql, list);
        if (count.length >= list.size()) {
            System.out.println("批量更新成功");
        } else {
            System.out.println("批量更新失败");
        }
    }

    @Test
    public void testAddBatch() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

        String sql = "insert into t_user (real_name, age) values(?, ?)";
        Object[] obj1 = {"bach", 20};
        Object[] obj2 = {"jack", 30};
        Object[] obj3 = {"mary", 40};
        List<Object[]> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        int[] ints = jdbcTemplate.batchUpdate(sql, list);
        if (ints.length >= list.size()) {
            System.out.println("批量插入成功");
        } else {
            System.out.println("批量插入失败");
        }
    }

    @Test
    public void testSelectOneValue() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

        String sql = "select count(*) from t_user";
        Integer total = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("总记录条数：" + total);
    }

    @Test
    public void testSelectAll() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

        String sql = "select * from t_user";
        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class))
                .forEach(System.out::println);
    }

    @Test
    public void testSelectSingleton() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

        String sql = "select id, real_name, age from t_user where id = ?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), 1);
        System.out.println(user);
    }

    @Test
    public void testDelete() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

        String sql = "delete from t_user where id = ?";
        int count = jdbcTemplate.update(sql, 3);
        if (count > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    @Test
    public void testUpdate() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

        String sql = "update t_user set age = ? where id = ?";
        int count = jdbcTemplate.update(sql, 50, 2);
        if (count > 0) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败");
        }
    }

    @Test
    public void testInsert() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

        String sql = "insert into t_user values(?, ?, ?)";
        int count = jdbcTemplate.update(sql, 3, "王五", 16);
        if (count > 0) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
    }

    @Test
    public void testJDBC() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        System.out.println(jdbcTemplate);
    }
}
