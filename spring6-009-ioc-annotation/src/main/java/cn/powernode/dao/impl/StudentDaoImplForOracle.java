package cn.powernode.dao.impl;

import cn.powernode.dao.StudentDao;
import org.springframework.stereotype.Repository;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/29 20:11
 * @since 1.0
 */
@Repository
public class StudentDaoImplForOracle implements StudentDao {

    @Override
    public void deleteById() {
        System.out.println("Oracle正在删除学生数据。。。");
    }
}
