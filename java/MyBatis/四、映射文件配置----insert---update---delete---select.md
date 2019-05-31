* insert – 映射插入语句
* update – 映射更新语句
* delete – 映射删除语句
* select – 映射查询语句
##select
查询语句是 MyBatis 中最常用的元素之一，光能把数据存到数据库中价值并不大，如果还能重新取出来才有用，多数应用也都是查询比修改要频繁。对每个插入、更新或删除操作，通常对应多个查询操作。这是 MyBatis 的基本原则之一，也是将焦点和努力放到查询和结果映射的原因。简单查询的 select 元素是非常简单的。

* 查找
```
<select id="getStudentById" parameterType="int" resultType="com.po.Student">
  SELECT * FROM PERSON WHERE ID = #{sid}
</select>
```
###insert, update 和 delete
数据变更语句 insert，update 和 delete 的实现非常接近：

* 插入
```
<insert id="save" parameterType="com.po.Student">
		insert into student values(seqstu.nextval,#{sname},#{sex},#{address},#{birthday})
	</insert>
```
* 修改
```
<update id="update" parameterType="com.po.Student">
		update student set sname=#{sname},sex=#{sex},address=#{address},birthday=#{birthday} where stuid=#{stuid}
	</update>
```
* 删除
```
<delete id="delById" parameterType="java.lang.Integer">
		delete from student where stuid=#{sid}
	</delete>
```
