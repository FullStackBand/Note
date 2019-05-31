---
typora-copy-images-to: ./
---

# 简介

在 Redis 复制的基础上，使用和配置主从复制非常简单，能使得从 Redis 服务器（下文称 `slave`）能精确得复制主 Redis 服务器（下文称 `master`）的内容。每次当 `slave` 和 `master` 之间的连接断开时， `slave` 会自动重连到 `master` 上，并且无论这期间 `master` 发生了什么， `slave` 都将尝试让自身成为 `master` 的精确副本。

这个系统的运行依靠三个主要的机制：

- 当一个 master 实例和一个 slave 实例连接正常时， master 会发送一连串的命令流来保持对 slave 的更新，以便于将自身数据集的改变复制给 slave ， ：包括客户端的写入、key 的过期或被逐出等等。
- 当 master 和 slave 之间的连接断开之后，因为网络问题、或者是主从意识到连接超时， slave 重新连接上 master 并会尝试进行部分重同步：这意味着它会尝试只获取在断开连接期间内丢失的命令流。
- 当无法进行部分重同步时， slave 会请求进行全量重同步。这会涉及到一个更复杂的过程，例如 master 需要创建所有数据的快照，将之发送给 slave ，之后在数据集更改时持续发送命令流到 slave 。

Redis使用默认的异步复制，其特点是高延迟和高性能，是绝大多数 Redis 用例的自然复制模式。但是，从 Redis 服务器会异步地确认其从主 Redis 服务器周期接收到的数据量。

# 使用

## 1. 配从不配主

## 2. 从库配置

   * `SLAVEOF host port`
   * [SLAVEOF](http://redisdoc.com/replication/slaveof.html#slaveof) 命令用于在 Redis 运行时动态地修改复制(replication)功能的行为。
   * 每次与master断开之后，都需要重新连接，除非你配置进redis.conf文件
   * `info replication` 查看当前主从状态

## 3. 配置文件细节操作

      1. 普通配置
            1. 拷贝多个redis.conf文件
            2. 开启`daemonize yes`
         3. pid (`pidfile /var/run/redisxxxxx.pid`)
         4. 日志文件(`logfile logxxxx.log`)
         5. rdb备份文件名称(`dumpxxxx.rdb`)
         6. 端口号(`port xxxx`)

## 4. 常用三招

   1. 一主二仆

      1. `info replication` 查看当前配置` 
      2. `slaveof xxx.xxx.xxx.xxx` 指定主机
      3. 只有主机可以写
      4. 主机死了,从机不变
      5. 主机重启,从机原地待命
      6. 从机死了,重启后从机丢失,除非写入配置文件

   2. 薪火相传

      1. 上一个Slave可以是下一个slave的Master，Slave同样可以接收其他
         slaves的连接和同步请求，那么该slave作为了链条中下一个的master,
         可以有效减轻master的写压力
      2. 中途变更转向:会清除之前的数据，重新建立拷贝最新的
      3. `slaveof 新主库IP 新主库端口`

      

   3. 反客为主

      * `SLAVEOF no one`
      * 使当前数据库停止与其他数据库的同步，转成主数据库





# 复制原理

1. slave启动成功连接到master后会发送一个sync命令
2. Master接到命令启动后台的存盘进程，同时收集所有接收到的用于修改数据集命令，
   在后台进程执行完毕之后，master将传送整个数据文件到slave,以完成一次完全同步
3. 全量复制：而slave服务在接收到数据库文件数据后，将其存盘并加载到内存中。
4. 增量复制：Master继续将新的所有收集到的修改命令依次传给slave,完成同步
5. 但是只要是重新连接master,一次完全同步（全量复制)将被自动执行



# 哨兵模式

> 自动版反客为主 
>
> 反客为主的自动版，能够后台监控主机是否故障，如果故障了根据投票数自动将从库转换为主库





## 1. 使用

1. 自定义的/myredis目录下新建`sentinel.conf`文件，名字绝不能错

2. 配置`sentinel.conf`

   ```properties
    sentinel monitor 被监控数据库名字(自己起名字) 127.0.0.1 6379 1
   ```

   

   上面最后一个数字1，表示主机挂掉后salve投票看让谁接替成为主机，得票数多少后成为主机

3. 启动哨兵

   * `redis-sentinel /myredis/sentinel.conf `

   * 上述目录依照各自的实际情况配置，可能目录不同

4. 主机挂了,哨兵自动切换到从机,主机重启变成从机

5. 一组sentinel可以同时监控多个Master

## 2. 缺点

* 复制延时

  由于所有的写操作都是先在Master上操作，然后同步更新到Slave上，所以从Master同步到Slave机器有一定的延迟，当系统很繁忙的时候，延迟问题会更加严重，Slave机器数量的增加也会使这个问题更加严重。