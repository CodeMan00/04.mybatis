package com.bjpowdernode.dao;

import com.bjpowdernode.domain.MyStudent;
import com.bjpowdernode.domain.Student;
import com.bjpowdernode.vo.QueryParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.logging.stdout.StdOutImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author gjd
 * @create 2021/10/6  17:50:21
 */
public interface StudentDao {


     Student selectStudentById(Integer sid);

     List<Student> selectMultiParam(@Param("myname")String name,@Param("myage") Integer age);

     int countStudent();

     //结果返回类型为map
     Map<Object,Object> selectMapById(Integer id);


     //通过resultMap定义映射关系
     List<Student> selectAllStudents();

     List<MyStudent> selectAllMyStudents();


     //第一种模糊查询,在java代码中,指定like的内容
     List<Student> selectLikeOne(String name);

     //第二种模糊查询 在mapper种拼接 like "%" 李 "%"
     List<Student> selectLikeTwo(String name);

     Student selectStudentAndDepartment(Integer id);

     Student selectStudentAndDepartment2(Integer id);

     //通过部门号查询该部门的所有学生信息
     List<Student> getStudentsByDeptId(Integer depid);


     //测试discriminator标签
     Student getStudnetByIdStep(String name);
}
