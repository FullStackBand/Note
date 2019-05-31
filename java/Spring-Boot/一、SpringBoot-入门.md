#1、Spring Boot 简介 
> * 简化Spring应用开发的一个框剪 
> * 整个Spring技术栈的一个大整合；
> * J2EE开发一站式解决方法

-----------------------------

#2、微服务 
* 2014，martin fowler （发表论文，开启了微服务的热潮） * 微服务：架构风格（服务微化） 
*  一个应用应该是一组小型服务；可以通过HTTP的方式进行互通
* 单体应用：ALL IN ONE
 [详细参照微服务文档](https://martinfowler.com/articles/microservices.html#MicroservicesAndSoa) 
 
------------------------------------------

#3、环境准备 
* 环境约束 
	* jdk1.8：Spring Boot 推荐jdk1.7及以上版本； 
	* maven 3.x； 
	* IDEA2018； 
	* SpringBoot 1.5.9； 
##3.1、maven设置；
* 给`maven`的`settings.xml`配置文件添加`prifiles`标签
```xml
<profile>
	<id>jdk‐1.8</id>
	<activation>
		<activeByDefault>true</activeByDefault>
		<jdk>1.8</jdk>
	</activation>
	<properties>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
		<maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
	</properties>
</profile>
```
##3.2、IDEA设置
整合maven进来；
1. 进入setting
2. 修改相应的环境
[图片上传失败...(image-c3b649-1536735262471)]

-----------------


#4、Spring Boot Hello World!

> 实现功能
> 浏览器发送hello请求，服务器接受并且处理，响应Hello World字符串。

##4.1、创建一个maven工程；（jar）
##4.2、导入spring boot 相关依赖；（在pom.xml中）
```xml
<!-- 父项 -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.4.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
<dependencies>
<!-- 启动器 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
```
##4.3、编写一个主程序；启动Spring Boot应用
```java
/**
 * @SpringBootApplication 来标注一个主程序类，说明这是一个SpringBoot因员工
 * */

@SpringBootApplication
public class HelloWorldMain {
    public static void main(String[] args) {
        //Spring应用启动起来
        SpringApplication.run(HelloWorldMain.class,args);
    }
}
```
##4.4、编写相关的Controller、Service
```java
@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!";
    }
}
```
##4.5、运行主程序测试
##4.6、简化部署
* 将这个应用打成jar包，直接使用java -jar的命令进行执行；
```xml
    <!-- 这个插件，可以将应用打包成一个可执行的jar包 -->
    <build>
        <plugins>
            <plugin>
                <groupId> org.springframework.boot </groupId>
                <artifactId> spring-boot-maven-plugin </artifactId>
            </plugin >
        </plugins>
    </build>
```
#5、、使用Spring Initializer快速创建Spring Boot项目
####1. IDEA：使用 `Spring Initializer`快速创建项目IDE都支持使用Spring的项目创建向导快速创建一个Spring Boot项目；
选择我们需要的模块；向导会联网创建Spring Boot项目；
默认生成的Spring Boot项目；
	* 主程序已经生成好了，我们只需要我们自己的逻辑
		* static：保存所有的静态资源； js css images；
		* templates：保存所有的模板页面；（Spring Boot默认jar包使用嵌入式的omcat，默认不支持JSP页面）；可以使用模板引擎（freemarker、thymeleaf）；
		* application.properties：Spring Boot应用的配置文件；可以修改一些默认设置；

#####2.`STS`使用 Spring Starter Project快速创建项目
* 需要下载专用的`eclipse`
