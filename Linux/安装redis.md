1. 下载

   [redis-5.0.4.tar.gz](redis-5.0.4.tar.gz)

2. 解压

   ```cmd
   tar xzvf redis-5.0.4.tar.gz
   ```

3. 创建目录

   ```cmd
   mkdir /usr/local/redis/
   ```

   

4. 移动

   ```
   mv redis-5.0.4 /usr/local/redis
   ```

   

5. 安装

   ```cmd
   cd /usr/local/redis
   make
   cd src
   make install PREFIX=/usr/local/redis
   ```

   

6. 配置

   ```cmd
   cd ..
   mkdir /usr/local/redis/etc
   mv redis.conf /usr/local/redis/etc
   ```

   

7. 后台启动

   ```cmd
   vi /usr/local/redis/etc/redis.conf
   ```

   `将daemonize no 改成daemonize yes`

8. 添加到开机启动

   ```
   vi /etc/rc.local 
   ```

   在里面添加内容：`/usr/local/redis/bin/redis-server /usr/local/redis/etc/redis.conf `(意思就是开机调用这段开启redis的命令)

9. 开启

   ```cmd
   /usr/local/redis/bin/redis-server /usr/local/redis/etc/redis.conf 
   ```

   

10. 控制台

    ```cmd
    /usr/local/redis/bin/redis-cli
    ```

    