-----------------------

* if (判断)
* choose(分支选择)
	* when
	* otherwise
* trim
	* where
	* set
* foreach
* ******************************

#if
* test : 判断表达式([OGNL](https://baike.baidu.com/item/OGNL/10365326))
* 从参数中取值判断
### 普通的
```
	<select id="findByParamIf" resultType="us" parameterType="us">
		SELECT * FROM users 
		WHERE
		<!-- test 判断表达式(OGNL表达式) -->
			<if test="uid!=null">
				uid=#{uid}
			</if>
			<!-- 并且不等于空字符串 (& >> &quot;) -->
			<if test="uname!=null and uname!=''">
				and uname = #{uname}
			</if>
			<if test="birthday!=null">
				and birthday=#{birthday}
			</if>
	</select>
```
###`<where>`封装查询条件
```
<select id="findByParamIf" resultType="us" parameterType="us">
		<!-- 
			查询的时候,缺失第一个条件是会出现SQL拼错
			解决方法 一 :
				* 给where 加上 1=1 之后的条件都可以 and XXX=XXX 
			解决方法 二 :
				* 使用<where> Xxx = Xxx </where>
			-->
		SELECT * FROM users
		WHERE 1=1
		<!-- test 判断表达式(OGNL表达式) -->
			<if test="uid!=null">
				and uid=#{uid}
			</if>
			<!-- 并且不等于空字符串 (&:&quot;) -->
			<if test="uname!=null and uname!=''">
				and uname like #{uname}
			</if>
			<if test="birthday!=null">
				and birthday=#{birthday}
			</if>
	</select>
```
#`<trim>`
* `prefix="" ` : 添加sql的前缀
* `prefixOverrides="" ` 指定去掉前边多余的字符串
* `suffix="" ` sql的后缀
* `suffixOverrides=""`指定去掉后面多余的字符串
#choose
* 类似于分支语句 switch
```
	<!-- 查询 携带了哪个就只用哪个查(一次只能匹配一个参数) -->
	<select id="findByParamChoose" resultType="us" parameterType="us">
		SELECT * FROM users WHERE 1=1
			<!-- 只会选择一个参数查询 -->
			<choose>
				<!-- 第一步 -->
				<when test="uid!=null">
					and uid=#{uid}
				</when>
				<!-- 第二步 -->
				<when test="uname!=null and uname!=''">
					and uname like #{uname}
				</when>
				<otherwise>
					<!-- 其他情况 -->
				</otherwise>
			</choose>
	</select>
```
#set
* 更新
* 也可以使用trim 进行处理 
```
	<!-- 修改 -->
	<update id="update" parameterType="us">
		<!-- 根据id 选择更新 -->
		UPDATE users
		<!-- 写更新的时候有可能多出逗号 -->
		<set>
			<if test="uname!=null">
				uname=#{uname},
			</if>
			<if test="birthday!=null">
				birthday=#{birthday},
			</if>
		</set>
		WHERE uid=#{uid}
	</update>
```
#foreach

* `collection`:指定输入对象中的集合属性的名称
* `item`:每次遍历生成的对象
* `open`:开始遍历时的拼接字符串
* `eparator`:遍历对象之间需要拼接的字符串
##范围查找
```
<!-- 动态 SQL 的另外一个常用的操作需求是对一个集合进行遍历，通常是在构建 IN 条件语句的时候(指定范围)
		  用 foreach 来改写 select * from user where id in (1,2,3)
	 -->
	<select id="findByParamForeach" resultType="us" parameterType="lsit">
		SELECT * FROM users  
		WHERE id 
		IN
		<!--
            collection:指定输入对象中的集合属性的名称
            item:每次遍历生成的对象
            open:开始遍历时的拼接字符串
            close:结束时拼接的字符串
            separator:遍历对象之间需要拼接的字符串
		-->
		<foreach collection="ids" item="id" open="(" separator="," close=")">
			id=#{id}
		</foreach>
	</select>
```
##批量插入
* 注意如果使用 `${ }` 需要加 单引号 `'${}'`
```
<!-- 批量保存 -->
	<insert id="saveList">
	INSERT INTO test.users (uname, birthday) 
	VALUES
		<foreach collection="list" item="us" open="" separator="," close="">
			(#{us.uname} , #{us.showBirthday})
		</foreach>
	</insert>
```
#参数
##`databaseId=""`
* 代表当前数据库的别名 
> 配合if判断数据库
> `<if test="databaseId == "mysql">`
##`parameter=""`
* 单个参数 : parameter就是这个参数
* 多个参数 : 参数会被封装为一个map,parameter就代表这个map
> 配合if判断是否传入参数
> `<if test="parameter != null>`
##`bind`
* 可以将OGNL表达式绑定到一个变量中,方便后来引用
* 强行拼`#{}`的参数,(因为`#{}`是不可以拼字符串的),但是`${}`可以拼字符串(但是`${}`相对是不安全的,所以不建议使用)
```
<bind name="_uname" value="'%'+uname+'%'">
```
##`sql` 和 `include`
* `sql`抽取重复使用的sql语句片段
* `include`插入指定的`sql`
####声明
```
<sql id="insert_select">select * from</sql>
```
####插入
```
<include refid="insert_select"/>
```

