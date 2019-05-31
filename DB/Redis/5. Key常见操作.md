> [Redis 命令参考(**官方中文**)  -  http://redisdoc.com/index.html](http://redisdoc.com/index.html)
# Key
## 1. 查看key 
1. 语法
    
* `KEYS pattern`
    * `KEYS *` 匹配数据库中所有 `key` 。
    * `KEYS h?llo` 匹配 `hello` ， `hallo` 和 `hxllo` 等。
    * `KEYS h*llo` 匹配 `hllo` 和 `heeeeello` 等。
    * `KEYS h[ae]llo` 匹配 `hello` 和 `hallo` ，但不匹配 `hillo` 。
        
2. 返回值
     * 符合给定模式的 key 列表。
3. 代码示例

```        
        redis> MSET one 1 two 2 three 3 four 4  # 一次设置 4 个 key
        OK

        redis> KEYS *o*
        1) "four"
        2) "two"
        3) "one" 

        redis> KEYS t??
        1) "two"

        redis> KEYS t[w]*
        1) "two"

        redis> KEYS *  # 匹配数据库内所有 key
        1) "four"
        2) "three"
        3) "two"
        4) "one"
```

        

> KEYS 的速度非常快，但在一个大的数据库中使用它仍然可能造成性能问题，如果你需要从一个数据集中查找特定的 `key` ，你最好还是用 Redis 的集合结构(set)来代替。

## 2. 判断是否存在
1. 语法
    * `EXISTS key`
        检查给定 `key` 是否存在。
2. 返回值
    * 若 key 存在，返回 1 ，否则返回 0 。
3. 代码示例

```

redis> SET db "redis"OK

redis> EXISTS db(integer) 1

redis> DEL db(integer) 1

redis> EXISTS db(integer) 0
```


## 3. 移动至库
1. 语法
    * `MOVE key db`
    将当前数据库的 `key` 移动到给定的数据库 `db` 当中。
    如果当前数据库(源数据库)和给定数据库(目标数据库)有相同名字的给定 `key` ，或者 `key` 不存在于当前数据库，那么 `MOVE` 没有任何效果。
    因此，也可以利用这一特性，将 `MOVE` 当作锁(locking)原语(primitive)。
 2. 返回值
     * 移动成功返回 `1` ，失败则返回 `0` 。
 3. 代码示例
```
# key 存在于当前数据库

redis> SELECT 0                             # redis默认使用数据库 0，为了清晰起见，这里再显式指定一次。OK

redis> SET song "secret base - Zone"OK

redis> MOVE song 1                          # 将 song 移动到数据库 1(integer) 1

redis> EXISTS song                          # song 已经被移走(integer) 0

redis> SELECT 1                             # 使用数据库 1OK

redis:1> EXISTS song                        # 证实 song 被移到了数据库 1 (注意命令提示符变成了"redis:1"，表明正在使用数据库 1)(integer) 1


# 当 key 不存在的时候

redis:1> EXISTS fake_key(integer) 0

redis:1> MOVE fake_key 0                    # 试图从数据库 1 移动一个不存在的 key 到数据库 0，失败(integer) 0

redis:1> select 0                           # 使用数据库0OK

redis> EXISTS fake_key                      # 证实 fake_key 不存在(integer) 0


# 当源数据库和目标数据库有相同的 key 时

redis> SELECT 0                             # 使用数据库0OKredis> SET favorite_fruit "banana"OK

redis> SELECT 1                             # 使用数据库1OKredis:1> SET favorite_fruit "apple"OK

redis:1> SELECT 0                           # 使用数据库0，并试图将 favorite_fruit 移动到数据库 1OK

redis> MOVE favorite_fruit 1                # 因为两个数据库有相同的 key，MOVE 失败(integer) 0

redis> GET favorite_fruit                   # 数据库 0 的 favorite_fruit 没变"banana"

redis> SELECT 1OK

redis:1> GET favorite_fruit                 # 数据库 1 的 favorite_fruit 也是"apple"
```
 
## 4. 设置过期时间
1. 语法
    * `EXPIRE key seconds`


* 为给定 `key` 设置生存时间，当 `key` 过期时(生存时间为 0 )，它会被自动删除。
    
* 在 Redis 中，带有生存时间的 `key` 被称为『易失的』(volatile)。
    
* 生存时间可以通过使用 `DEL` 命令来删除整个 `key` 来移除，或者被 `SET` 和 `GETSET` 命令覆写(overwrite)，这意味着，如果一个命令只是修改(alter)一个带生存时间的 `key` 的值而不是用一个新的 `key` 值来代替(replace)它的话，那么生存时间不会被改变。
    
* 比如说，对一个 `key` 执行 `INCR` 命令，对一个列表进行 `LPUSH` 命令，或者对一个哈希表执行 `HSET` 命令，这类操作都不会修改 `key` 本身的生存时间。
    
* 另一方面，如果使用 `RENAME` 对一个 `key` 进行改名，那么改名后的 `key` 的生存时间和改名前一样。
    
* RENAME 命令的另一种可能是，尝试将一个带生存时间的 `key` 改名成另一个带生存时间的 `another_key` ，这时旧的 `another_key` (以及它的生存时间)会被删除，然后旧的 `key` 会改名为 `another_key` ，因此，新的 `another_key` 的生存时间也和原本的 key 一样。
    
* 使用 `PERSIST` 命令可以在不删除 `key` 的情况下，移除 `key` 的生存时间，让 `key` 重新成为一个『持久的』(persistent) key 。
2. 更新生存时间

* 可以对一个已经带有生存时间的 `key` 执行 `EXPIRE` 命令，新指定的生存时间会取代旧的生存时间。

3. 过期时间的精确度


* 在 Redis 2.4 版本中，过期时间的延迟在 1 秒钟之内 —— 也即是，就算 key 已经过期，但它还是可能在过期之后一秒钟之内被访问到，而在新的 Redis 2.6 版本中，延迟被降低到 1 毫秒之内。

4. 返回值

* 设置成功返回 1 。 当 key 不存在或者不能为 key 设置生存时间时(比如在低于 2.1.3 版本的 Redis 中你尝试更新 key 的生存时间)，返回 0 。

5. 代码示例

```
redis> SET cache_page "www.google.com"OK

redis> EXPIRE cache_page 30  # 设置过期时间为 30 秒(integer) 1

redis> TTL cache_page    # 查看剩余生存时间(integer) 23

redis> EXPIRE cache_page 30000   # 更新过期时间(integer) 1

redis> TTL cache_page(integer) 29996
```
 

6. 模式:导航会话

假设你有一项 web 服务，打算根据用户最近访问的 N 个页面来进行物品推荐，并且假设用户停止阅览超过 60 秒，那么就清空阅览记录(为了减少物品推荐的计算量，并且保持推荐物品的新鲜度)。这些最近访问的页面记录，我们称之为『导航会话』(Navigation session)，可以用 `INCR` 和 `RPUSH`命令在 Redis 中实现它：每当用户阅览一个网页的时候，执行以下代码：
```
MULTI
    RPUSH pagewviews.user:<userid> http://.....
    EXPIRE pagewviews.user:<userid> 60
 EXEC
```
如果用户停止阅览超过 60 秒，那么它的导航会话就会被清空，当用户重新开始阅览的时候，系统又会重新记录导航会话，继续进行物品推荐。
## 5. 查看剩余过期时间
1. 语法
    * `TTL key`
    
以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。    

2. 返回值

当 `key` 不存在时，返回 `-2` 。 当 `key` 存在但没有设置剩余生存时间时，返回 `-1` 。 否则，以秒为单位，返回 `key` 的剩余生存时间。

> 在 Redis 2.8 以前，当 key 不存在，或者 key 没有设置剩余生存时间时，命令都返回 -1 。
3. 代码示例

```
# 不存在的 key

redis> FLUSHDBOK

redis> TTL key(integer) -2


# key 存在，但没有设置剩余生存时间

redis> SET key valueOK

redis> TTL key(integer) -1


# 有剩余生存时间的 key

redis> EXPIRE key 10086(integer) 1

redis> TTL key(integer) 10084
```

## 6. 查看key是什么类型
1. 语法
    * `TYPE key`

返回 key 所储存的值的类型。

2. 返回值

* `none` (key不存在)
* `string` (字符串)
* `list` (列表)
* `set` (集合)
* `zset` (有序集)
* `hash` (哈希表)
* `stream` （流）

3. 代码示例
```

# 字符串

redis> SET weather "sunny"OK

redis> TYPE weatherstring


# 列表

redis> LPUSH book_list "programming in scala"(integer) 1

redis> TYPE book_listlist


# 集合

redis> SADD pat "dog"(integer) 1

redis> TYPE patset
```
    
