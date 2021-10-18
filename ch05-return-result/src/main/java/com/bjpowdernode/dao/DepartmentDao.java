package com.bjpowdernode.dao;

import com.bjpowdernode.domain.Department;

/**
 * @author gjd
 * @create 2021/10/8  18:30:02
 */
public interface DepartmentDao {

     Department selectDepartmentById(Integer id);

     //在查询部门信息的时候，把该部门下的所有员工的信息也查询出来
     Department getDepartmentByIdPlus(Integer id);

     //分部查询  先查询部门信息，然后根据部门信息进行查询属于该部门的所有学生的信息  这是分部的，如果
     //我们不想查询学生信息，那么就不会再去数据库进行查询了。
     Department getDepartmentByIdStep(Integer id);
}
