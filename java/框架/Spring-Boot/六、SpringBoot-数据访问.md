#1、JDBC
##1）启动器
如果您使用`spring-boot-starter-jdbc`或`spring-boot-starter-data-jpa`“启动器”，您将自动获得依赖`HikariCP`
> [HikariCP－史上最快速的连接池](http://brettwooldridge.github.io/HikariCP/)
没有什么比这更快了。没有比这更正确的了。HikariCP是一个“零开销”的生产质量连接池。
##2）数据库连接
```yml
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/test
    username: root
    password: root
    driver-class-name: com.mysql.jdbc.Driver
```
> SpringBoot默认可以支持；
> 1. org.apache.tomcat.jdbc.pool.DataSource
> 2. HikariDataSource
> 3. BasicDataSource
##3) 更多选项
###1. 默认
有关DataSourceProperties 更多支持的选项，请参阅 。无论实际实施如何，这些都是标准选项。也可以微调实现特定的设置，使用各自的前缀（`spring.datasource.hikari.*`， `spring.datasource.tomcat.*`，和`spring.datasource.dbcp2.*`）。

例如，如果使用 Tomcat连接池，则可以自定义许多其他设置，如以下示例所示：
```properties
＃如果没有可用连接，则在抛出异常之前要等待的ms数。
spring.datasource.tomcat.max-wait = 10000

＃可以同时从该池分配的最大活动连接数。
spring.datasource.tomcat.max-active = 50

＃在从池中借用连接之前验证连接。
spring.datasource.tomcat.test-on-borrow = true
```
###2. 自定义
请看整合Druid数据源

#2、整合Druid数据源
##1）导入资源(pom.xml)
```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.8</version>
        </dependency>
```
##2）整合其他数据源需要编写配置类
```java
@Configuration
public class DruidCinfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        return new DruidDataSource();
    }
    //配置Druid监控
    //配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        /**创建一个管理后台的Servlet
         *      StatViewServlet=状态展示的页面
         *      "/druid/"=请求路径）
         * */
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        HashMap<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername", "admin"); //配置登陆用户名
        initParameters.put("loginPassword", "123456");//配置登陆密码
        initParameters.put("allow", "");//允许哪些IP访问  如果为“”则表示所有
        initParameters.put("deny", "");//拒绝哪些IP访问  如果为“”则表示所有
        bean.setInitParameters(initParameters);
        return bean;
    }
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        Map<String,String> initParameters = new HashMap();
        initParameters.put("exclusions", "*.js,*.css,/druid/*");//放行的请求
        bean.setInitParameters(initParameters);
        bean.setUrlPatterns(Arrays.asList("/*"));//拦截所有请求
        return bean;
    }
}
```
##3）编写好配置类中的方法`druid`就可以配置如下内容
```yml
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/test
    username: root
    password: root
    driver-class-name: com.mysql.jdbc.Driver
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




```
