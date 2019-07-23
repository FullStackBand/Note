#1、简介
使用SpringBoot
**1）、创建SpringBoot应用，选中我们需要的模块；**

**2）、SpringBoot已经默认将这些场景配置好了，只需要在配置文件中指定少量配置就可以运行起来**

**3）、自己编写业务代码；**
#2、对静态资源的规则
> [官方文档:静态资源](https://docs.spring.io/spring-boot/docs/1.5.10.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-static-content)

##1）默认情况下
* Spring Boot将从类路径中的`/static`（ `/public`或`/resources`或`/META-INF/resources`）目录或者根目录中提供静态内容`ServletContext`。
* 它使用`ResourceHttpRequestHandlerSpring MVC`，因此您可以通过添加自己`WebMvcConfigurerAdapter`的`addResourceHandlers`方法来修改该行为并覆盖该 方法。
* 在独立的Web应用程序中，还启用了容器中的默认`servlet`，并充当后备，从`ServletContextif` 的根目录提供内容，决定不处理它。大多数情况下这不会发生（除非你修改默认的MVC配置）因为Spring总是能够通过它来处理请求`DispatcherServlet`。
##2) 修改默认
* 修改默认静态资源路径`/**`
```properties
spring.mvc.static-path-pattern = / resources / **
```
* 修改欢迎界面
```properties
spring.resources.static-locations = /index.html
```
* Webjars
具有路径的所有资源`/webjars/**` 如果以Webjars格式打包，将从jar文件中提供。
Webjars需要在pom.xml中引入包
[资源链接](https://www.webjars.org/)
> src/main/webapp如果您的应用程序将打包为jar，请不要使用该目录。虽然这个目录是一个通用标准，但它只适用于war包装，如果你生成一个jar，它将被大多数构建工具默默忽略。

#3、模板引擎
##1）引入thymeleaf
* 引入
```xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
          	2.1.6
</dependency>
```
* 切换默认版本
```xml
<properties>
		<thymeleaf.version>3.0.9.RELEASE</thymeleaf.version>
		<!-- 布局功能的支持程序  thymeleaf3主程序  layout2以上版本 -->
		<!-- thymeleaf2   layout1-->
		<thymeleaf-layout-dialect.version>2.2.2</thymeleaf-layout-dialect.version>
</properties>
```
##2）Thymeleaf使用
* 配置源码
```java
@ConfigurationProperties(prefix = "spring.thymeleaf")
public class ThymeleafProperties {

	private static final Charset DEFAULT_ENCODING = Charset.forName("UTF-8");

	private static final MimeType DEFAULT_CONTENT_TYPE = MimeType.valueOf("text/html");

	public static final String DEFAULT_PREFIX = "classpath:/templates/";

	public static final String DEFAULT_SUFFIX = ".html";
```
    可以看出他使用的是`html`页面
    并且其网页也要放入`classpath:/templates/`路径之中
* 只要我们把HTML页面放在classpath:/templates/，thymeleaf就能自动渲染；

###1.导入thymeleaf的名称空间
```html
  <html lang="en" xmlns:th="http://www.thymeleaf.org">
```
###2.使用thymeleaf语法

```html
  <!DOCTYPE html>
  <html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
      <meta charset="UTF-8">
      <title>Title</title>
    </head>
    <body>
      <h1>成功！</h1>
      <!--th:text 将div里面的文本内容设置为 -->
      <div th:text="${hello}">这是显示欢迎信息      </div>
    </body>
  </html>
```
##3)语法规则
1. th：任意html属性；来替换原生属性的值
![2018-02-04_123955.png](https://upload-images.jianshu.io/upload_images/13055171-2a00366a4be80842.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

2. 表达式
```
Simple expressions:（表达式语法）
    Variable Expressions: ${...}：获取变量值；OGNL；
    		1）、获取对象的属性、调用方法
    		2）、使用内置的基本对象：
    			#ctx : the context object.
    			#vars: the context variables.
                #locale : the context locale.
                #request : (only in Web Contexts) the HttpServletRequest object.
                #response : (only in Web Contexts) the HttpServletResponse object.
                #session : (only in Web Contexts) the HttpSession object.
                #servletContext : (only in Web Contexts) the ServletContext object.
                
                ${session.foo}
            3）、内置的一些工具对象：
#execInfo : information about the template being processed.
#messages : methods for obtaining externalized messages inside variables expressions, in the same way as they would be obtained using #{…} syntax.
#uris : methods for escaping parts of URLs/URIs
#conversions : methods for executing the configured conversion service (if any).
#dates : methods for java.util.Date objects: formatting, component extraction, etc.
#calendars : analogous to #dates , but for java.util.Calendar objects.
#numbers : methods for formatting numeric objects.
#strings : methods for String objects: contains, startsWith, prepending/appending, etc.
#objects : methods for objects in general.
#bools : methods for boolean evaluation.
#arrays : methods for arrays.
#lists : methods for lists.
#sets : methods for sets.
#maps : methods for maps.
#aggregates : methods for creating aggregates on arrays or collections.
#ids : methods for dealing with id attributes that might be repeated (for example, as a result of an iteration).

    Selection Variable Expressions: *{...}：选择表达式：和${}在功能上是一样；
    	补充：配合 th:object="${session.user}：
   <div th:object="${session.user}">
    <p>Name: <span th:text="*{firstName}">Sebastian</span>.</p>
    <p>Surname: <span th:text="*{lastName}">Pepper</span>.</p>
    <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>
    </div>
    
    Message Expressions: #{...}：获取国际化内容
    Link URL Expressions: @{...}：定义URL；
    		@{/order/process(execId=${execId},execType='FAST')}
    Fragment Expressions: ~{...}：片段引用表达式
    		<div th:insert="~{commons :: main}">...</div>
    		
Literals（字面量）
      Text literals: 'one text' , 'Another one!' ,…
      Number literals: 0 , 34 , 3.0 , 12.3 ,…
      Boolean literals: true , false
      Null literal: null
      Literal tokens: one , sometext , main ,…
Text operations:（文本操作）
    String concatenation: +
    Literal substitutions: |The name is ${name}|
Arithmetic operations:（数学运算）
    Binary operators: + , - , * , / , %
    Minus sign (unary operator): -
Boolean operations:（布尔运算）
    Binary operators: and , or
    Boolean negation (unary operator): ! , not
Comparisons and equality:（比较运算）
    Comparators: > , < , >= , <= ( gt , lt , ge , le )
    Equality operators: == , != ( eq , ne )
Conditional operators:条件运算（三元运算符）
    If-then: (if) ? (then)
    If-then-else: (if) ? (then) : (else)
    Default: (value) ?: (defaultvalue)
Special tokens:
    No-Operation: _ 
```
#4、SpringMVC自动配置
> [官方文档：SpringMVC自动配置](https://docs.spring.io/spring-boot/docs/1.5.10.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-auto-configuration)

##1）Spring MVC auto-configuration
以下是SpringBoot对SpringMVC的默认配置:`（WebMvcAutoConfiguration）`
- Inclusion of `ContentNegotiatingViewResolver` and `BeanNameViewResolver` beans.

  - 自动配置了ViewResolver（视图解析器：根据方法的返回值得到视图对象（View），视图对象决定如何渲染（转发？重定向？））
  - ContentNegotiatingViewResolver：组合所有的视图解析器的；
  - ==如何定制：我们可以自己给容器中添加一个视图解析器；自动的将其组合进来；==

- Support for serving static resources, including support for WebJars (see below).静态资源文件夹路径,webjars

- Static `index.html` support. 静态首页访问

- Custom `Favicon` support (see below).  favicon.ico

  ​

- 自动注册了 of `Converter`, `GenericConverter`, `Formatter` beans.

  - Converter：转换器；  public String hello(User user)：类型转换使用Converter
  - `Formatter`  格式化器；  2017.12.17===Date；
##2）扩展SpringMVC

**编写一个配置类（`@Configuration`），是`WebMvcConfigurerAdapte`r类型；不能标注`@EnableWebMvc`**
```java
//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       // super.addViewControllers(registry);
        //浏览器发送 /atguigu 请求来到 success
        registry.addViewController("/atguigu").setViewName("success");
    }
}
```
##3）全面接管SpringMVC
SpringBoot对SpringMVC的自动配置不需要了，所有都是我们自己配置；所有的SpringMVC的自动配置都失效了

**我们需要在配置类中添加`@EnableWebMvc`即可；**
#5、如何修改SpringBoot的默认配置
* 模式：

​	1）SpringBoot在自动配置很多组件的时候，先看容器中有没有用户自己配置的（@Bean、@Component）如果有就用用户配置的，如果没有，才自动配置；如果有些组件可以有多个（ViewResolver）将用户配置的和自己默认的组合起来；

​	2）在SpringBoot中会有非常多的xxxConfigurer帮助我们进行扩展配置

​	3）、在SpringBoot中会有很多的xxxCustomizer帮助我们进行定制配置
#6、Restful风格
##1)Restful风格注解
```java
    @PostMapping
    @DeleteMapping
    @GetMapping
    @PutMapping
```
例如：`@PostMapping`中都配置了（简化了写法）
```java
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(
    method = {RequestMethod.POST}
)
```
##2)默认访问首页
```java
//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc   不要接管SpringMVC
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       // super.addViewControllers(registry);
        //浏览器发送 /atguigu 请求来到 success
        registry.addViewController("/atguigu").setViewName("success");
    }

    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
            }
        };
        return adapter;
    }
}
```

##3)登陆
1.禁用模板引擎的缓存
```properties
# 禁用缓存
spring.thymeleaf.cache=false 
```
2.页面修改完成以后ctrl+f9：重新编译；
* 登陆错误消息的显示
```html
<p style="color: red" th:text="${msg}" th:if="${not #strings.isEmpty(msg)}"></p>
```
##4)拦截器进行登陆检查
* 编写拦截器`HandlerInterceptor`
```java
**
 * 登陆检查，
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if(user == null){
            //未登陆，返回登陆页面
            request.setAttribute("msg","没有权限请先登陆");
            //重定向
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else{
            //已登陆，放行请求
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
```
* 注册自定义拦截器   写在自定义配置类中
```java
/**
 * 声明这是一个配置类
 * */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean//将组件注册到容器中
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");

            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //super.addInterceptors(registry);
                /**
                 * addInterceptor   添加拦截器
                 * addPathPatterns  添加拦截路径 --  /**表示所有
                 * excludePathPatterns  添加例外路径
                 *
                 * */
                registry.addInterceptor(new LoginState()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login");
            }
        };
        return adapter;
    }
}
```


