package com.bjpowdernode;

import com.bjpowdernode.dao.StudentDao;
import com.bjpowdernode.domain.Student;
import com.bjpowdernode.utils.MyBatatisUtils;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.beans.Transient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author gjd
 * @create 2021/10/6  21:04:56
 */

public class MyTest {

    /**
     * 测试动态sql之if
     */
    @Test
    public void testSelectStudentById() throws IOException {

        SqlSession sqlSession = MyBatatisUtils.getSqlSession();

        StudentDao mapper = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("张三");
        //student.setAge(12);
        List<Student> students = mapper.selectStudentIf(student);

        students.forEach(stu-> System.out.println(stu));

        sqlSession.close();
    }

    /**
     *  测试 where标签
     */
    @Test
    public void testSelectStudentWhere() throws IOException {

        SqlSession sqlSession = MyBatatisUtils.getSqlSession();

        StudentDao mapper = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("张三");
        //student.setAge(12);
        List<Student> students = mapper.selectStudentWhere(student);

        students.forEach(stu-> System.out.println(stu));

        sqlSession.close();
    }


    /**
     * 测试foreach 标签 集合中存放的是基本数据类型
     * @throws IOException
     */
    @Test
    public void testSelectForeachOne() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        List<Student> students = mapper.selectForeachOne(list);

        students.forEach(stu-> System.out.println(stu));

        session.close();
    }

    /**
     *  测试foreach 标签  集合中存放的是对象
     */
    @Test
    public void testSelectForeachTwo() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);
        List<Student> list = new ArrayList<>();
        list.add(new Student(1));
        list.add(new Student(2));
        list.add(new Student(3));
        list.add(new Student(4));

        List<Student> students = mapper.selectForeachTwo(list);

        students.forEach(stu-> System.out.println(stu));

        session.close();
    }


    /**
     *   通过pagehelper 对查询结果进行分页
     */

    @Test
    public void testSelectAllPageHelper() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        //加入pagehelper的方法进行分页  第一个参数表示从第几页开始,不能是0 第二个参数表示每页显示几个数据
        PageHelper.startPage(1,3);

        List<Student> students = mapper.selectAll();

        students.forEach(stu-> System.out.println(stu));

        session.close();
    }

    /**
     *   测试trim标签
     */
    @Test
    public void testSelectStudentTrim() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("张三");
        student.setAge(12);
        student.setEmail("zhangsan@qq.com");
        List<Student> students = mapper.selectStudentTrim(student);
        students.forEach(stu -> System.out.println(stu));
        session.close();
    }

    /**
     *   测试choose标签
     */
    @Test
    public void testSelectStudentChoose() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        Student student = new Student();
        //student.setName("张三");
        student.setEmail("lisi@qq.com");
        List<Student> students = mapper.selectStudentChoose(student);
        students.forEach(stu -> System.out.println(stu));
        session.close();
    }


    /**
     *   测试choose标签
     */
    @Test
    public void testUpdateStudent() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        Student student = new Student();
        student.setId(2);
        //student.setName("张三");
        student.setEmail("lisi@qq.com");
        mapper.updateStudent(student);

        session.commit();
        session.close();
    }
    /**
     *   测试choose标签
     */
    @Test
    public void testAddStudent() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        List<Student> students = new ArrayList<>();
        students.add(new Student("苹果","pingguo.163.com",22));
        students.add(new Student("香蕉","xiangjiao.163.com",26));

        mapper.addStudnets(students);
        session.commit();
        session.close();
    }
}
