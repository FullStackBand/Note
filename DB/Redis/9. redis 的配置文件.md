# units 单位

1. 配置大小单位,开头定义了一些基本的度量单位，只支持bytes，不支持bit
2. 对大小写不敏感
# include 包含
1. 和我们的Struts2配置文件类似，可以通过includes包含，redis.conf可以作为总闸，包含其他
# general 通用
1. daemonize 守护进程运行
    * 默认情况下，Redis不作为守护进程运行。如果你需要，用“是”。
    * 注意，Redis将在/var/run/ Redis中写入pid文件。当监控pid。
2. pidfile 指定pid文件路径
    * 当服务器运行非守护进程化时，如果没有pid文件，则不会创建pid文件
    * 在配置中指定。当服务器被守护时，pid文件即使没有指定，默认也使用`“/var/run/redis.pid”`。
3. port 端口号
    * 接受指定端口上的连接，默认值为6379 (IANA #815344)。
    * 如果端口0被指定，Redis将不会监听TCP套接字。
4. tcp-backlog 连接队列
    * 在高并发的环境中，您需要一个高backlog值,来避免客户端连接慢的问题
5. timeout 空闲关闭连接
    * 在客户端空闲N秒后关闭连接(0表示禁用)
6. bind
7. tcp-keepalive TCP保持活动
    * 如果非零，使用SO_KEEPALIVE在客户端不存在时发送TCP ack的沟通。这样做有两个原因:
        1. 检测死对等点。
        2. 从中间的网络设备的角度来看待活连接
    * 在Linux上，指定的值(以秒为单位)是用于发送ack的周期。注意，要关闭连接，需要双倍的时间。对于其他内核，周期取决于内核配置。
    * 最新的合理值为`300`
> * seq和ack号存在于TCP报文段的首部中，seq是序号，ack是确认号，大小均为4字节。
8. loglevel 日志级别
    *  指定服务器的详细级别:
        1.   `debug`(很多信息，对开发/测试有用)
        2.   `verbose`(许多很少有用的信息，但不像调试级别那样混乱)
        3.   notice(适度冗长，可能是您在生产中想要的)
        4.   warning(只记录非常重要/关键的消息)
9. logfile 指定日志文件
    * 指定日志文件名。同样，空字符串也可以用来强制
10. syslog-enabled
    * no/yes 关闭/打开系统日志
11. syslog-ident
    * 指定日志名称
12. syslog-facility
    * 指定syslog设备。必须是USER或介于LOCAL0-LOCAL7之间。
13. databases
    *　设置数据库数量
# snapshotting 快照
1. save
2. stop-writes-on-bgsave-error
3. rdbcompression
4. rdbchecksum
5. dbfilename
6. dir
# replication 复制
# security 安全
1. (cli 命令)
    1. `config get requirepass`
        * 获取密码
    2. `config get dir`
        * 在什么路径启动
    3. `config set requirepass '123456'`
        * 设置密码为123456
    4. `auth 123456`
        * 密码认证
    5. `config set requirepass ''`
        * 撤销设置密码
# limits 限制
1. maxclients 最大连接数
    * 默认为10000
    * 最小为32
    * 超出设置返回错误
2. maxmemory 最大内存
3. maxmemory-policy 最大内存的策略
    * 用来处理内存
    1. `volatile-lru`(least recently used):最近最少使用算法，从设置了过期时间的键中选择空转时间最长的键值对清除掉；
    2. `volatile-lfu`(least frequently used):最近最不经常使用算法，从设置了过期时间的键中选择某段时间之内使用频次最小的键值对清除掉；
    3. `volatile-ttl`:从设置了过期时间的键中选择过期时间最早的键值对清除；
    4. `volatile-random`:从设置了过期时间的键中，随机选择键进行清除；
    5. `allkeys-lru`:最近最少使用算法，从所有的键中选择空转时间最长的键值对清除；
    6. `allkeys-lfu`:最近最不经常使用算法，从所有的键中选择某段时间之内使用频次最少的键值对清除；
    7. `allkeys-random:`所有的键中，随机选择键进行删除；
    8. `noeviction`:不做任何的清理工作，在redis的内存超过限制之后，所有的写入操作都会返回错误；但是读操作都能正常的进行;
4. maxmemory-samples 最大内存的样品
    *  LRU、LFU和最小TTL算法并不是精确的算法，而是近似的算法(为了节省内存)，因此您可以对其进行调优以获得速度或精度。对于缺省情况，Redis将检查五个键并选择最近使用较少的一个，您可以使用下面的配置指令更改样本大小。
    * 默认值5可以产生足够好的结果。10非常接近真实的LRU，但是需要更多的CPU。3更快，但不是很准确。
# append only mode 追加

