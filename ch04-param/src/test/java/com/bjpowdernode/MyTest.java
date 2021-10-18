package com.bjpowdernode;

import com.bjpowdernode.dao.StudentDao;
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

    /**
     *  mybatis的动态代理机制，使用SqlSession.getMapper(dao接口)
     *  getMapper方法能获取到接口对应的实现类对象（也就是mybatis底层创建一个我们定义的接口的实现类，
     *  我们无需自己创建一个新的实现类。）
     */
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
     * 通过把数据封装到自定义对象中，然后在进行参数传递
     */
    @Test
    public void testSelectMutiObject() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        QueryParam param = new QueryParam();
        param.setParamName("李四");
        param.setParamAge(33);

        Student student = mapper.selectMutiObject(param);
        System.out.println(student);

        session.close();
    }


    /**
     *   通过参数的位置  进行多个参数的传递
     */
    @Test
    public void testSelectMultiPosition() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        Student student = mapper.selectMultiPosition("李四", 33);

        System.out.println(student);

        session.close();
    }


    /**
     * 通过map 对多个参数进行传值
     */
    @Test
    public void testSelectMultiMap() throws IOException {

        SqlSession sqlSession = MyBatatisUtils.getSqlSession();

        StudentDao mapper = sqlSession.getMapper(StudentDao.class);

        Map map = new HashMap<String,Object>();

        map.put("name","张三");
        map.put("age",23);

        Student student = mapper.selectMultiMap(map);

        System.out.println(student);

        sqlSession.close();
    }

    /**
     * 通过 ${} 来获取参数
     */
    @Test
    public void testselectStudentBy$() throws IOException {

        SqlSession sqlSession = MyBatatisUtils.getSqlSession();

        StudentDao mapper = sqlSession.getMapper(StudentDao.class);

        //注意：必须加上''，$是进行拼接字符串的形式进行查询的，在sql语句中，字符串要加上单引号。
        //这种方法不能防止sql注入。
        Student student = mapper.selectStudentBy$("'李四'");
        System.out.println(student);

        sqlSession.close();
    }
}
