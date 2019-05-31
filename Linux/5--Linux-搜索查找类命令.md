 #find 命令
* 语法
 ` find [搜索范围] [选项] 文件名`
* 选项
  
|选项|功能|
|-|-|
|`-name`|按照指定的文件名查找模式查找|
|`-user`|按照指定用户名查找文件|
|`-size`|按照指定文件的大小查找文件|

> `+20M` ：大于20MB
> `-10K`：小于10KB
> `1G`：等于1GB
* 实例
```cmd
[root@localhost ~]# find /root -name config
/root/.kde/share/config
```
#locate
locaate指令可以快速定位文件路径。locate指令利用事先建立的系统中所有文件名称及路径的locate数据库实现快速定位给定的文件。Locate指令无需遍历整个文件系统，查询速度较快。为了保证查询结果的准确度，管理员必须定期更新locate时刻。
* 基本语法
locate 搜索文件
> 搜索前要使用`updatedb`创建`locate`的搜索数据库(这种搜索是极快速的)
* 实例
```
[root@localhost ~]# locate luke
/home/luke
/home/luke/.bash_history
/home/luke/.bash_logout
/home/luke/.bash_profile
/home/luke/.bashrc
/home/luke/.cache
```
#grep
`grep`过滤查找，管道符`|`，表示将前一个命令的处理结果输出传递给后面的命令处理。
* 基本语法
`grep [选项] 查找内容 源文件`
* 常用选项
  * -n 显示匹配的行号
  * -i 忽略字母大小写
* 实例
  在hello.java 查找hello
```
[root@localhost admin]# cat hello.java | grep -n hello
1:public class hello{
3:		System.out.printli("hello"+args[0]);
```
