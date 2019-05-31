

# Linux 入门

## 1.Linux介绍

1. Linux 是一款操作系统，免费，开源，安全，稳定，处理高并发非常强悍，现在很对的企业级的项目都部署到Linux服务器运行

2. Linux 的创始人 林纳斯

3. Linux 的吉祥物是一个企鹅

4. 发行版本（Linux是指内核）

   1. CentOSE
   2. Redhat
   3. Ubuntu
   4. Suse
   5. 红旗Linux 

5. 目前的主要的操作系统

   1. windows，ios，Linux，安卓

6. Linux 和 Unix

 ![![](https://upload-images.jianshu.io/upload_images/13055171-86891cd5e18c6ccb.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
](https://upload-images.jianshu.io/upload_images/13055171-83186eef016fc8d6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


7. 关系图

 ![](https://upload-images.jianshu.io/upload_images/13055171-af62afa29e278856.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


8. linux和windows的比较

## 2.安装和说明

需求：

- VM软件（可以在Windows中安装linux）
- CentOS-7-x86_64-DVD-1804.iso（安装镜像）

安装Centos 的注意事项

- 网络链接
  - 桥连接：方便，但有可能造成IP不够用
  - NAT：IP不冲突，linux可以访问外网（推荐）
- Centos终端的使用和客户端的使用
  - 终端
    - 点击鼠标右键，就可以进入终端
  - 联网
    - 点击右边上册的网络连接，选择网卡
##3.Linux工具
1. Xshell
    远程控制器
2. Xftp
    文件上传下载
3. vi 和 vim
    文本编辑器

##vi 和 vim 基本介绍
* 所有的Linux 系统都会内建vi 文本编辑器。
  Vim 具有程序编辑的能力，可以看做是Vi的增强版本，可以主动的以字体颜色辨别语法的正确性，方便程序设计。代码补完、编译及错误跳转等方便编程的功能特别丰富，在程序员中被广泛使用。
###vi和vim常用的三种模式
* 正常模式:
以vim 打开一个档案就直接进入一般模式了(这是默认的模式)。在这个模式中，你可以使用『上下左右』按键来移动光标，你可以使用『删除字符』或『删除整行』来处理档案内容，也可以使用『复制、贴上』来处理你的文件数据。
* 插入模式:
按下i, I, o, O, a, A, r, R等任何一个字母之后才会进入编辑模式, 一般来说按i即可.
* 命令行模式
在这个模式当中，可以提供你相关指令，完成读取、存盘、替换、离开vim 、显示行号等的动作则是在此模式中达成的！
###模式的切换
![](https://upload-images.jianshu.io/upload_images/13055171-7f89447c72ae8a2e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
###快捷键
![](https://upload-images.jianshu.io/upload_images/13055171-6c4aedd0d88dd369.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


