package com.bjpowdernode.dao;

import com.bjpowdernode.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gjd
 * @create 2021/10/6  17:50:21
 */
public interface StudentDao {


     //动态sql ,使用java对象作为参数
     List<Student> selectStudentIf(Student student);

     //where标签的使用
     List<Student> selectStudentWhere(Student student);

     //foreach标签用法一   集合中是基本数据类型
     List<Student> selectForeachOne(List<Integer> idList);

     //foreach标签用法二   集合中是对象
     List<Student> selectForeachTwo(List<Student> idList);

     //使用pageHelper分页数据
     List<Student> selectAll();

     //trim标签的使用
     List<Student> selectStudentTrim (Student student);

     //测试choose标签的使用
     List<Student> selectStudentChoose(Student student);

     //set标签与if标签结合的使用
     public void updateStudent(Student student);

     //forEach标签批量保存
     public void addStudnets(@Param("students") List<Student> students);
}
