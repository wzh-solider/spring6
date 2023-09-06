package com.powernode.dao.impl;

import com.powernode.dao.AccountDao;
import com.powernode.pojo.Account;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/6 10:57
 * @since 1.0
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account selectActNo(String actNo) {
        String sql = "select * from t_act where actno = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), actNo);
    }

    @Override
    public int updateAct(Account account) {
        String sql = "update t_act set balance = ? where actno = ?";
        return jdbcTemplate.update(sql, account.getBalance(), account.getActNo());
    }

    @Override
    public int insert(Account account) {
        String sql = "insert into t_act(actno, balance) values(?, ?)";
        return jdbcTemplate.update(sql, account.getActNo(), account.getBalance());
    }
}
