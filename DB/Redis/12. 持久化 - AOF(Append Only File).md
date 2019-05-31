---
typora-copy-images-to: ./
typora-root-url: ./
---

#  持久化 - AOF(Append Only File)
## 介绍
* 以日志的形式来记录每个写操作，将Redis执行过的所有写指令记录下来(读操作不记录)，
只许追加文件但不可以改写文件，redis启动之初会读取该文件重新构建数据，换言之，redis
重启的话就根据日志文件的内容将写指令从前到后执行一次以完成数据的恢复工作
## 文件
* appendonly.aof
## 配置
1. appendonly
    *  Append Only文件是另一种持久性模式，它提供了更好的持久性。
2. appendonlyfilename
    * 文件的名称(默认:“appendonly.aof”) 
3. appendfsync 
    * 支持三种不同的模式:
        1. `no`: 
        2. `always`: 同步持久化,每次发生数据变更都会立即记录到磁盘,性能较差,但是数据完整性较好
        3. `everysec`: 出厂默认,异步操作,如果系统异常,有数据丢失
4. no-appendfsync-no-rewrite
    * 如果您有延迟问题，请将此选项变为“yes”。
    * 否则保持原样从耐久性的角度来看，“no”是最安全的选择。
5. auto-aof-rewrite-min-size
    * 重写机制的大小 
6. auto-aof-rewrite-percentage
    * 重写机制的百分比
## 启动/修复/恢复
### 1. 正常恢复
1. 启动
    * 设置yes : appendonly no , 改为 yes
2. 备份
    * 将有数据的aof复制一份保存到相应的目录
3. 恢复
    * 重启后自动加载

### 2. 异常恢复
1. 启动
    * 设置yes : appendonly no , 改为 yes
2. 备份
    * 备份被写坏的的aof文件
3. 修复
    *  使用`redis-check-aof --fix`进行修复
4. 恢复
    * 重启后自动加载
## rewrite
1. 定义
    * AOF采用文件追加方式，文件会越来越大为避免出现此种情况，新增了重写机制,当AOF文件的大小超过所设定的阈值时，Redis就会启动AOF文件的内容压缩，只保留可以恢复数据的最小指令集.可以使用命令bgrewriteaof
2. 重写原理
    *  AOF文件持续增长而过大时，会fork出一条新进程来将文件重写(也是先写临时文件最后再rename)，遍历新进程的内存中数据，每条记录有一条的Set语句。重写aof文件的操作，并没有读取旧的aof文件，而是将整个内存中的数据库内容用命令的方式重写了一个新的aof文件，这点和快照有点类似
3. 触发机制
    *  Redis会记录上次重写时的AOF大小，默认配置是当AOF文件大小是上次rewrite后大小的一倍且文件大于64M时触发 `auto-aof-rewrite-min-size 64m`
## 优势
1. 每修改同步
    *  每修改同步：appendfsync always   同步持久化 每次发生数据变更会被立即记录到磁盘  性能较差但数据完整性比较好
2. 每秒同步
    * 每秒同步：appendfsync everysec    异步操作，每秒记录   如果一秒内宕机，有数据丢失
3. 不同步
    * 不同步：appendfsync no   从不同步
## 劣势
1. 恢复慢
    * 相同数据集的数据而言aof文件要远大于rdb文件，恢复速度慢于rdb 
2. 效率低
    * aof运行效率要慢于rdb,每秒同步策略效率较好，不同步效率和rdb相同 
# 小结

![1553213967346](/1553213967346.png)

