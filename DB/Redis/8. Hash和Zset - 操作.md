# Hash
## ==1. hset/hget/hmset/hmget/hgetall/hdel==
* `HSET hash field value`将哈希表 hash 中域 field 的值设置为 value 。
* `HGET hash field` 返回哈希表中给定域的值。
* `HMSET key field value [field value …]` 同时将多个 field-value (域-值)对设置到哈希表 key 中。
* `HMGET key field [field …]`返回哈希表 key 中，一个或多个给定域的值。
* `HGETALL key` 返回哈希表 key 中，所有的域和值。
* `HDEL key field [field …]` 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
## 2. hlen
* `HLEN key` 返回哈希表 key 中域的数量。
## ==3. hkeys/hvals==
* `HKEYS key` 返回哈希表 key 中的所有域。
* `HVALS key` 返回哈希表 key 中所有域的值。
## 4. hincrby/hincrbyfolat
* `HINCRBY key field increment` 为哈希表 key 中的域 field 的值加上增量 increment 。
* `HINCRBYFLOAT key field increment` 为哈希表 key 中的域 field 加上浮点数增量 increment 。
## 5. hsetnx
* `HSETNX hash field value` 当且仅当域 field 尚未存在于哈希表的情况下， 将它的值设置为 value 。
# Zset
> 
## 1. zadd/zrange [withscores]
* `ZADD key score member [[score member] [score member] …]` 
    * 将一个或多个 `member` 元素及其 `score` 值加入到有序集 `key` 当中。
    * 如果某个 `member` 已经是有序集的成员，那么更新这个 `member` 的 score 值，并通过重新插入这个 `member` 元素，来保证该 `member` 在正确的位置上。
    * `score` 值可以是整数值或双精度浮点数。
    * 如果 `key` 不存在，则创建一个空的有序集并执行 `ZADD` 操作。
*  `ZRANGE key start stop [WITHSCORES]` 
    *  返回有序集 key 中，指定区间内的成员。
    *  其中成员的位置按 score 值递增(从小到大)来排序。
    *  具有相同 score 值的成员按字典序(lexicographical order )来排列。
    *  下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
    *  超出范围的下标并不会引起错误。 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
    *  可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
## 2. zrangebyscore key
* `ZREVRANGEBYSCORE key max min [WITHSCORES] [LIMIT offset count]`
    * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。有序集成员按 score 值递减(从大到小)的次序排列。
    * 具有相同 score 值的成员按字典序的逆序(reverse lexicographical order )排列。
    * 可选的 LIMIT 参数指定返回结果的数量及区间(就像SQL中的 SELECT LIMIT offset, count )，注意当 offset 很大时，定位 offset 的操作可能需要遍历整个有序集，此过程最坏复杂度为 O(N) 时间
## 3. zrem key
* `ZREM key member [member …]`
    * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
## 4. zcard/zcount key score / zrank key values
* `ZCARD key`
    * 返回有序集 key 的基数。
* `ZCOUNT key min max`
    * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量。
* `ZRANK key member`
    * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。
## 5. zrevrank key values
## 6. zrevrange
## 7. zrevrangebyscore key