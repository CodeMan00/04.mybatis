package com.bjpowdernode.dao;

import com.bjpowdernode.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gjd
 * @create 2021/10/6  17:50:21
 */
public interface StudentDao {

     Student selectById(Integer id);

     void addStudent(Student student);
}
