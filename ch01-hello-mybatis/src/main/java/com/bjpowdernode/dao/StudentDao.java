package com.bjpowdernode.dao;

import com.bjpowdernode.domain.Student;

import java.util.List;

/**
 * @author gjd
 * @create 2021/10/6  13:51:22
 */
//接口操作student数据库表
public interface StudentDao {

    //查询student表的所有数据
    public List<Student> selectStudent();

    //插入方法   返回值表示执行insert操作后，影响数据库的行数
    public int insert(Student student);
}
