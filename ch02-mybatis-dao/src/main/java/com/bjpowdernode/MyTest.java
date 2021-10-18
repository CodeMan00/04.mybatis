package com.bjpowdernode;

import com.bjpowdernode.dao.StudentDao;
import com.bjpowdernode.dao.impl.StudentDaoImp;
import com.bjpowdernode.domain.Student;
import com.bjpowdernode.utils.MyBatatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author gjd
 * @create 2021/10/6  18:08:08
 */
public class MyTest {

    @Test
    public void test() throws IOException {


        StudentDaoImp studentDaoImp = new StudentDaoImp();
        List<Student> students = studentDaoImp.selectStudents();

        students.forEach(stu -> System.out.println(stu));
    }

    @Test
    public void testInsertStudent() throws IOException {

        StudentDao dao = new StudentDaoImp();

        Student student = new Student();

        student.setId(5);
        student.setName("tianyi");
        student.setEmail("tianyi@qq.com");
        student.setAge(32);

        int rows = dao.insertStudent(student);

        System.out.println(rows > 0 ? "插入成功！" : "插入失败！");
    }


}
