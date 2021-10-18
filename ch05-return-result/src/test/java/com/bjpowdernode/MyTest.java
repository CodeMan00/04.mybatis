package com.bjpowdernode;

import com.bjpowdernode.dao.DepartmentDao;
import com.bjpowdernode.dao.StudentDao;
import com.bjpowdernode.domain.Department;
import com.bjpowdernode.domain.MyStudent;
import com.bjpowdernode.domain.Student;
import com.bjpowdernode.utils.MyBatatisUtils;
import com.bjpowdernode.vo.QueryParam;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gjd
 * @create 2021/10/6  21:04:56
 */
public class MyTest {


    @Test
    public void testSelectStudentById() throws IOException {

        SqlSession sqlSession = MyBatatisUtils.getSqlSession();

        StudentDao mapper = sqlSession.getMapper(StudentDao.class);

        Student student = mapper.selectStudentById(1);

        System.out.println(student);
    }

    /**
     * 传递多个参数进行查询
     */
    @Test
    public void testSelectMultiParam() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        List<Student> students = mapper.selectMultiParam("张三", 23);

        students.forEach(stu -> System.out.println(stu));

        session.close();
    }


    /**
     * 返回值是基本数据类型
     */
    @Test
    public void testCountStudnet() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        int i = mapper.countStudent();

        System.out.println(i);

        session.close();

    }


    /**
     * 返回值为map
     */

    @Test
    public void testSelectMapById() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        Map<Object, Object> map = mapper.selectMapById(1);

        System.out.println(map);

        session.close();
    }

    /**
     *
     *  定义resultMap完成映射关系
     */
    @Test
    public void testSelectAllStudents() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        List<Student> students = mapper.selectAllStudents();

        students.forEach(stu-> System.out.println(stu));

        session.close();
    }

    /**
     *  通过给数据库表中的列 起别名 完成映射关系
     */
    @Test
    public void testSelectAllMyStudents() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        List<MyStudent> students = mapper.selectAllMyStudents();

        students.forEach(stu-> System.out.println(stu));

        session.close();
    }


    /**
     * like 进行模糊查询
     */
    @Test
    public void testSelectLikeOne() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        List<Student> students = mapper.selectLikeOne("李%");

        students.forEach(stu-> System.out.println(stu));

        session.close();
    }


    /**
     * like 进行模糊查询方式二
     */
    @Test
    public void testSelectLikeTwo() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        List<Student> students = mapper.selectLikeTwo("李");

        students.forEach(stu-> System.out.println(stu));

        session.close();
    }


    /**
     *  测试 resultMap 完成映射
     */
    @Test
    public void testSelectStudentAndDepartment() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        Student student = mapper.selectStudentAndDepartment(1);

        System.out.println(student);

        session.close();
    }


    /**
     *  测试 resultMap 完成映射
     */
    @Test
    public void testSelectStudentAndDepartment2() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        Student student = mapper.selectStudentAndDepartment2(4);

        System.out.println(student);

        session.close();
    }


    /**
     *  测试 懒加载
     *    这个测试所需的表是在jpa_test数据中的，这是一个我找了很久的bug，代码没错，就是没用对库
     */
    @Test
    public void testSelectStudentAndDepartment3() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        Student student = mapper.selectStudentAndDepartment2(4);

        //此时我们只需要学生的姓名，那么就不会进行查询他的部门信息，可以通过控制台的sql语句，进行查看是否有查询部门的sql语句。
        //如果没有开启懒加载，就算是只获取学生的姓名，也会把学生关联的部门的信息从数据库查询出来，这样效率会低。
        System.out.println(student.getName());
        //如果我们需要部门信息，它才会到数据库中进行部门信息查询
        System.out.println(student.getDepartment());

        session.close();
    }

    /**
     *  测试 一对多的情况   查询部门信息，并且查询出该部门中的所有学生信息
     */
    @Test
    public void testGetDepartmentByIdPlus() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        DepartmentDao mapper = session.getMapper(DepartmentDao.class);

        Department department = mapper.getDepartmentByIdPlus(1);

        System.out.println(department);
    }


    /**
     *  测试 一对多的情况   分部查询部门信息，并且查询出该部门中的所有学生信息
     */
    @Test
    public void testGetDepartmentByIdStep() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        DepartmentDao mapper = session.getMapper(DepartmentDao.class);

        Department department = mapper.getDepartmentByIdStep(1);

        System.out.println(department.getName());
    }


    //测试 discriminator 对应不同的列值，我们可以定义不同的封装方式
    @Test
    public void testGetStudnetByIdStep() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

//        Student studnet = mapper.getStudnetByIdStep("张三");
        Student studnet = mapper.getStudnetByIdStep("李四");

        System.out.println(studnet);
    }
}
