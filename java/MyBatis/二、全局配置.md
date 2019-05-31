[详情(官方中文文档)](http://mybatis.github.io/mybatis-3/zh/configuration.html#environments)

---------------------------------

##全局配置
###一 根标签
`configuration`根标签
###1.引入外部配置
`properties` 引入外部配置文件
```
<properties resource="dbconfig.propertise"></properties>
```
-----------------------------------

###2. setting 其他设置
```
<settings>
	<setting name="_" value="_" />
</settings>
```

####2.1驼峰命名法
`name`=`"mapUnderscoreToCamelCase"`
`value`=`"true"`

----------------------------

###2.2别名处理
* 可以为java类型起别名
* 别名不区分大小写
```
<typeAliases>
	...
</tpeAliases>
```
####2.2.1逐个别名
```
<typeAlias type="com.po.Student" alias="Student"/>
```
###2.2.2批量别名
```
<!-- 批量别名,使用类名作为别名 -->
<package name="com.po"/>
```
###2.2.3批量别名(指定别名)
* 类名重复时
	* 使用@Alias注解为类指定别名
####已经默认的别名
```
Alias		Mapped Type
_byte		byte
_long		long
_short 		short
_int 		int
_integer 	int
_double 	double
_float 		float
_boolean 	boolean
string 		String
byte 		Byte
long 		Long
short 		Short
int 		Integer
integer 	Integer
double 		Double
float 		Float
boolean 	Boolean
date 		Date
decimal 	BigDecimal
bigdecimal 	BigDecimal
object 		Object
map 		Map
hashmap 	HashMap
list 		List
arraylist 	ArrayList
collection 	Collection
iterator 	Iterator
```
----------------------------
###3. MyBatis环境`environments`
####环境`environment`
* 属性 : `id=""`表示
* 标签
	* `<transactionManager type="JDBC|MANAGED" />` 事务管理器 
	* `<dataSource type="UNPOOL|POOLED|JNDI" />`


#### 3.1数据库`JDBC` & `POOLED`

`${jdbc.driver}`使用这种形式获取外部配置文件的值
```
<environments default="development">
		<environment id="development">
			<transactionManager type="JDBC" />
			<dataSource type="POOLED">
				<property name="driver" value="${jdbc.driver}" />
				<property name="url" value="${jdbc.url}" />
				<property name="username" value="${jdbc.usersname}" />
				<property name="password" value="${jdbc.password}" />
			</dataSource>
		</environment>
	</environments>
```

###4. 映射器`mappers`
* `resource`
	* 引用类路径下的sql映射文件
* `url`
	* 引用网络路径或者磁盘路径下的sql映射文件
		    `file:///var/mapper/XxxMapper.xml
* `class`
	* 引用(注册)接口
	1. **映射文件和接口必须同名同目录**
	2. 注解的形式
		例如:
```
@Select("select * from student")
方法...
```
####4.1 类路径注册
```
<!-- 使用相对于类路径的资源引用 -->
<mappers>
  <mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
  <mapper resource="org/mybatis/builder/BlogMapper.xml"/>
  <mapper resource="org/mybatis/builder/PostMapper.xml"/>
</mappers>
```
####4.2URL路径注册
```
<!-- 使用完全限定资源定位符（URL） -->
<mappers>
  <mapper url="file:///var/mappers/AuthorMapper.xml"/>
  <mapper url="file:///var/mappers/BlogMapper.xml"/>
  <mapper url="file:///var/mappers/PostMapper.xml"/>
</mappers>
```
####4.3 指定接口注册
```
<!-- 使用映射器接口实现类的完全限定类名 -->
<mappers>
  <mapper class="org.mybatis.builder.AuthorMapper"/>
  <mapper class="org.mybatis.builder.BlogMapper"/>
  <mapper class="org.mybatis.builder.PostMapper"/>
</mappers>
```
####4.4 批量注册
1. 接口名称和xml名称必须一模一样
2. 批量时 xml 和 接口必须 放在同包里

> 例如 : 
		> IUsersMapper.xml
		> IUsersMapper.java

```
<!-- 将包内的映射器接口实现全部注册为映射器 -->
<mappers>
  <package name="org.mybatis.builder"/>
</mappers>
```

> 批量时 xml 和 接口必须 放在同包里


------------------------------------

##懒加载 :全局配置设置

* `lazyLoadingEnabled`	延迟加载的全局开关。当开启时，所有关联对象都会延迟加载．
* `aggressiveLazyLoading`	当开启时，任何方法的调用都会加载该对象的所有属性。否则，每个属性会按需加载．
```
<settings>
	<!-- 延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。
		默认值:false	
	 -->
	<setting name="lazyLoadingEnabled	" value="true"/>
	<!-- 当开启时，任何方法的调用都会加载该对象的所有属性。
		默认值:false	
	 -->
	<setting name="aggressiveLazyLoading" value="false"/>
</settings>
```
