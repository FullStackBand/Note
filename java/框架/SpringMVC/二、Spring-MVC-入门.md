#入门
##1.配置`DispatcherServlet`
* 位置web.xml
```
<!-- spring mvc 拦截器 -->
	<servlet>
		<servlet-name>springmvc</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>springmvc</param-name>
			<param-value>/WEB-INF/springmvc-servlet.xml</param-value>
		</init-param>
		<!-- 表示初始化的时候加载 -->
		<load-on-startup>1</load-on-startup>
	</servlet>
	<!-- 映射请求 -->
	<servlet-mapping>
		<servlet-name>springmvc</servlet-name>
		<!-- 
		表示应答所有请求
		<url-pattern>url</url-pattern> 
		-->
		<url-pattern>*.do</url-pattern>
	</servlet-mapping>
```
##2.配置`controller`请求处理器
###	--2.1配置`controller`请求处理器的包位置
* 在`DispatcherServlet`指定的文件中配置要扫描的包(classpath),此包就是 强求处理器的包
```
<!-- 指定Controller请求处理器的自定义的包 -->
	<context:annotation-config></context:annotation-config>
```
###	--2.2注解`@controller`声明自定义的请求处理器
* 在类名之前使用 `@Controller`注解表示该类是一个请求处理器
###	--2.3注解`@RequestMapping`配置映射的请求
* 在类名或方法名之前使用`@RequestMapping("value")`
##3.配置视图解析器
* 位置 在配置`controller`的配置之后
```
<!-- 配置视图解析器: 如何把 handler 方法返回值解析为实际的物理视图 -->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/"></property>
		<property name="suffix" value=".jsp"></property>
	</bean>
```
