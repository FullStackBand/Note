> 首先开启`redis-server /etc/redis.conf` 后面的路径是修改后配置文件的路径
> * 测试redis的性能
    `redis-benchmark`
    > * *<u>默认端口**6379**</u>*

# 进入redis控制台
* `redis-cli` 
## 常见命令
### 1. 切换库
redis 默认有16个库
* `select [0-15]`
![](https://upload-images.jianshu.io/upload_images/13055171-5fedc34db6610172.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 2. 查看当前库中<u>**key**</u>的数量
* `dbsize`
![](https://upload-images.jianshu.io/upload_images/13055171-d840fd6a6bb1b64e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 3. 查看key
1. `keys *`
    `'*'`是通配符
![](https://upload-images.jianshu.io/upload_images/13055171-57fba8a0440b3eef.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

    
2. `keys nam?`
    `'?'`一个字符的占位符
![](https://upload-images.jianshu.io/upload_images/13055171-a8ecf5876db0dd9b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](https://upload-images.jianshu.io/upload_images/13055171-afdf967d47c8ee4f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 4. 清除库
* `LUSHDB` 
    清除当前库 - LUSHDB
![](https://upload-images.jianshu.io/upload_images/13055171-8b8abe855e1d3a74.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* `FLUSHALL`
     清除所有库 - FLUSHALL

    
