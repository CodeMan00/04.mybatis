package com.bjpowdernode;

import com.bjpowdernode.dao.StudentDao;
import com.bjpowdernode.domain.Student;
import com.bjpowdernode.utils.MyBatatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author gjd
 * @create 2021/10/6  21:04:56
 */
public class MyTest {

    /**
     *  mybatis的动态代理机制，使用SqlSession.getMapper(dao接口)
     *  getMapper方法能获取到接口对应的实现类对象（也就是mybatis底层创建一个我们定义的接口的实现类，
     *  我们无需自己创建一个新的实现类。）
     */
    @Test
    public void test() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        //使用的是jdk的动态代理创建的对象
        StudentDao mapper = session.getMapper(StudentDao.class);
        System.out.println(mapper);

        List<Student> students = mapper.selectStudents();

        students.forEach(stu -> System.out.println(stu));

        session.close();
    }

    @Test
    public void testInsert() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        Student student = new Student();
        student.setId(7);
        student.setName("关羽");
        student.setEmail("guanyu@qq.com");
        student.setAge(32);

        int rows = mapper.insertStudent(student);

        //提交事务
        session.commit();

        session.close();
    }

    /**
     * 测试 修改学生信息
     */
    @Test
    public void testUpdateStudent() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        Student student = new Student();
        student.setId(6);
        student.setName("离线");
        student.setEmail("lixian@qq.com");
        student.setAge(12);
        mapper.updateStudent(student);

        //需要手动提交事务
        session.commit();

        session.close();
    }

    /**
     * 测试 删除学生信息
     */
    @Test
    public void testDeleteStudent() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        mapper.deleteStudent(5);

        //需要手动提交事务
        session.commit();

        session.close();
    }

    /***
     *    测试插入操作，主键无需赋值，是自增的。
     */
    @Test
    public void testinsertGene() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("刘德华");
        student.setEmail("liudehua@qq.com");
        student.setAge(51);
        int i = mapper.insertGene(student);

        System.out.println(i > 0 ? "插入成功！":"插入失败！");
        System.out.println(student.getId());
        //提交事务
        session.commit();
        session.close();
    }
}
