#处理模型数据
* Spring MVC 提供了以下几种途径输出模型数据
 * ModelAndView: 处理方法返回值类型为ModelAndView时, 方法体即可通过该对象添加模型数据
 * Map 及 Model: 入参为org.springframework.ui.Model、org.springframework.ui.ModelMap 或 java.uti.Map 时，处理方法返回时，Map 中的数据会自动添加到模型中。
 * @SessionAttributes: 将模型中的某个属性暂存到HttpSession 中，以便多个请求之间可以共享这个属性
 * ModelAttribute: 方法入参标注该注解后, 入参的对象就会放到数据模型中

##1. `ModelAndView` 模型与视图 
* 目标方法的返回值可以是 ModelAndView 类型。 
* 其中可以包含视图和模型信息
* SpringMVC 会把 ModelAndView 的 model 中数据放入到 request 域对象中. 
* 例子：

java代码
```
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView(){
		String viewName = SUCCESS;
		ModelAndView modelAndView = new ModelAndView(viewName);
		
		//添加模型数据到 ModelAndView 中.
		modelAndView.addObject("time", new Date());
		
		return modelAndView;
	}
```
jsp返回页面
```
//省略...
time: ${requestScope.time } //从请求域中获取time的值
//省略...
```
结果
```
time:(当前值...)
```

##2.  `Map`及`Model`入参(和1.中的效果一样，但是用法不一样)
* 目标方法可以添加 Map 类型(实际上也可以是 Model 类型或 ModelMap 类型)的参数. 
* 例子
java 代码
```
@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map){
		System.out.println(map.getClass().getName()); 
		map.put("names", Arrays.asList("Tom", "Jerry", "Mike"));
		return SUCCESS;
	}
```
jsp返回页面
```
//省略...
names: ${requestScope.names} //从请求域中获取
//省略...
```
结果
```
names:"Tom", "Jerry", "Mike"
```

##3. `@SessionAttributes` 暂存到HttpSession 中 
 * @SessionAttributes 除了可以通过属性名指定需要放到会话中的属性外(实际上使用的是` value `属性值)
 * 还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中(实际上使用的是 `types` 属性值)
 *  注意: 该注解`只能`放在`类的上面`. 而`不能修饰放方法`. 
```
@SessionAttributes(value={"user"}, types={String.class})
//指定 key 为 user 的对象 存入 Session 中
//指定 类型为 String.class 的对象也存入 Session 中
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String, Object> map){
		User user = new User("Tom", "123456", "tom@atguigu.com", 15);
		map.put("user", user);
		map.put("school", "atguigu");
		return SUCCESS;
	}
}
```
jsp返回页面
```
//省略...
	session user: ${sessionScope.user }
	<br><br>
//省略...
```
结果
```
user:（user对象的toString方法输出的值）
```
##4. `ModelAttribute` 自动放入模型中

###4.1运行流程
1. 执行 @ModelAttribute 注解修饰的方法: 从数据库中取出对象, 把对象放入到了 Map 中. 键为: user
2. SpringMVC 从 Map 中取出 User 对象, 并把表单的请求参数赋给该 User 对象的对应的为`null`的属性.
3. SpringMVC 把上述对象传入目标方法的参数. 
> 注意: 在 @ModelAttribute 修饰的方法中, 放入到 `Map` 时的`键`需要和`目标方法` `入参类型`的`第一个字母小写的字符串一致`!

###4.2`@ModelAttribute` 的使用
1. 有 @ModelAttribute 标记的方法, 会在每个目标方法执行之前被 SpringMVC 调用! 
2. @ModelAttribute 注解也可以来修饰目标方法 POJO 类型的入参, 其 value 属性值有如下的作用:
 1. SpringMVC 会使用 value 属性值在 implicitModel 中查找对应的对象, 若存在则会直接传入到目标方法的入参中.
 2. SpringMVC 会一 value 为 key, POJO 类型的对象为 value, 存入到 request 中. 
