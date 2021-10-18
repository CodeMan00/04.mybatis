package com.bjpowdernode;

import com.bjpowdernode.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author gjd
 * @create 2021/10/6  14:30:15
 */
public class MyTest {

    private static SqlSession session;

    //初始化操作
     private static void common() throws IOException {
        //访问mybatis读取student数据
        //1.定义mybatis主配置文件的路径，从类路径下开始
        String config = "mybatis.xml";

        //2.读取配置文件
        InputStream is = Resources.getResourceAsStream(config);

        //3.创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        //4.创建SqlSessionFactory对象
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(is);

        //5.获取SqlSession对象，从SqlSessionFactory中获取对象SqlSession
        session = build.openSession(true); //参数true表示自动提交事务
    }

    //关闭资源操作
    private static void closeRes(SqlSession session){

        if(session!=null)
            session.close();
    }


    //查询所有学生对象

    public static void test1() throws IOException {

         //初始化操作
         common();


        //6.指定要执行的sql语句的标识，sql映射文件中的nameSpace + . + 标签的id
        String sqlId = "com.bjpowdernode.dao.StudentDao" + "." +"selectStudents";

        //7.执行sql语句
        List<Student> list = session.selectList(sqlId);

        //8.输出结果
        list.forEach(stu -> System.out.println(stu));

        //9.关闭SqlSesion对象
        closeRes(session);
    }


    //插入学生对象

    public static void test2() throws IOException {

         //初始化操作
         common();

        String sql = "com.bjpowdernode.dao.StudentDao.insert";

        Student student = new Student();
        student.setId(6);
        student.setName("haha");
        student.setEmail("123");
        student.setAge(32);

        if (session != null) {
            int rows = session.insert(sql,student);
            System.out.println(rows > 0 ? "执行成功！" : "执行失败！");
        }
        //mybatis默认是不提交事务的，所以在insert、update、delete后需要手动提交事务
       // session.commit();

        //关闭资源
        closeRes(session);
    }


    public static void main(String[] args) throws IOException {

         test1();
    }
}
