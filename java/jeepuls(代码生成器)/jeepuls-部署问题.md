#1.问题一:
```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.3:compile (default-compile) on project jeeplus: Compilation failure
[ERROR] No compiler is provided in this environment. Perhaps you are running on a JRE rather than a JDK?
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
```
##解决办法

![](https://upload-images.jianshu.io/upload_images/13055171-96227bbe7d98ff86.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

改为jdk 的目录
![](https://upload-images.jianshu.io/upload_images/13055171-c077d11419839c2d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#问题二:
```
2018-11-01 18:18:30,456 WARN  [alibaba.druid.pool.vendor.MySqlValidConnectionChecker] - Cannot resolve com.mysq.jdbc.Connection.ping method.  Will use 'SELECT 1' instead.
java.lang.NullPointerException
      ***太多省略***	
2018-11-01 18:18:35,015 DEBUG [jeeplus.modules.act.rest.DispatcherServletConfiguration] - Creating requestMappingHandlerMapping
2018-11-01 18:18:35,037 DEBUG [jeeplus.modules.act.rest.DispatcherServletConfiguration] - Configuring localeChangeInterceptor
十一月 01, 2018 6:18:40 下午 org.apache.catalina.core.ApplicationContext log
信息: Initializing Spring FrameworkServlet 'RestServlet'
十一月 01, 2018 6:18:40 下午 org.apache.catalina.core.ApplicationContext log
信息: Initializing Spring FrameworkServlet 'ModelRestServlet'
十一月 01, 2018 6:18:41 下午 org.apache.catalina.core.ApplicationContext log
信息: Initializing Spring FrameworkServlet 'springServlet'
```
##原因
* 启动项目的时候，报了这个错误com.alibaba.druid.pool.vendor.MySqlValidConnectionChecker - Cannot resolve com.mysq.jdbc.Connection.ping method. Will use 'SELECT 1' instead.，后面修改了jbdc和druid的依赖，把druid的依赖修改成最新的版本。即可启动成功。

##解决办法
pom.xml
```
<druid.version>1.0.11</druid.version>
```
修改为
```
<druid.version>1.1.11</druid.version>
```

#问题三
##报错
```
***省略***
Caused by: java.net.ConnectException: Connection refused: connect
	at java.net.DualStackPlainSocketImpl.waitForConnect(Native Method)
	at java.net.DualStackPlainSocketImpl.socketConnect(DualStackPlainSocketImpl.java:85)
	at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:350)
	at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:206)
	at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:188)
	at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:172)
	at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
	at java.net.Socket.connect(Socket.java:589)
	at redis.clients.jedis.Connection.connect(Connection.java:184)
	... 81 more
```
##原因
端口占用
##解决办法
更改设置
pom.xml
```
        <!-- environment setting -->
        <jdk.version>1.8</jdk.version>
        <tomcat.version>2.2</tomcat.version>
        <jetty.version>7.6.14.v20131031</jetty.version>
        <webserver.port>8080</webserver.port>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <downloadSources>true</downloadSources>
```
#问题四
```
***省略****
Caused by: redis.clients.jedis.exceptions.JedisConnectionException: java.net.ConnectException: Connection refused: connect
	at redis.clients.jedis.Connection.connect(Connection.java:207)
	at redis.clients.jedis.BinaryClient.connect(BinaryClient.java:93)
	at redis.clients.jedis.BinaryJedis.connect(BinaryJedis.java:1767)
	at redis.clients.jedis.JedisFactory.makeObject(JedisFactory.java:106)
	at org.apache.commons.pool2.impl.GenericObjectPool.create(GenericObjectPool.java:868)
	at org.apache.commons.pool2.impl.GenericObjectPool.borrowObject(GenericObjectPool.java:458)
	at org.apache.commons.pool2.impl.GenericObjectPool.borrowObject(GenericObjectPool.java:363)
	at redis.clients.util.Pool.getResource(Pool.java:49)
	... 74 more
Caused by: java.net.ConnectException: Connection refused: connect
	at java.net.DualStackPlainSocketImpl.waitForConnect(Native Method)
	at java.net.DualStackPlainSocketImpl.socketConnect(DualStackPlainSocketImpl.java:85)
	at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:350)
	at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:206)
	at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:188)
	at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:172)
	at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
	at java.net.Socket.connect(Socket.java:589)
	at redis.clients.jedis.Connection.connect(Connection.java:184)
	... 81 more
```
##原因
没有启动redis
##解决方法
启动redis
