package cn.powernode.service.impl;

import cn.powernode.dao.StudentDao;
import cn.powernode.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/29 20:04
 * @since 1.0
 */
@Service
public class StudentServiceImpl implements StudentService {

    // // 可以放到属性上，并按名称注入
    // @Resource(name = "studentDaoImplForOracle")
    private StudentDao studentDao;

    // 可以放到set方法上，并按名称注入
    @Resource(name = "studentDaoImplForMySQL")
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void delete() {
        studentDao.deleteById();
    }
}
