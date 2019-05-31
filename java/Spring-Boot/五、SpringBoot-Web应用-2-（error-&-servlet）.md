#1、错误处理机制
 * Spring Boot `/error`默认提供映射，以合理的方式处理所有错误，并在servlet容器中注册为“全局”错误页面。
* 对于机器客户端，它将生成一个`JSON`响应，其中包含错误，HTTP状态和异常消息的详细信息。
对于浏览器客户端，有一个“`whitelabel`”错误视图，以HTML格式呈现相同的数据（要自定义它，只需添加一个View解析为'错误'的数据）。
* 要完全替换默认行为，您可以实现 `ErrorController`并注册该类型的`bean`定义，或者只是添加类型的bean ErrorAttributes以使用现有机制但替换内容。
##1）、SpringBoot默认的错误处理机制
##2）、如果定制错误响应：
###1. 如何定制错误的页面；
1）有模板引擎的情况下；error/状态码;
* 【将错误页面命名为 错误状态码.html 放在模板引擎文件夹里面的error文件夹下】，发生此状态码的错误就会来到 对应的页面；
* 我们可以使用4xx和5xx作为错误页面的文件名来匹配这种类型的所有错误，精确优先（优先寻找精确的状态
码.html）；
* 页面能获取的信息；
  * timestamp：时间戳
  * status：状态码
  * error：错误提示
  * exception：异常对象
  * message：异常消息
  * errors：JSR303数据校验的错误都在这里

2）没有模板引擎（模板引擎找不到这个错误页面），静态资源文件夹下找；
3）以上都没有错误页面，就是默认来到SpringBoot默认的错误提示页面；

###2. 如何定制错误的json数据；
1）自定义异常处理&返回定制json数据；
* 自定义异常
 ```java
public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
        super("用户不存在");
    }
}
```
 * **没有自适应效果...**
```java
@ControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Map<String,Object> handleException(Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("code","user.notexist");
        map.put("message",e.getMessage());
        return map;
    }
}
```
>     @ControllerAdvice 的声明内容
>    ```
>      @Target(ElementType.TYPE)  
>      @Retention(RetentionPolicy.RUNTIME)  
>      @Documented  
>      @Component  
>    ```
2)  设置什么情况下调用异常
```java
@ResponseBody
    @RequestMapping("/hello")
    public  String hello(@RequestParam("user") String user){
        if(user.equals("aaa")){
            throw new UserNotExistException();
        }
        return "Hello World";
    }
```
3）转发到/error进行自适应响应效果处理
```java
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //传入我们自己的错误状态码  4xx 5xx
        /**
         * Integer statusCode = (Integer) request
         .getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexist");
        map.put("message","用户出错啦");

        request.setAttribute("ext",map);
        //转发到/error
        return "forward:/error";
    }
}
```
###3. 将我们的定制数据携带出去；
* 出现错误以后，会来到`/error`请求，会被`BasicErrorController`处理，响应出去可以获取的数据是由`getErrorAttributes`得到的（是`AbstractErrorController（ErrorController）`规定的方法）；

​	1. 完全来编写一个`ErrorController`的实现类【或者是编写`AbstractErrorController`的子类】，放在容器中；【不方便】

​	2. 页面上能用的数据，或者是`json`返回能用的数据都是通过`errorAttributes.getErrorAttributes`得到； 容器中`DefaultErrorAttributes.getErrorAttributes()；`默认进行数据处理的；

* 给容器中加入我们自己定义的`ErrorAttributes`
```java
//给容器中加入我们自己定义的ErrorAttributes
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    //返回值的map就是页面和json能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);
        map.put("company","atguigu");

        //我们的异常处理器携带的数据
        Map<String,Object> ext = (Map<String, Object>) requestAttributes.getAttribute("ext", 0);
        map.put("ext",ext);
        return map;
    }
}
```
#8、配置嵌入式Servlet容器
##1、如何定制和修改Servlet容器的相关配置；
1）修改和server有关的配置（`ServerProperties`【也是EmbeddedServletContainerCustomizer】）；
```properties
server.port=8081
server.context‐path=/crud
server.tomcat.uri‐encoding=UTF‐8
//通用的Servlet容器设置
server.xxx
//Tomcat的设置
server.tomcat.xxx
```
2） 编写一个`EmbeddedServletContainerCustomizer`：嵌入式的Servlet容器的定制器；来修改Servlet容器的
配置
```java
@Bean  //一定要将这个定制器加入到容器中
public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
    return new EmbeddedServletContainerCustomizer() {

        //定制嵌入式的Servlet容器相关的规则
        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.setPort(8083);
        }
    };
}
```
##2、注册Servlet三大组件【`Servlet`、`Filter`、`Listener`】
* `ServletRegistrationBean`
* `FilterRegistrationBean`
* `ServletListenerRegistrationBean`
> 前提是自己也要编写Servlet`、`Filter`、`Listener`方法【下面就不示范这三个类的编写了】
1. `ServletRegistrationBean`
```java
@Bean
public ServletRegistrationBean myServlet(){
    ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
    return registrationBean;
}
```
2. `FilterRegistrationBean`
```java
@Bean
public FilterRegistrationBean myFilter(){
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    registrationBean.setFilter(new MyFilter());
    registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
    return registrationBean;
}
```
3. `ServletListenerRegistrationBean`
```java
@Bean
public ServletListenerRegistrationBean myListener(){
    ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<>(new MyListener());
    return registrationBean;
}
```
##3、使用内置的Servlet容器
> 目前支持3中servlet容器
> 1. tomcat
> 2. jetty
> 3. undertow
1. 排除默认使用的`Tomcat`
```xml
<!-- 引入web模块 -->
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
   <exclusions>
      <!-- 排除tomcat -->
      <exclusion>
         <artifactId>spring-boot-starter-tomcat</artifactId>
  <groupId>org.springframework.boot</groupId>
      </exclusion>
   </exclusions>
