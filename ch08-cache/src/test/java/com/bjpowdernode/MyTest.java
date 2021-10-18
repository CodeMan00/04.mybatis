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
     *  两级缓存：
     *    一级缓存（本地缓存）：sqlSession级别的缓存，一级缓存是一直开启的。
     *             与数据库同一次会话期间查询到的数据会放在本地缓存中。
     *             以后如果需要获取相同的数据，直接从缓存中拿，没必须再去查数据库
     *
     *          一级缓存失效情况（没有使用到当前一级缓存的情况，效果就是，同一条sql语句，还需要向数据库发送查询）
     *              1.sqlSession对象不同
     *              2.sqlSession相同，但是查询条件不同（前提是 当前一级缓存中还没有这条数据）。
     *              3.sqlSession相同，两次查询之间执行了增删操改操作。（这次增删改可能会对当前缓存的数据有影响）
     *              4.sqlSession相同，手动清除了一级缓存（缓存清空）  session.clearCache();
     *
     *    二级缓存(全局缓存)： 基于nameSpace的缓存，一个nameSpace对应一个二级缓存
     *          工作机制：
     *              1.一个会话查询一条数据，这个数据就会被保存在当前会话的一级缓存中；
     *              2.如果会话关闭，一级缓存中的数据就会被保存在二级缓存中，新的会话查询信息，
     *                  就可以参照二级缓存
     *              3.sqlSession 会根据不同的mapper进行分类存储到不同的缓存中。
     *                  如果StudentDao的缓存信息存放在一起，DepartmentDao的缓存信息存放在一起。
     *                  不同的nameSpace查出的数据会放在自己对应的缓存中。
     *
     *              注意： 只有当前session关闭后，一级缓存的数据才会转移到二级缓存中，如果没有关闭，
     *                      二级缓存是没有数据的，即使是同一条sql语句，但是不同的sqlsession对象
     *                      在二级缓存中拿不到数据，就会查数据库表。1
     *
     *          使用：
     *              1.开启全局二级缓存配置，在主配置文件中通过setting标签 cacheEanabled属性开启配置，默认是开启（true）的。
     *              2.去接口的xml中，配置使用二级缓存
     *              3.我们的POJO需要实现序列化接口
     *
     *
     *          和缓存有关的设置/属性：
     *                1. cacheEnabled=true/false  开启 /关闭缓存  （关闭二级缓存，一级缓存不会关闭）
     *                2. select标签的useCache = true 属性 ，如果置为false，那么关闭的是二级缓存
     *                3 每个增删改标签，都会有flushCache属性，默认为true，增删改执行完毕后就会清除一级缓存和二级缓存。
     *                4. select标签中也有flushCache属性，默认为false，是不清除缓存的。如果置为true，那么每次查询后都会
     *                      清空缓存。
     *                5. sqlSession.clearCache(); 知识清除一级缓存，不会清除二级缓存
     *                6.localCacheScope属性：可选值：session  当前会话的所有数据保存在会话缓存中。
     *                                             statement：禁用一级缓存
     *
     *
     *      总结：
     *          当我们要使用sql语句查询数据时，先到二级缓存中去查询数据是否存在，如果存在，直接返回结果，如果不存在，则到一级
     *          缓存中去查询，如果有则返回结果，如果没有，则去数据库查询.
     *
     */

    @Test
    public void testFirstLevelCache() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        /**
         *  发出两次相同的查询语句，但是控制台上只发送了一次sql查询，那么这就验证了
         *  mybatis指请求了一次数据库，第二次是从缓存中读取数据的。
         */
        Student student = mapper.selectById(1);
        Student student1 = mapper.selectById(1);

        System.out.println(student);
        System.out.println(student1);
        System.out.println(student == student1);
    }

    /**
     *  sqlSession对象不同 进而导致一级缓存失效
     *      同一条sql语句，不同的SqlSession对象进行处理，不会利用到一级缓存，而是
     *      从数据库中进行查询。
     */
    @Test
    public void testCacheInvalid() throws IOException {
        //不同的SqlSession对象
        SqlSession session = MyBatatisUtils.getSqlSession();
        SqlSession session1 = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);
        Student student = mapper.selectById(1);

        StudentDao mapper1 = session1.getMapper(StudentDao.class);
        Student student1 = mapper1.selectById(1);

        System.out.println(student);
        System.out.println(student1);
    }
    /**
     * sqlSession相同，两次查询之间执行了增删操改操作。（这次增删改可能会对当前缓存的数据有影响）
     */
    @Test
    public void testCache3() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        Student student = new Student("英语","yingyu@qq.com",31);

        mapper.addStudent(student);

        session.commit();

        Student student1 = mapper.selectById(1);

        System.out.println(student1);
    }

    /**
     *  测试  二级缓存
     */

    @Test
    public void testSecondCache() throws IOException {

        SqlSession session = MyBatatisUtils.getSqlSession();

        SqlSession session1 = MyBatatisUtils.getSqlSession();

        StudentDao mapper = session.getMapper(StudentDao.class);

        StudentDao mapper1 = session1.getMapper(StudentDao.class);

        Student student = mapper.selectById(1);
        System.out.println(student);
        //注意：只有当前session关闭后，缓存数据才存入到二级缓存，如果没有关闭，数据是不存入二级缓存的，
        //这也是一个常见的小bug
        session.close();

        //从二级缓存中拿到数据，并没有发送新的sql
        Student student1 = mapper1.selectById(1);
        System.out.println(student1);
        session1.close();

    }
}
