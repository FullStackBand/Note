在实际工作中，有时候为了赶时间，往往通过复制项目得到一个成型的框架。那么怎么才可以彻底修改项目名称呢？

#1、web.xml





![](https://upload-images.jianshu.io/upload_images/13055171-bcf343bf111886cc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)




#2、工作空间中找到当前项目下.project文件 
![](https://upload-images.jianshu.io/upload_images/13055171-e23a73f7f23de0c6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#3、工作空间中找到当前项目，打开.settings文件夹，找到org.eclipse.wst.common.component文件 
![](https://upload-images.jianshu.io/upload_images/13055171-73d22b06be57e417.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

> 如果是非maven的项目的话，上面几步就可以实现彻底修改名称了，但是如果是maven项目，还需要对pom.xml做修改 
#4、修改pom.xml： 
![](https://upload-images.jianshu.io/upload_images/13055171-f0c9b339b8b53050.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
