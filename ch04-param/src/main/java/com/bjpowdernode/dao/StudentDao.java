package com.bjpowdernode.dao;

import com.bjpowdernode.domain.Student;
import com.bjpowdernode.vo.QueryParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.logging.stdout.StdOutImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author gjd
 * @create 2021/10/6  17:50:21
 */
public interface StudentDao {

    /**
     * 传入一个简单类型的参数
     *    简单参数：mybatis把java的基本数据类型和其包装类和String类型称为简单类型。
     *
     *
     *  在mapper文件获取简单类型的一个参数值看，使用 #{任意字符}
     */
    public Student selectStudentById(Integer sid);


    /**
     * 传入多个参数：
     *    命名参数，在形参定义的前面加入@Param("自定义参数名称")
     *
     *    注解中的名称要和 xml 文件的mapper标签内的 sql语句中的 {} 内的名称要保持一致。
     */
    public List<Student> selectMultiParam(@Param("myname")String name,@Param("myage") Integer age);


    /**
     * 我们定义一个对象把我们需要的数据封装到对象中
     */
    public Student selectMutiObject(QueryParam params);


    /**
     * 方式三：多个参数(简单类型)，通过位置传值
     */
    public Student selectMultiPosition(String name,Integer age);


    /**
     * 方式四：多个参数，通过map进行传值
     *
     * 因为数据封装到map中，对于参数的类型及其个数，我们不能明显的得知，可读性比较差。
     */
    Student selectMultiMap(Map<String,Object> map);


    /**
     *  通过 ${} 来获取参数
     */
    Student selectStudentBy$(@Param("name") String name);
}
