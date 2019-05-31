# redis 事务

## 1. 定义

可以一次执行多个命令，本质是一组命令的集合。一个事务中的
所有命令都会序列化，按顺序地串行化执行而不会被其它命令插入，不许加塞

## 2. 命令

1. [MULTI](http://redisdoc.com/transaction/multi.html) : 事务的开始

   1. 标记一个事务的开始

       事务块内的多条命令会按照先后顺序被放进一个队列当中，最后由 [EXEC](http://redisdoc.com/transaction/exec.html#exec) 命令原子性(atomic)地执行。 

   2. 返回值

      `ok`

   3. 示例

      ```cmd
      redis> MULTI            # 标记事务开始
      OK
      
      redis> INCR user_id     # 多条命令按顺序入队
      QUEUED
      
      redis> INCR user_id
      QUEUED
      
      redis> INCR user_id
      QUEUED
      
      redis> PING
      QUEUED
      
      redis> EXEC             # 执行
      1) (integer) 1
      2) (integer) 2
      3) (integer) 3
      4) PONG
      ```

      

2. [EXEC](http://redisdoc.com/transaction/exec.html) : 执行事务块

   1. 执行所有事务块内的命令

      假如某个(或某些) key 正处于 [WATCH](http://redisdoc.com/transaction/watch.html#watch) 命令的监视之下，且事务块中有和这个(或这些) key 相关的命令，那么 [EXEC](http://redisdoc.com/transaction/exec.html#exec) 命令只在这个(或这些) key 没有被其他命令所改动的情况下执行并生效，否则该事务被打断(abort)。 

   2. 返回值

      事务内,所有命令的返回值,并且按照顺序排列

   3. 示例

      ```cmd
      # 事务被成功执行
      
      redis> MULTI
      OK
      
      redis> INCR user_id
      QUEUED
      
      redis> INCR user_id
      QUEUED
      
      redis> INCR user_id
      QUEUED
      
      redis> PING
      QUEUED
      
      redis> EXEC
      1) (integer) 1
      2) (integer) 2
      3) (integer) 3
      4) PONG
      
      
      # 监视 key ，且事务成功执行
      
      redis> WATCH lock lock_times
      OK
      
      redis> MULTI
      OK
      
      redis> SET lock "huangz"
      QUEUED
      
      redis> INCR lock_times
      QUEUED
      
      redis> EXEC
      1) OK
      2) (integer) 1
      
      
      # 监视 key ，且事务被打断
      
      redis> WATCH lock lock_times
      OK
      
      redis> MULTI
      OK
      
      redis> SET lock "joe"        # 就在这时，另一个客户端修改了 lock_times 的值
      QUEUED
      
      redis> INCR lock_times
      QUEUED
      
      redis> EXEC                  # 因为 lock_times 被修改， joe 的事务执行失败
      (nil)
      ```

      

3. [DISCARD](http://redisdoc.com/transaction/discard.html) : 取消事务

   1. 取消事务,放弃所有的事务内的命令

      如果正在使用 `WATCH` 命令监视某个(或某些) key，那么取消所有监视，等同于执行命令 `UNWATCH` 。 

   2. 返回值

      总是`ok`

   3. 示例

      ```cmd
      redis> MULTI
      OK
      
      redis> PING
      QUEUED
      
      redis> SET greeting "hello"
      QUEUED
      
      redis> DISCARD
      OK
      ```

      

4. [WATCH](http://redisdoc.com/transaction/watch.html) : 监视事务

   1. 语法

      `WATCH key [key …] `

   2. 监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断。 

   3. 返回值

      总是返回`ok`

   4. 示例

      ```cmd
      redis> WATCH lock lock_times
      OK
      ```

      

5. [UNWATCH](http://redisdoc.com/transaction/unwatch.html) : 取消监视

   1. 语法

      `UNWATCH `

   2. 说明

      1. 取消 [WATCH](http://redisdoc.com/transaction/watch.html#watch) 命令对所有 key 的监视。
      2. 如果在执行 [WATCH](http://redisdoc.com/transaction/watch.html#watch) 命令之后， [EXEC](http://redisdoc.com/transaction/exec.html#exec) 命令或 [DISCARD](http://redisdoc.com/transaction/discard.html#discard) 命令先被执行了的话，那么就不需要再执行 [UNWATCH](http://redisdoc.com/transaction/unwatch.html#unwatch) 了。
      3. 因为 [EXEC](http://redisdoc.com/transaction/exec.html#exec) 命令会执行事务，因此 [WATCH](http://redisdoc.com/transaction/watch.html#watch) 命令的效果已经产生了；而 [DISCARD](http://redisdoc.com/transaction/discard.html#discard) 命令在取消事务的同时也会取消所有对 key 的监视，因此这两个命令执行之后，就没有必要执行 [UNWATCH](http://redisdoc.com/transaction/unwatch.html#unwatch) 了。

   3. 返回值

      总是`ok`

   4. 示例

      ```cmd
      redis> WATCH lock lock_times
      OK
      
      redis> UNWATCH
      OK
      ```

      

## 3. 使用

* 开启
  * 由`MULTI`开始一个事务
* 入队
  * 将多个命令入队到事务中，接到这些命令并不会立即执行，而是放到等待执行的事务队列里面
* 执行
  * 由`EXEC`命令触发事务

## 4.特性

 * 单独的隔离操作：事务中的所有命令都会序列化、按顺序地执行。事务在执行的过程中，不会被其他客户端发送来的命令请求所打断。
 * 没有隔离级别的概念：队列中的命令没有提交之前都不会实际的被执行，因为事务提交前任何指令都不会被实际执行，也就不存在”事务内的查询要看到事务里的更新，在事务外查询不能看到”这个让人万分头痛的问题.
 * 不保证原子性：redis同一个事务中如果有一条命令执行失败，其后的命令仍然会被执行，没有回滚



