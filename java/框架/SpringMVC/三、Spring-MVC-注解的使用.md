#1.`@RequestMapping` 的使用
##1.1映射url
* Spring MVC 使用 @RequestMapping 注解为控制器指定可以处理哪些 URL 请求
* 在控制器的类定义及方法定义处都可标注`@RequestMapping`
	* 类处 : 提供初步的请求映射信息.相对于WEB应用的目录
	* 方法处 : 提供进一步的细分映射信息. 相对与类定义出的`url` . 若类定义处为标注`@RequestMapping`,则方法处标记的`url`就相对于WEB应用的根目录
* `DispatcherServlet`接获请求后,就通过控制器(`controller`)上的`@RequestMapping`提供的映射信息确定请求所对应的处理方法.
例如:
```
@Controller
/*类标注处*/
@RequestMapping("/test")
public class Test {
	/*方法标注处*/
	@RequestMapping("test1")
	public String test1 () {
		
		return "success";
	}
}
```
> * `@RequestMapping` 除了可以使用请求 URL 映射请求外，还可以使用请求方法、请求参数及请求头映射请求。
* `@RequestMapping` 的 `value`、`method`、`params` 及 `heads`分别表示请求 `URL`、请求方法、请求参数及请求头的映射条
件，他们之间是与的关系，联合使用多个条件可让请求映射
更加精确化。

------------------------------------------
##1.2映射请求参数、请求方法、请求头
 * `params` 和 `headers` 支持简单的表达式
	* `param1` : 表示请求必须包含名为 `param1`的请求参数
	* !param1 : 表示请求不能包含 `param1`的请求参数
	* `param1 != value1` : 表示请求包含名为 `param1`的请求参数，但是其值不能为`value1`
	* `{"param1 = value" , "param2"}` : 请求必须包含名为`param1` 和 `param2`的两个请求参数，且`param1`参数的值必须为`value1`。
* 例如：
```
/** 
  * 映射地址=delete
  * 请求方法=post
  * 请求参数=userId （必须有这个参数）
  */
@RequestMapping(value="/delete" , method=RequesteMethod.POST , params="userId")
public String test1(){
	//...
	return "user/test1";
}
/** 
  * 映射地址=show
  * 请求头必须含有 ： 
  *        contentType = text/*
  * 其中 * 为通配符 表示所有字符
  */
@RequestMapping(value="/show" , headers="contentType = text/*")
public String test1(){
	//...
	return "user/test1";
}
``` 

##1.3 `Ant`风格资源地址支持的3种匹配符
>* `?` ：匹配文件名中的一个字符
* `*`  ：匹配文件名中的任意字符
* `**`：匹配对曾路径

* 例如：`@RequestMapping`的`value`可以写成如下形式

 *	/user/`*`/createUser: 匹配  
	 *	/user/`aaa`/createUser
	 *	/user/`bbb`/createUser 
	 *	等 URL
 * /user/`**`/createUser: 匹配  
	 * /user/createUser
	 * /user/`aaa/bbb`/createUser 
	 * 等 URL
 * /user/createUser`??`: 匹配  
	 * /user/createUseraa
	 * /user/createUser`bb` 
	 * 等 URL
##1.4`@PathVariable` 占位符
* 带占位符的`URL`是Spring 3.X之后添加的新功能，该功能在SpringMVC向`REST`（[什么是REST？](https://baike.baidu.com/item/rest/6330506?fr=aladdin)）挺近有着里程碑的意义。
* 通过`@PathVariable`可以将URL中占位符绑定到控制器的参数中。
* URL 中的`{XXX}`占位符可以通过`@PathVariable("XXX")`绑定到操作方法的形参中
* 例如：
```
@RequestMapping("/delete/{id}")
public String delete(@PathVariable("id") Integer id){
	System.out.println(id)
	return "success";
}
```

#2.`@RequestParanm`绑定请求参数
* `@RequestParam` 来映射请求参数. 
* `value` 值即请求参数的参数名 
* `required` 该参数是否必须. 默认为 `true`
* `defaultValue` 请求参数的默认值

```
	/**
	 * @RequestParam 来映射请求参数. 
	 * value 值即请求参数的参数名 
	 * required 该参数是否必须. 默认为 true
	 * defaultValue 请求参数的默认值
	 */
	@RequestMapping(value = "/testRequestParam")
	public String testRequestParam(
			@RequestParam(value = "username") String un,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age) {
		System.out.println("testRequestParam, username: " + un + ", age: "
				+ age);
		return SUCCESS;
	}
```

#3.`@RequestHeader`绑定请求参数

```
	/**
	 * 了解: 映射请求头信息 
	 * 用法同 @RequestParam
	 */
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(
			@RequestHeader(value = "Accept-Language") String al) {
		System.out.println("testRequestHeader, Accept-Language: " + al);
		return SUCCESS;
	}
```	

#4.`@CookieValue`绑定CookValue值  

```
	/**
	 * 了解:
	 * 
	 * @CookieValue: 映射一个 Cookie 值. 属性同 @RequestParam
	 */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String sessionId) {
		System.out.println("testCookieValue: sessionId: " + sessionId);
		return SUCCESS;
	}
```
#5.`@ControllerAdvice`
是Spring3.2提供的新注解，从名字上可以看出大体意思是控制器增强。让我们先看看@ControllerAdvice的实现：
```java
package org.springframework.web.bind.annotation;
 
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ControllerAdvice {
 
@AliasFor("basePackages")
String[] value() default {};
 
@AliasFor("value")
String[] basePackages() default {};
 
Class<?>[] basePackageClasses() default {};
 
Class<?>[] assignableTypes() default {};
 
Class<? extends Annotation>[] annotations() default {};
```
##用来处理异常
`@ExceptionHandler`和`@ResponseStatus`我们提到，如果单使用`@ExceptionHandler`，只能在当前Controller中处理异常。但当配合`@ControllerAdvice`一起使用的时候，就可以摆脱那个限制了。
