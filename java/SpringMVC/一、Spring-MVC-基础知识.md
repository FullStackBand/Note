#Spring MVC 基础知识
![这里写图片描述](http://upload-images.jianshu.io/upload_images/13055171-8e1939ef1aab677a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##简介
* Spring MVC是 Spring 框架的一个模块,Spring MVC 无需和 Spring 进行整合.
* Spring MVC 是一个基于MVC的框架

> * MVC 是一个设计模式(B/S系统的应用) 
	* C (Controller) : 控制器
	* M (model) : 模型
		*  pojo 		
		*  action
		*  service 	
		*  dao
	* V (view) : 视图

* Spring MVC 的处理方式
	1. 发起请求到前端控制器(`DispatcherServlet`)
	2. 前端控制器请求`HandlerMapping`查找`Handler` (可以根据xml配置 , 注解进行查找)
	3. 处理器映射器`HandlerMapping`向前端控制器返回`Handler`
	4. 前端控制器调用处理适配器 `Handler`
	5. 处理器适配器去执行`Handler`
	6. `Handler` 执行完成给适配器返回`ModelAndView`
	6. 处理器适配器向前端控制器返回`ModelAndView`(`ModelAndView`是`Spring MVC`框架的一个底层对象,包括`Model`和`View`)
	7. 前端控制器请求视图解析器进行视图解析(根据逻辑视图名解析真正的视图(jsp))
	8. 视图解析器向前端控制器返回`View`
	9. 前台控制器进行视图渲染(视图渲染将模型数据(在`ModelAndView` 对象中))充填到`request`域
	10. 前端控制器向用户响应结果

##组件
###1.前端控制器`DispatcherServlet`
* 作用 : 接受请求,响应结果,相当于转发器
* 相当于一个中央处理器
* 有了`DispatcherServlet`减少了其他
###2.处理器映射器`HandlerMapping`
* 作用 : 根据请求的`url`查找`Handler`
###3.处理器适配器`handlerAdapter`
* 作用 : 按照特定规则(`HandlerAdapter`要求的规则)去执行`Handler`
* 注意 : 编写`Handler`时按照`HandlerAdapter`的要求去做,这样适配器才可以去正确执行`Handler`
###4.视图解析器`View resolver`
* 作用 : 进行视图解析,根据逻辑视图名解析成真正的视图(`View`)
###5.视图`View`
`View`是一个接口,实现类支持不同的`View`类型(`jsp` , `freemarker` ,` pdf` , ...)
