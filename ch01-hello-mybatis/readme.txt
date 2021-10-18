mybatis入门案例：


实现步骤：
1.新建student数据库表
2.加入maven的mybatis坐标，mysql驱动坐标
3.创建实体类，Student，保存表中的数据
4.创建持久层的dao接口，定义操作数据库的方法
5.创建一个mybtais使用的配置文件，叫做sql映射文件：写sql语句的，
    一般一个表一个映射文件，这个文件是xml格式的。
  文件的位置；
        在dao接口所在的目录中，文件的名称和接口保持一致。

6.创建mybatis主配置文件，一个项目中就一个配置文件，主配置文件提供了连接数据库
    的连接信息和sql映射文件的位置信息。
7.创建使用mybatis类，通过mybatis访问数据库。



解决idea可能由于缓存带来的一些问题的方案：
1.通过maven中的clean  然后在选择compile
2.通过菜单的Build 菜单栏下的rebuild project，重新构建一下项目
3.通过File菜单，选择Invalidate Caches/Restart  重启一下

