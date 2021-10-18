package com.bjpowdernode;

import com.bjpowdernode.domain.Student;
import com.bjpowdernode.utils.MyBatatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

/**
 * @author gjd
 * @create 2021/10/6  17:35:50
 */
public class MyTest02 {

    /**
     *   把重复的代码抽取出来，定义一个工具类，简化了代码。
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        SqlSession sqlSession = MyBatatisUtils.getSqlSession();

        String sqlId = "com.bjpowdernode.dao.StudentDao.selectStudents";
        List<Student> list = sqlSession.selectList(sqlId);

        list.forEach(stu-> System.out.println(stu));

        //资源关闭
        sqlSession.close();
    }
}
