package com.powernode.bank.mapper;

import com.powernode.bank.pojo.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/10 21:24
 * @since 1.0
 */
public interface AccountMapper {
    /**
     * 增
     */
    @Insert("insert into t_act(actno, balance) values (#{actNo}, #{balance})")
    int insertAct(Account account);

    /**
     * 查询
     */
    @Select("select * from t_act where actno = #{actNo}")
    Account selectById(@Param("actNo") String actNo);

    /**
     * 修改
     */
    @Update("update t_act set balance = #{balance} where actno = #{actNo}")
    int update(Account account);
}
