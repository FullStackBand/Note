[详细参见官方中文文档](http://www.mybatis.org/mybatis-3/zh/sqlmap-xml.html)

-----------------------------------------

#`resultMap`自定义结果
* `<resultMap>` : 结果集
	* `id="" `唯一标识
	* `type="" ` : 数据类型
	* `<id>` :  – 一个 ID 结果;标记出作为 ID 的结果可以帮助提高整体性能
		* `column="" ` : 数据库中的列名
		* `property="" ` : 映射到列结果的字段或属性
	* `<result>` :  – 注入到字段或 JavaBean 属性的普通结果
		* 属性和id一样
	* `<association>` :  – pojo对象
嵌套结果映射 – 关联可以指定为一个 resultMap 元素，或者引用一个
		* `property="" ` : 映射到列结果的字段或属性
		* `javaType=""` : 一个 Java 类的完全限定名,或一个类型别名(参考上面内建类型别名的列 表) .
		* `jdbcType=""` : 在这个表格之前的所支持的 JDBC 类型列表中的类型
		* `select=""` : 映射另一个对应查询`id` `(如果使用联合查询可以不指定)`
		* `column=""` : 指定`<association>`中的参数查询
	* `<collection>`  : – pojo对象的集合
		* 大部分和`<association>`相同
		* `property="" ` : 指定`<association>`中的集合结果
		* `javaType="" ` : 指定集合类型
		* `ofType=""` : 指定集合元素类型
		* `column="id" ` : 指定`<association>`中的参数查询
		* `select=""`映射另一个对应查询`id` `(如果使用联合查询可以不指定)`
		* `<id>`|`<result>` : 标签同上
	* `<discriminator>`  : – 使用结果值来决定使用哪个 resultMap
		* `javaType` : 列值对应的java类型
		* `column` : 制定判断的列名
		 * `<case>`  :  基于某些值的结果映射
			 * `value` : 对比的值
			 * `resultMap`  : 指定封装的结果类型

##简单结果


```
<!-- 自定义某个javaBean的封装规则 
	type:自定义规则的 java 的类型
	id:唯一id 方便引用
	-->
<resultMap type="com.po.Student" id="student">
	<id column="sid" property="sid"/>
	<result column="sname" property="studentname"/>
	...
</resultMap>
```
###联合查询

* 查询学生的时候,同时查询该学生的班级信息
####1. 级联属性(`clazz.cname`)

```
<resultMap type="com.po.Student" id="student">
	<id column="sid" property="sid"/>
	<result column="sname" property="studentname"/>
	...
	<result column="cid" property="clazz.cid" />
	<result column="cname" property="clazz.cname"/>
</resultMap>
```

####2. 指定类型`association` 

* `association`指定返回类型中的类型

```
<resultMap type="com.po.Student" id="student">
	<id column="sid" property="sid"/>
	<result column="sname" property="studentname"/>
	...
	<association property="clazz" javaType="com.po.Clazz">
		<id column="cid" property="cid" />
		<result column="cname" property="cname"/>
	</association>
</resultMap>
```

####指定类型,并调用查询方法

* 同时查询时使用的`resultMap`

```
<resultMap type="com.po.Student" id="student">
	<id column="sid" property="sid"/>
	<result column="sname" property="studentname"/>
	<result column="sid" property="sid"/>
	...
	<!--指定查询方法 
		select="getClazzById" 
		指定查询数据
		column="cid"
		-->
	<association 
	property="clazz"
	javaType="com.po.Clazz"
	select="getClazzById"
	column="cid"
	>
		<id column="cid" property="cid" />
		<result column="cname" property="cname"/>
	</association>
</resultMap>
```
* 结果会先查询学生信息
* 在根据cid的值查询班级
* 并封装到对象中
####指定集合,并调用查询方法

```
<resultMap type="com.po.Clazz" id="clzz">
	<id column="cid" property="cid"/>
	<result column="cname" property="cname"/>
	<!--指定查询方法 
		select="getClazzById" 
		指定查询数据
		column="cid"
		-->
	<collection property="posts" ofType="domain.blog.Post">
		<id property="id" column="post_id"/>
		<result property="subject" column="post_subject"/>
	</collection>
</resultMap>
```

##鉴别器
* 有时候一个单独的数据库查询也可以返回很多不同的数据类型,鉴别器就是用来处理这种情况的
* 它的表现和java中的switch特别相似.
