#gzip/gunzip
gzio用于压缩，gunzip用于解压
* 基本语法
`gzip  文件`
`gunzip 文件.gz`
* 实例
  * 压缩
```
[root@localhost admin]# gzip hello.java
[root@localhost admin]# ls
hello.java.gz  公共  模板  图片  下载  桌面
ok.txt         视频  文档  音乐

```
  * 解压
```
[root@localhost admin]# gunzip hello.java.gz
[root@localhost admin]# ls
hello.java  公共  模板  图片  下载  桌面
ok.txt      视频  文档  音乐
```
> 当使用gizp 和 gunzip的时候，不会保留源文件

#zip和unzip
zip用于压缩，unzip用于解压
* 基本语法
`zip [选项] XXX.zip 将要压缩的内容`
`unzip [选项] XXX.zip `
* zip常用选项
  * -r 递归压缩，即压缩整个目录
* unzip常用选项
  * -d<目录> 指定解压后存放的目录
* 案例
  * 压缩目录
```
[root@localhost admin]# zip -r p.zip /home/luke/文档
  adding: home/luke/文档/ (stored 0%)
  adding: home/luke/文档/金庸-射雕英雄传txt精校版.txt (deflated 56%)
  adding: home/luke/文档/date.txt (stored 0%)
```
* 解压至指定文件
```
[root@localhost admin]# unzip -d /home/admin p.zip
Archive:  p.zip
   creating: /home/admin/home/luke/文档/
  inflating: /home/admin/home/luke/文档/金庸-射雕英雄传txt精校版.txt  
 extracting: /home/admin/home/luke/文档/date.txt 
```
#tar
tar指令是打包指令，最后的打包文件是.tar.gz的文件
* 基本语法
`tar [选项] XXX.tar.gz 打包内容`
* 选项说明
  * -c  生产打包文件
  * -v  显示详细信息
  * -f  指定压缩后的文件名
  * -z  打包同时解压
  * -x  解包文件
* 实例
1. 将多个文件压缩
```
[root@localhost admin]# tar -zcvf a.tar.gz  hello.java ok.txt p.zip 雷 鸣简历.doc
hello.java
ok.txt
p.zip
雷鸣简历.doc
[root@localhost admin]# ls
a.tar.gz    home    p.zip  雷鸣简历.doc  视频  文档  音乐
hello.java  ok.txt  公共   模板          图片  下载  桌面

```
2. 将文件夹压缩
```
[root@localhost admin]# tar -zcvg myadmin.tar.gz /home/admin/
```
3. 解压到当前目录
```
[root@localhost admin]# tar -zxvf myadmin.tar.gz

gzip: stdin: unexpected end of file
tar: Child returned status 1
tar: Error is not recoverable: exiting now
```
4. 解压到指定目录
```
[root@localhost admin]# tar -zxvf myadmin.tar.gz -C /home/admin/

gzip: stdin: unexpected end of file
tar: Child returned status 1
tar: Error is not recoverable: exiting now
```