###4.3代码演示
#### java代码
* @ModelAttribute方法
```
	@ModelAttribute
	public void getUser(@RequestParam(value="id",required=false) Integer id, 
			Map<String, Object> map){
		System.out.println("modelAttribute method");
		if(id != null){
			//模拟从数据库中获取对象
			User user = new User(1, "Tom", "123456", "tom@atguigu.com", 12);
			System.out.println("从数据库中获取一个对象: " + user);
			
			map.put("user", user);
		}
	}
```
* 实际映射的方法
```
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(@ModelAttribule("user") User user){
		System.out.println("修改: " + user);
		return SUCCESS;
	}
```
####效果
* 前台没有对user 传入密码的值
* 通过`@ModelAttribute`的注解，标记在`testModelAttribute`之前运行了`getUser`方法获取了一个有完整数据的`user`
* 通过一系列的方法（后面会讲到）将前台传入的`user`对象进行比对，将`null`的属性补全后

###4.4SpringMVC 确定目标方法 POJO 类型入参的过程
1. 确定一个 `key`:
 1. 若目标方法的 POJO 类型的参数木有使用 `@ModelAttribute` 作为修饰, 则` key` 为 `POJO` 类名`第一个字母的小写`
 2. 若使用了  `@ModelAttribute` 来修饰, 则` key` 为 `@ModelAttribute` 注解的` value` 属性值. 
2. 在 `implicitModel` 中查找 `key` 对应的对象, 若存在, 则作为入参传入
 1. 若在 `@ModelAttribute` 标记的方法中在 `Map` 中`保存过`, 且 `key` 和 `1` 确定的 `key` 一致, 则会获取到. 
3. 若 `implicitModel` 中不存在 `key` 对应的对象, 则检查当前的 `Handler` 是否使用 `@SessionAttributes` 注解修饰, 若使用了该注解, 且 `@SessionAttributes` 注解的 `value` 属性值中包含了` key`, 则会从 `HttpSession` 中来获取 `key` 所 对应的` value` 值, 若存在则直接传入到目标方法的入参中. `若不存在则将抛出异常`. 
4. 若 `Handler` 没有标识 `@SessionAttributes` 注解或 `@SessionAttributes` 注解的 `value` 值中`不包含`  `key`, 则会通过反射来`创建 POJO `类型的参数, 传入为`目标方法的参数`
5. SpringMVC 会把 `key` 和 `POJO` 类型的对象保存到 `implicitModel` 中, 进而会保存到` request` 中. 
	 
###4.5源代码分析的流程
1. 调用 `@ModelAttribute` 注解修饰的方法. 实际上把 `@ModelAttribute` 方法中 `Map` 中的数据放在了 `implicitModel` 中.
2. 解析请求处理器的目标参数, 实际上该目标参数来自于 `WebDataBinder` 对象的` target` 属性
 1. 创建 `WebDataBinder` 对象:
		1. 确定 `objectName` 属性: 若传入的 `attrName` 属性值为` ""`, 则` objectName` 为类名`第一个字母小写`. 注意: `attrName`. 若目标方法的 `POJO` 属性使用了 `@ModelAttribute` 来修饰, 则` attrName` 值即为 `@ModelAttribute` 的 `value` 属性值 
		2. 确定 `target` 属性:在` implicitMode`l 中查找 `attrName` 对应的属性值. 若存在, `ok` 若不存在: 则验证当前 `Handler` 是否使用了 `@SessionAttributes` 进行修饰, 若使用了, 则尝试从` Session` 中获取 `attrName` 所对应的属性值. 若 `session` 中没有对应的属性值, 则抛出了`异常`. 若 `Handler` 没有使用` @SessionAttributes `进行修饰, 或 `@SessionAttributes` 中没有使用 `value` 值指定的` key`和 `attrName` 相匹配, 则通过`反射创建了 POJO 对象`
 2. SpringMVC 把表单的请求参数赋给了` WebDataBinder` 的` target` 对应的属性. 
 3. SpringMVC 会把` WebDataBinder` 的 `attrName` 和 `target `给到 `implicitModel`.近而传到 `request` 域对象中. 
 4. 把 `WebDataBinder` 的 `target` 作为参数传递给目标方法的入参. 
