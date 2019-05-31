
# List-常用操作
## 1. lpush/rpush/lrange
* `LPUSH key value [value …]` 左插入
* `RPUSH key value [value …]` 右插入
* `LRANGE key start stop` 从左边开始取出 
## 2. lpop/rpop
* `lpop` 取最左边的
* `rpop` 取最右边的
## 3. lindex
* `LINDEX key index` 按照索引下标获取,从左到右
## 4. llen
* `LLEN key` 获取长度
## 5. lrem key
* `LREM key count value` 在key中删除count个value
## 6. lrem key 开始 结束 
* `LTRIM key start stop` 截取key中start到stop中的值,放入key
## 7. rpoplpush
* `RPOPLPUSH source destination` 把source的最右边,放在destination的最左边
## 8. lset key index value 
* `LSET key index value` 设置下标index的为value
## 9. liinsert key berfore/after pivot value 
* `LINSERT key BEFORE|AFTER pivot value` 将值 value 插入到列表 key 当中，位于值 pivot 之前或之后。
> 他是一个字符串链表,left\right都可以插入添加:

> 如果键不存在,创建新的链表

> 如果键已存在,新增内容 

> 如果值全部移除,键也消失

> 链表的操作,无论是头还是尾效率都是极高的,但是是对中间的元素进行操作,效率就是极低的.

# set - 常用操作
## 1. sadd/smenbers/sismenber
* `SADD key member [member …]` 将一个或多个` member `元素加入到集合 `key` 当中，已经存在于集合的 `member` 元素将被忽略。
* `SMEMBERS key` 返回集合 `key` 中的所有成员。
* `SISMEMBER key member` 判断 member 元素是否集合 key 的成员。
## 2. scard
* `SCARD key`返回集合 key 的基数(集合中元素的数量)。
## 3. srem
* `SREM key member [member …]`
    * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
## 4. srandmenber
* `SRANDMEMBER key [count]`
    * 如果命令执行时，只提供了 `key` 参数，那么返回集合中的一个随机元素。
    * 如果 `count` 为正数，且小于集合基数，那么命令返回一个包含 `count` 个元素的数组，数组中的元素各不相同。如果 `count` 大于等于集合基数，那么返回整个集合。
    * 如果 `count` 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 `count` 的绝对值。
## 5. spop key
* `SPOP key` 移除并返回集合中的一个随机元素。
## 6. smove 
* `SMOVE source destination member ` 将 member 元素从 source 集合移动到 destination 集合。 `原子性`
## 7. 数学集合
1. sdiff    差集
    * `SDIFF key [key …]`
    * 返回一个集合的全部成员，该集合是所有给定集合之间的差集。
    * 不存在的 key 被视为空集。
2. sinter   交集 
    * `SINTER key [key …]`
    * 返回一个集合的全部成员，该集合是所有给定集合的交集。
    * 不存在的 key 被视为空集。
3. sunion   并集
    * `SUNION key [key …]`
    * 返回一个集合的全部成员，该集合是所有给定集合的并集。
    * 不存在的 key 被视为空集。