#基础
##三大特点
* 不屏蔽SQL
这意味着更加精确的定位SQL语句,可以对其进行优化和改造,这有利于互联网系统的提高,符合互联网应用的性能优化特点.
* 强大灵活的映射机制
提供动态SQL功能,允许根据不同条件组装SQL,这个功能远比其他工具或者 Java 编码的可读性和可维护性高的多,满足了各种应用系统的同时也满足了互联网系统的高性能要求.
* Mapper接口编程
只要一个接口和一个XML就能创建映射器,进一步简化我们的工作,使得很多框架API在MyBatis中消失,开发者能更集中于业务逻辑中.
##准备环境jar
jar包下载<a href="https://github.com/mybatis/mybatis-3/releases">https://github.com/mybatis/mybatis-3/releases</a>
参考手册<a href="http://www.mybatis.org/mybatis-3/zh/getting-started.html">http://www.mybatis.org/mybatis-3/zh/getting-started.html</a>
##MyBatis 核心组件
* `SqlSessionFactoryBuilder` ( 构造器 ) : 他会根据配置或者代码生成SqlSessionFactory,采用的是分步构建的Builder模式.
* `SQLSessionFactory` ( 工厂接口 ) : 依靠它来生成SqlSession,使用的是工程模式 .
* `SQLSession` ( 会话 ) : 一个既可以发送SQL执行返回结果,也可以获取 `Mapper` 的接口 . 在现有的技术中 , 一般我们会让其在业务逻辑代码中 " 消失 " ,而使用的是 MyBatis 提供的SQL Mapper接口编程技术 , 他能提高代码的可读性和可维护性 . 
* `SQL Mapper` ( 映射器 ) : MyBatis 新设计存在的组件 , 他由一个Java接口和XML文件(或注解)构成,需要给出对应的SQL和映射规则 . 他负责发送SQL去执行 , 并返回结果 . 
* 如图所示 : 
![2018070919283596.jpg](https://upload-images.jianshu.io/upload_images/13055171-b2670fad212e508b.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![20180717093634217.png](https://upload-images.jianshu.io/upload_images/13055171-797a9ebf1619a112.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### SqlSessionFactory ( 工厂接口 )
* 使用MyBatis 首先要使用配置或者代码去生产 `SqlSessionFactory` , 而MyBatis提供了构造器 `SqlSessionFactoryBuilder` . 它提供了一个类 ` org.apache.ibatis.session.Configuration` 作为引导 , 它采用的是Builder模式. 
![20180709194540139.jpg](https://upload-images.jianshu.io/upload_images/13055171-8d744e3cab775a57.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


--------------------------------
##MyBatis 工作流程
![20180722101016736.png](https://upload-images.jianshu.io/upload_images/13055171-b43ea03768610aa4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


----------------------------------
 
