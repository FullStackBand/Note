#方案一：激活Tomcat的defaultServlet来处理静态文件
```
  <servlet-mapping>
      <servlet-name>default</servlet-name>
      <url-pattern>*.jpg</url-pattern>
  </servlet-mapping>
  <servlet-mapping>
      <servlet-name>default</servlet-name>
      <url-pattern>*.js</url-pattern>
  </servlet-mapping>
  <servlet-mapping>
     <servlet-name>default</servlet-name>
     <url-pattern>*.css</url-pattern>
 </servlet-mapping>  
```
* 要配置多个，每种文件配置一个。要写在DispatcherServlet的前面， 让defaultServlet先拦截，这个就不会进入Spring了，我想性能是最好的吧。

* Tomcat, Jetty, JBoss, and GlassFish  默认 Servlet的名字 -- "default"

* Google App Engine 默认 Servlet的名字 -- "_ah_default"

* Resin 默认 Servlet的名字 -- "resin-file"

* WebLogic 默认 Servlet的名字  -- "FileServlet"

 *WebSphere  默认 Servlet的名字 -- "SimpleFileServlet"
 
 #方案二： 在spring3.0.4以后版本提供了mvc:resources 
 
```
 <!--对静态资源文件的访问-->
 <mvc:resources mapping="/images/**" location="/images/" />
```
* /images /**映射到 ResourceHttpRequestHandler 进行处理，location指定静态资源的位置.可以是web application根目录下、jar包里面，这样可以把静态资源压缩到jar包中。cache-period可以使得静态资源进行web cache  

* 如果出现下面的错误，可能是没有配置 `<mvc:annotation-driven />` 的原因。 报错`WARNING: No mapping found for HTTP request with URI [/mvc/user/findUser/lisi/770] in DispatcherServlet with name 'springMVC'`

* 使用 `<mvc:resources/>` 元素,把 mapping 的 URI 注册到 SimpleUrlHandlerMapping的urlMap 中,key 为 mapping 的 URI pattern值,而 value为 ResourceHttpRequestHandler,这样就巧妙的把对静态资源的访问由 HandlerMapping 转到ResourceHttpRequestHandler 处理并返回,所以就支持 classpath 目录, jar 包内静态资源的访问.另外需要注意的一点是,不要对 SimpleUrlHandlerMapping 设置 defaultHandler. 因为对 static uri 的 defaultHandler 就是ResourceHttpRequestHandler,


#方案三 ，使用<mvc:default-servlet-handler/>
 ```

<mvc:default-servlet-handler/>
 
```
* 会把 "/**" url,注册到 SimpleUrlHandlerMapping 的 urlMap 中,把对静态资源的访问由 HandlerMapping 转到 org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler 处理并返回.
DefaultServletHttpRequestHandler 使用就是各个 Servlet 容器自己的默认 Servlet.

* 补充说明：多个HandlerMapping的执行顺序问题：

 

* DefaultAnnotationHandlerMapping 的 order 属性值是：0

 

* <mvc:resources/ >自动注册的 SimpleUrlHandlerMapping 的 order 属性值是： 2147483646

 

* <mvc:default-servlet-handler/>自动注册的 SimpleUrlHandlerMapping 的 order 属性值是：2147483647

 

* spring 会先执行 order 值比较小的。当访问一个 a.jpg 图片文件时，先通过 DefaultAnnotationHandlerMapping 来找处理器，一定是找不到的，我们没有叫 a.jpg 的 Action。再按 order 值升序找，由于最后一个 SimpleUrlHandlerMapping 是匹配 "/**" 的，所以一定会匹配上，再响应图片。
