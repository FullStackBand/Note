# 准备

[jdk](server-jre-8u211-linux-x64.tar.gz)

# 安装

## 1. 创建目录

```cmd
mkdir /usr/java
```



## 2. 解压

```cmd
tar -zxvf /opt/server-jre-8u211-linux-x64.tar.gz 
```

* 移动到指定目录

```cmd
mv /opt/server-jre-8u211-linux-x64 /usr/java
```



## 3. 创建连接

```cmd
ln -s /usr/java/jdk1.8.0_211/ /usr/jdk
```

相当于快捷方式

## 4. 环境变量

```cmd
vi /etc/profile
```

```txt
JAVA_HOME=/usr/jdk
PATH=$PATH:$HOME/bin:$JAVA_HOME/bin
export JAVA_HOME
export PATH
```



## 5. 重启

```cmd
reboot
```

## 6. 查看

```cmd
java -version
```

