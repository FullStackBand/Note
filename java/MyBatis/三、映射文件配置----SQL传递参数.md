-------------------------------------------

##取值的区别
* `#{}` 和`${}`
	都可以获取Map集合或者pojo对象属性的值
###`#{}`预编译取值
* 是以预编译的形式,将参数设置到sql语句中.
#### 规定参数的规则
* `javaType`\ `jdbcType`\ `mode`存储过程\ `numericScale`\ `resultMap`\ `typeHandler`\ `jabcTypeName` \  `expression`(未来预留)



###`${}`字符串替换
* 取出的值直接拼装到sql语句中 , 但是会有一定的安全问题.
> 所以大多数情况,都是以 `#{ }` 方式取值
> 但是 表名 关键的原生的关键字,不支持预编译 和 占位符,所以只能使用 `${ }` 的方式取值

----------------------------------------------------

##SQL传递参数

###单个参数
* `#{参数名}`
默认取出参数
* SQL:
`select * from student where id=#{id} `
##sql
这个元素可以被用来定义可重用的 SQL 代码段，可以包含在其他语句中。它可以被静态地(在加载参数) 参数化. 不同的属性值通过包含的实例变化.
###多个参数
####参数索引
* 多个参数不能使用参数名的方法
`#{param1} ... #{paranmN}`
####参数注解
* 接口方法
```
public int getXxx  (
	@Param(id) 		Integer id , 
	@Param("name")	String name 
) ;
```
* SQL:
`select * from student where id=#{id} and name=#{name}`
###POJO对象参数
使用 `#{属性名}` 直接取出
###MAP参数
* 接口方法
```
public int getXxx  (
	Map<String,Object> map
) ;
```
* SQL
对应map集合中的key也可以
`#{key}`
* 但是要注入到MAP集合值,没有POJO的方便
如果经常使用一种参数可以封装一个**TO**(Transfer Object)进行传输
###混合参数
####1. 普通方法
* 接口方法
```
public int getXxx (
	Integer id , 
	Student st
);
```
* SQL
`select * from student where id=#{param1} and name=#{param2.name}`
####2. 注解方法
* 接口方法
```
public int getXxx (
	Integer id , 
	@Param("st")Student st
);
```
* SQL
`select * from student where id=#{param1} and name=#{st.name}`
####3. List | Set | 数组 参数
* SQL
	* List : #{List[0]}
	* Set : #{Set.key}
	* 数组:#{array[0]}

---------------------------------------------------------