</dependency>
```
2. 引入
* jetty
```xml
<!--引入其他的Servlet容器-->
<dependency>
   <artifactId>spring-boot-starter-jetty</artifactId>
   <groupId>org.springframework.boot</groupId>
</dependency>
```
* undertow
```xml
<!--引入其他的Servlet容器-->
<dependency>
   <artifactId>spring-boot-starter-undertow</artifactId>
   <groupId>org.springframework.boot</groupId>
</dependency>
```
##3、使用外置的Servlet容器
**步骤**
###1）添加服务
1. 必须在创建的时候，创建`war`项目
![创建`war`项目
](https://upload-images.jianshu.io/upload_images/13055171-12eee7ed103fa9f5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
2. 添加Tomcat服务
  1. 打开配置界面
  ![打开配置界面](https://upload-images.jianshu.io/upload_images/13055171-07641d49a9c85d65.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
  2. 配置服务器
* 选择服务器
  ![选择服务器](https://upload-images.jianshu.io/upload_images/13055171-51fde6ff739d1dca.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
* 配置服务器
![配置服务器](https://upload-images.jianshu.io/upload_images/13055171-301a0ac7c1de2f77.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
* 部署运行项目
![部署运行项目](https://upload-images.jianshu.io/upload_images/13055171-c40751bed36be99b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![选择项目](https://upload-images.jianshu.io/upload_images/13055171-856d7afd63b2632f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

3. 配置项目 `webapp`中的文件
* 打开配置页面

![打开配置页面](https://upload-images.jianshu.io/upload_images/13055171-d80d99cce0f7b498.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
* 配置 webapp 文件夹 和 web.xml 文件

![配置 webapp 文件夹 和 web.xml 文件](https://upload-images.jianshu.io/upload_images/13055171-ef9ba01b3690d8bd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

###2）必须编写一个**SpringBootServletInitializer**的子类，并调用configure方法
* 通过扩展SpringBootServletInitializer（例如在一个被调用的类中Application）创建可部署的war ，并添加Spring Boot @SpringBootApplication注释。
```java
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // Customize the application or call application.sources(...) to add sources
        // Since our example is itself a @Configuration class (via @SpringBootApplication)
        // we actually don't need to override this method.
        return application;
    }
}
```
请记住，无论你放入什么`sources`只是一个`SpringApplicationContext`，通常任何已经有效的东西都应该在这里工作。可能有一些`bean`可以在以后删除，让**Spring Boot**为它们提供自己的默认值，但应该可以先得到一些工作。

静态资源可以移动到类路径根目录中`/public`（`/static`或`/resources`或 `/META-INF/resources`）。相同`messages.properties`（Spring Boot会在类路径的根目录中自动检测到这一点）。
#4、使用外置Servlet容器
##1）目录结构
实例：
![目录](https://upload-images.jianshu.io/upload_images/13055171-5656fb37ccd43bb8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##2）jsp
```thml
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>HELLO</h1>
    <a href="/hello" ><h2>success</h2></a>
</body>
</html>
```
##3）视图解析器配置
```properties
spring.mvc.view.prefix=/WEB-INF/
spring.mvc.view.suffix=.jsp
```
##4）控制器
```java
@Controller
public class controller {
    @GetMapping("/hello")
    public String success(Model model) {
        model.addAttribute("hello", "你好！");
        return "success";
    }
}
```
##5）转发至的jsp页面
```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>success</h1>
</body>
</html>
```












