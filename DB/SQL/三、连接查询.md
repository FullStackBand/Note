# 内连接
##1、笛卡尔积
* 语法
1. 无条件筛选 
```sql
select  列名
from 标名1，表名2，...
```
2. 有条件筛选
```sql
select  列名
from 标名1，表名2，...
where 中介列条件相等
and 其他条件
```
* 什么是笛卡尔积？
笛卡尔积就是将A和B表中的行任意组合，得到一个新的结果。
## 2、inner join
* 语法
1. inner join
```sql
select 列名
from 表名1
inner join 表名2
on 相等的条件
where 其他条件
```

##2. 笛卡尔积
```sql
select  列名
from 标名1，表名2，...
where 中介列条件相等
and 其他条件
```
#外连接
* 让找不到的数据显示为`null`
##1、 left join
* 左外连接
此种情况下，左方表的连接值在右方表中找不到时，则右方表中没有值的内容显示null
```sql
select 列名
from 左表
left outer join 右表
on 连接条件
where 其他条件
```
##2、 right join
* 右外连接
此种请况和左外连接相反
```sql
select 列名
form 左表
right outer join 右表
on 连接条件
where 其他条件
```

> oracle 特有的写法
> ```sql
>   select 列名
>   form 表1，表2
>   where 表1.条件列 = 表2.条件列(+)
>   -- (+) 表示，若没有数据不全为null值的表
>  ```
##3、 full join
* 完全外链接
任意一方表找不到数据，都显示为null
```sql
select 列名
from 表1
full outer join 表2
on 连接条件
where 其他条件
```
#三、其他连接
##1、 cross join
* 笛卡尔积
```sql
select 列名
form 表1
cross join 表2
where 等值条件
```
> 注：cross join 的作用本身不大，但是使用了where子句进行筛选，效果就很明显
##2、 natural join
* 自动连接（自然连接）
* 可以对两个表相同的列名（类型也要相同）自动判断等值连接
```sql
select 列名
form 表1
natural join 表2
where 其他条件
```
> 注：
> 1. 列的类型也要相等
> 2. 可以自动匹配多个字段
##3、 using join 子句
* 在 natural join 查询中
列名相同字段类型不同，则会反馈类型不匹配
此时可以通过在join子句后面使用using字句来设置，需要等值判断的字段。
```sql
select 列名
form 表1
join 表2
using(列名)
where 其他条件
```
