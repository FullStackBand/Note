1. 我们可以输入命令查看防火墙的状态；

`firewall-cmd --state`
2. 如何在CentOS 7下开放8080端口
如果上一步处于关闭状态，输入命令：

`systemctl start firewalld.service`

3. 如何在CentOS 7下开放8080端口
开启8080端口，输入命令：

`firewall-cmd --zone=public --add-port=8080/tcp --permanent`
4. 如何在CentOS 7下开放8080端口
让我们来解释一下上一个命令：
```
--zone=public：表示作用域为公共的；

--add-port=8080/tcp：添加tcp协议的端口8080；

--permanent：永久生效，如果没有此参数，则只能维持当前服务生命周期内，重新启动后失效；
```
如何在CentOS 7下开放8080端口
5. 输入命令重启防火墙；

`systemctl restart firewalld.service`

如何在CentOS 7下开放8080端口
7. 输入命令重新载入配置；

`firewall-cmd --reload`
