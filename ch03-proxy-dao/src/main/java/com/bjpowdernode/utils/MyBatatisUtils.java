package com.bjpowdernode.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author gjd
 * @create 2021/10/6  16:15:57
 */
public class MyBatatisUtils {

    private static SqlSessionFactory factory;

    static {
        //指定主配置文件路径
        String config = "mybatis.xml";

        InputStream resource = null;
        try {
            resource = Resources.getResourceAsStream(config);

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            factory = builder.build(resource);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession() throws IOException {

        SqlSession session = null;

        if(factory!=null) {
            session = factory.openSession();
        }

        return session;
    }
}
