# 下载

[Tomcat8.5](apache-tomcat-8.5.40.tar.gz)

# 安装

1. 创建目录

```cmd
mkdir /usr/local/tomcat
```



1. 解压

```cmd
tar -zxvf  apache-tomcat-8.5.40.tar.gz
```



1. 移动

```cmd
mv apache-tomcat-8.5.40 /usr/local/tomcat
```



1. 配置 80端口

```xml
<!-- 修改 -->
<Connector port="80`" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />

```



1. 启动

```cmd
./bin/startup.sh
```

