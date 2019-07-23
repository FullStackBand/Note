#什么是RESTful
* 一种软件架构风格、设计风格，而不是标准，只是提供了一组设计原则和约束条件。它主要用于客户端和服务器交互类的软件。基于这个风格设计的软件可以更简洁，更有层次，更易于实现缓存等机制。
* REST（英文：Representational State Transfer，简称REST）
*  * REST：即 Representational State Transfer。（资源）表现层状态转化。是目前最流行的一种互联网软件架构。它结构清晰、符合标准、易于理解、扩展方便，所以正得到越来越多网站的采用
* `资源（Resources）`：网络上的一个实体，或者说是网络上的一个具体信息。它可以是一段文本、一张图片、一首歌曲、一种服务，总之就是一个具体的存在。可以用一个URI（统一资源定位符）指向它，每种资源对应一个特定的 URI 。要获取这个资源，访问它的URI就可以，`因此 URI 即为每一个资源的独一无二的识别符`。
* `表现层（Representation）：把资源具体呈现出来的形式`，叫做它的表现层（Representation）。比如，文本可以用 txt 格式表现，也可以用 HTML 格式、XML 格式、JSON 格式表现，甚至可以采用二进制格式。
* `状态转化（State Transfer）`：每发出一个请求，就代表了客户端和服务器的一次交互过程。HTTP协议，是一个无状态协议，即所有的状态都保存在服务器
端。因此，如果客户端想要操作服务器，必须通过某种手段，让服务器端发生“状态转化”（State Transfer）。而这种转化是建立在表现层之上的，所以就是 “表现层状态转化”。具体说，就是 `HTTP 协议里面，四个表示操作方式的动词：GET、POST、PUT、DELETE。它们分别对应四种基本操作：GET 用来获取资源，POST 用来新建资源，PUT 用来更新资源，DELETE 用来删除资源。`



##1.5 `REST` 风格的请求


###第一步 配置过滤器
* `HiddenHttpMethodFilter`：浏览器 form 表单`只支持 GET与 POST` 请求，而DELETE、PUT 等 method 并不支持，Spring3.0 添加了一个过滤器，可以将这些请求转换为标准的 http 方法，使得支持` GET、POST、PUT 与DELETE `请求。
* 配置方法：
```
<filter>  
                <filter-name>HiddenHttpMethodFilter</filter-name>  
                <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>  
        </filter>  
        <filter-mapping>  
                <filter-name>HiddenHttpMethodFilter</filter-name>  
                <servlet-name>spring</servlet-name>  
        </filter-mapping>
```


> `注意`：`HiddenHttpMethodFilter过滤器必须配置在DispatcherServlet之前`


###第二步 Controller 

```
/**
	 * Rest 风格的 URL. 以 CRUD 为例: 新增: /order POST 修改: /order/1 PUT update?id=1 获取:
	 * /order/1 GET get?id=1 删除: /order/1 DELETE delete?id=1
	 * 
	 * 如何发送 PUT 请求和 DELETE 请求呢 ? 
	 * 1. 需要配置 HiddenHttpMethodFilter 
	 * 2. 需要发送 POST 请求
	 * 3. 需要在发送 POST 请求时携带一个 name="_method" 的隐藏域, 值为 DELETE 或 PUT
	 * 
	 * 在 SpringMVC 的目标方法中如何得到 id 呢? 使用 @PathVariable 注解
	 * 
	 */
	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
	public String testRestPut(@PathVariable Integer id) {
		System.out.println("testRest Put: " + id);
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
	public String testRestDelete(@PathVariable Integer id) {
		System.out.println("testRest Delete: " + id);
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest", method = RequestMethod.POST)
	public String testRest() {
		System.out.println("testRest POST");
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.GET)
	public String testRest(@PathVariable Integer id) {
		System.out.println("testRest GET: " + id);
		return SUCCESS;
	}
```

###前台 form 表单提交
* 如何发送 PUT 请求和 DELETE 请求呢 ? 
	 1. 需要配置 HiddenHttpMethodFilter 
	 2. 需要发送 POST 请求
	 3. 需要在发送 POST 请求时携带一个 name="_method" 的隐藏域, 值为 DELETE 或 PUT
```
	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="PUT"/>
		<input type="submit" value="TestRest PUT"/>
	</form>
	<br><br>
	
	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
		<input type="submit" value="TestRest DELETE"/>
	</form>
	<br><br>
	
	<form action="springmvc/testRest" method="post">
		<input type="submit" value="TestRest POST"/>
	</form>
	<br><br>
	
	<a href="springmvc/testRest/1">Test Rest Get</a>
	<br><br>
```
