#缓存
![20180729100708286.png](https://upload-images.jianshu.io/upload_images/13055171-142e538ad5c239d4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##一级缓存(本地缓存)
> SqlSession级别的缓存

* 一级缓存是一直开启的 ;
* 与数据库通一次会话期间查询的数据会放在本地缓存 ;
* 以后如果需要相同的数据,直接从缓存中获取 ;
* 一级缓存失效情况 :
	1. 不同的SqlSession
	2. 查询条件不同
	3. 查询期间执行了>增删改< 
##二级缓存(全局缓存)
> namespace接别的缓存,一个namespace对应一个二级缓存

###工作机制
1. 一个会话,查询一条数据,这个数据就会被放在当前会话的一级缓存中 ;
2. 如果会话关闭,一级缓存中的数据会被保存到二级缓存中,新的会话查询信息,就可以参照二级缓存 ;
###使用:

1. 开启
	2.  全局配置 `cacheEnabled`	全局地开启或关闭配置文件中的所有映射器已经配置的任何缓存。默认	`true`
	3. 映射配置`cache` – 给定命名空间的缓存配置。
####全局配置`cacheEnabled`
```
<settings>
	<!-- 开启二级缓存 -->
	<setting name=cacheEnabled" value="true"/>
</settings>
```
####映射配置`cache`
```
<cache
  eviction="FIFO"
  flushInterval="60000"
  size="512"
  readOnly="true"/>
```
* 映射语句文件中的所有 select 语句将会被缓存。
* 映射语句文件中的所有 insert,update 和 delete 语句会刷新缓存。
* 缓存会使用 Least Recently Used(LRU,最近最少使用的)算法来收回。
* 根据时间表(比如 no Flush Interval,没有刷新间隔), 缓存不会以任何时间顺序 来刷新。
* 缓存会存储列表集合或对象(无论查询方法返回什么)的 1024 个引用。
* 缓存会被视为是 read/write(可读/可写)的缓存,意味着对象检索不是共享的,而 且可以安全地被调用者修改,而不干扰其他调用者或线程所做的潜在修改。

* `eviction`:缓存回收策略
 * `LRU`(默认):最近最少使用的,移除长时间不使用的;
 * `FIFO`:先进先出,按对象进入缓存的顺序移除;
 * `SOFT`:软引用,移除基于垃圾回收器状态和软引用规则的对象;
 * `WEAK`:弱引用更积极地移除基于垃圾收集器状态和弱引用规则的对象
* `flushInterval`(默认不清空):缓存刷新间隔(毫秒)
* `readOnly`(默认 false):
 * 只读  直接返回引用地址,不安全速度快.
 * 非只读 复制一份数据,返回,更安全
*  `size`:缓存存放多少元素
*  `type`:指定全类名 > type 属 性指 定的 类必 须实现 org.mybatis.cache.Cache 接口。这个接口是 MyBatis 框架中很多复杂的接口之一,但是简单 给定它做什么就行。
####增删改查的属性
* flushCache	将其设置为 true，任何时候只要语句被调用，都会导致本地缓存和二级缓存都会被清空，默认值：false。

* useCache	将其设置为 true，将会导致本条语句的结果被二级缓存，默认值：对 select 元素为 true。
* 默认值:
```
<select ... flushCache="false" useCache="true"/>
<insert ... flushCache="true"/>
<update ... flushCache="true"/>
<delete ... flushCache="true"/>
```
####参照缓存
回想一下上一节内容, 这个特殊命名空间的唯一缓存会被使用或者刷新相同命名空间内 的语句。也许将来的某个时候,你会想在命名空间中共享相同的缓存配置和实例。在这样的 情况下你可以使用 cache-ref 元素来引用另外一个缓存。
```
<cache-ref namespace="com.someone.application.data.SomeMapper"/>
```

