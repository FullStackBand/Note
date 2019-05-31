# 安装(Linux 环境)
> 最好使用 root 用户 进入 media 文件下,下载和安装
1. 下载 

```
wget http://download.redis.io/releases/redis-5.0.3.tar.gz
```
![](https://upload-images.jianshu.io/upload_images/13055171-1d91df9eef8ddfcf.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


2. 解压
```
tar xzf redis-5.0.3.tar.gz
```
![](https://upload-images.jianshu.io/upload_images/13055171-4969a9f52ead331b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


3. 进入目录
```
cd redis-5.0.3
```
4. 编译
```
make
```
![](https://upload-images.jianshu.io/upload_images/13055171-3c70b1533fcb943a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


5.  安装

```
 cd src/
make install
```

![](https://upload-images.jianshu.io/upload_images/13055171-dbe3adf033f51476.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



> 如果`make`运行错误 请安装gcc
> * Linux 安装GCC讲解(在线和无网离线)

> 二次`make` 运行错误
> *  运行`make distclean`之后再`make`

# 部署Redis
1. 复制redis.conf
    1. 复制到etc中
2. 修改内容
    意思是,允许后台运行
![](https://upload-images.jianshu.io/upload_images/13055171-caba87a1edcc24d5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

3. 按照新的配置运行
````
redis-server /etc/redis.conf
````
4. 查看redis进程,是否启动
````
ps -ef|grep redis

````
5. 启动  redis客户端
```
redis-cli

```
6. 查看key
```
keys *
```
7. 关闭 redis服务

```
redis-cli -p 6379 shutdown
ps -A|grep redis
```
