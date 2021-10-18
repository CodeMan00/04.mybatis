package com.bjpowdernode.dao.impl;

import com.bjpowdernode.dao.StudentDao;
import com.bjpowdernode.domain.Student;
import com.bjpowdernode.utils.MyBatatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

/**
 * @author gjd
 * @create 2021/10/6  18:03:36
 */
public class StudentDaoImp implements StudentDao {


    @Override
    public List<Student> selectStudents() throws IOException {

       //1.获取SqlSession对象
        SqlSession sqlSession = MyBatatisUtils.getSqlSession();

        String sqlId = "com.bjpowdernode.dao.StudentDao.selectStudents";

        //执行sql语句
        List<Student> students = sqlSession.selectList(sqlId);

        //关闭
        sqlSession.close();

        return students;
    }

    @Override
    public int insertStudent(Student student) throws IOException {

        SqlSession sqlSession = MyBatatisUtils.getSqlSession();

        String sqlId = "com.bjpowdernode.dao.StudentDao.insertStudent";

        //返回执行sql语句影响的行数
        int rows = sqlSession.insert(sqlId, student);

        //手动提交事务
        sqlSession.commit();

        //关闭
        sqlSession.close();

        return rows;
    }
}
