package com.bjpowdernode.dao;

import com.bjpowdernode.domain.Student;

import java.io.IOException;
import java.util.List;

/**
 * @author gjd
 * @create 2021/10/6  17:50:21
 */
public interface StudentDao {


    List<Student> selectStudents() throws IOException;

    int insertStudent(Student student) throws IOException;

}
