#一、整合测试环境
开发工具：IntelliJ IDEA 2018.2.3 x64
springboot: 2.0.5
jdk：1.8
maven:4.0.0、
其他功能
PageHelper 分页插件
mybatis generator 自动生成代码插件
#二、创建工程
##1.启动器
* 使用开工具的 -  Spring Initializr  快速创建
  * 启动器
    1. Web
        * Web
    2. SQL 
        * MySQL
        * JDBC
        * MyBatis
##2.pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.luke</groupId>
    <artifactId>springboot-mybatis</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>springboot-mybatis</name>
    <description>Demo project for Spring Boot</description>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.5.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- druid 数据源 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.10</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>net.minidev</groupId>
            <artifactId>json-smart</artifactId>
            <version>2.2.1</version>
        </dependency>
        <!--pagehelper必须这个版本 其他版本与以上配置不兼容-->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.6</version>
        </dependency>

    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <!-- mybatis generator 自动生成代码插件 -->
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.2</version>
                <configuration>
                    <configurationFile>${basedir}/src/main/resources/generator/generatorConfig.xml</configurationFile>
                    <overwrite>true</overwrite>
                    <verbose>true</verbose>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```
#三、全局配置
##1.文件目录
![文件目录](https://upload-images.jianshu.io/upload_images/13055171-e47eb91c8ecf4b6e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##2.application.yml
```yml
spring:
  datasource:
    username: root
    password: root
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/test
    # 使用druid数据源
    type: com.alibaba.druid.pool.DruidDataSource
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true
    # 本版本的SpringBoot只支持log4j2（否则会报错）
    filters: stat,wall,log4j2
    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
## 该配置节点为独立的节点，有很多同学容易将这个配置放在spring的节点下，导致配置无法被识别
mybatis:
  # 指定全局配置文件位置
  config-location: classpath:mybatis/mybatis-config.xml
  # 指定sql映射文件位置
  mapper-locations: classpath:mybatis/mapper/*.xml
  # 别名配置
  type-aliases-package: com.luke.springbootmybatis.pojo
#pagehelper分页插件
pagehelper:
    helperDialect: mysql
    reasonable: true
    supportMethodsArguments: true
    params: count=countSql
```
四、创建数据库
```sql
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `gender` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin
```
#五、使用mybatis generator 生成文件
##1. 添加Generator工具
![](https://upload-images.jianshu.io/upload_images/13055171-8d2bb136d17bc8a9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](https://upload-images.jianshu.io/upload_images/13055171-9555dc191f9382ae.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##2.配置生成条件`generatorConfig.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <!-- 数据库驱动:选择你的本地硬盘上面的数据库驱动包-->
    <classPathEntry location="C:\Users\v_huidongqu\.m2\mybaitsPlusRes\mysql\mysql-connector-java\5.1.47\mysql-connector-java-5.1.47.jar"/>
    <context id="DB2Tables" targetRuntime="MyBatis3">
        <commentGenerator>
            <property name="suppressDate" value="true"/>
            <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>
        <!--数据库链接URL，用户名、密码 -->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://127.0.0.1/test"
                        userId="root"
                        password="root">
        </jdbcConnection>
        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>
        <!-- 生成模型的包名和位置-->
        <javaModelGenerator targetPackage="com.luke.springbootmybatis.pojo" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>
        <!-- 生成映射文件的包名和位置-->
        <sqlMapGenerator targetPackage="mapper" targetProject="src/main/resources/mybatis">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>
        <!-- 生成DAO的包名和位置-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.luke.springbootmybatis.mapper"
                             targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>
        <!-- 要生成的表 tableName是数据库中的表名或视图名 domainObjectName是实体类名-->
        <table tableName="person" domainObjectName="Person" enableCountByExample="false" enableUpdateByExample="false"
               enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false"></table>
    </context>
</generatorConfiguration>
```
##3.生成
![微信截图_20180915172838.png](https://upload-images.jianshu.io/upload_images/13055171-4beb31c472064e95.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##4.生成的文件
###Person.java
```java
package com.luke.springbootmybatis.pojo;

public class Person {
    private Integer id;

    private String name;

    private String gender;

    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}
```
###PersonMapper.java
```java
package com.luke.springbootmybatis.mapper;

import com.luke.springbootmybatis.pojo.Person;
import org.apache.ibatis.annotations.Mapper;


public interface PersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}
```
###PersonMapper.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- 省略.... -->
```
> 修改屎黄色的SQL
    ![](https://upload-images.jianshu.io/upload_images/13055171-3bce82594d213790.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
#六、将项目中对应的mapper类的路径加进来就可以了
```java
package com.luke.springbootmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.luke.springbootmybatis.mapper")
public class SpringbootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisApplication.class, args);
    }
}

```
#七、编写一个简单的测试
```java
@RestController
public class PersonController {
    @Autowired
    private PersonMapper personMapper;
    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable("id") Integer id){
        Person person = personMapper.selectByPrimaryKey(id);
        return person;
    }
    @GetMapping("/person")
    public Person postPerson(Person person){
        personMapper.insert(person);
        return person;
    }

    public PersonController() {
        System.out.println("PersonController === 构造器");
    }
}

```







    
