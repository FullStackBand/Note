---
typora-copy-images-to: img
typora-root-url: img

---

# jeepuls 快速开发平台

## 一、准备

### 1.配置jdk

- 下载jdk1.8（资源见文档）
- 配置环境变量
- 检查

### 2. 安装maven

- 安装（资源见文档）
- 配置环境变量
  - 新建环境变量`MAVEN_HOME`
    - 设置安装路径
  - 在PATH里加入maven的bin的路径
    - ` %MAVEN_HOME%\bin`
- 设置本地库
  - 打开`conf`文件夹下的`settings.xml`文件，找到第53行(左右)，把注释去掉，修改成：
    - `<localRepository>C:/develop/</localRepository>`
- 检查
  - 配置完毕后，在Windows命令提示符下，输入`mvn -v`测试一下

### 3. 配置eclipse

- 如果没有maven插件需要下载插件

### 4.Redis

- 下载（资源见文档）备用

### 5.配置数据库（MySQL）

- 安装数据库（资源见文档）

- 数据库还原（资源见文档）执行sql

  > 生产环境也要使用

### 6.Tomcat

- Tomcat8.5 下载（资源见文档）备用

  > 生产环境使用

## 二、部署(代码生产环境)

### 1.开启Redis

- 双击`redis-server.exe`

  > 注意:不论是代码生成环境还是生产环境都需要打开**redis**

### 2.导入maven项目


![](https://upload-images.jianshu.io/upload_images/13055171-8e66fabff3d921f3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](https://upload-images.jianshu.io/upload_images/13055171-895b89d30175bd5f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



### 3. 修改数据库配置

- 配置文件`/jeeplus/src/main/resources/properties/jeeplus.properties`[图片上传失败...(image-b603fa-1541390166991)]

  > 指定你配置好的数据库

### 4.使用maven命令启动

- 使用Debug As > Maven Build...
![](https://upload-images.jianshu.io/upload_images/13055171-9036787bfb396bc3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 在Goals:中输入命令`tomcat7:rum`启动项目
  ![](https://upload-images.jianshu.io/upload_images/13055171-80b12a9790b47a8e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 等待显示如下就表示启动完成
![](https://upload-images.jianshu.io/upload_images/13055171-49b9e727688381ec.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 5.授权

1. 如图:登录页面进入`开发工具`>`表单配置`
![](https://upload-images.jianshu.io/upload_images/13055171-1f71d47656e27d31.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


2. 复制机器码

3. 通过购买渠道提供的方法获取

   - (例如:)
     - productID :` Y20****3051`
     - license : `E540228086A0C17E3C..........................................................00093E3E8C9C09474ECD00B`

4. 在位置 : /jeeplus/src/main/resources/properties/license.properties 中

   - 例如

   ```properties
   productID=Y20****3051
   license=3E540228086A0......................................................................8C809BCCF9CD00B
   ```

### 6.进入表单配置

1. 停止当前运行项目

   > 否则在此启动会有端口占用

2. 重复第4步:使用maven命令启动

## 三、部署完成(代码生成)

![](https://upload-images.jianshu.io/upload_images/13055171-c6d3d7cb39151c4e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)




## 参考文档

jeepuls[官方文档](http://wiki.jeeplus.org/docs/show/75)





