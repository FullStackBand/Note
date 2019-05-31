1. eclipse
.settings\org.eclipse.wst.common.component
```
<?xml version="1.0" encoding="UTF-8"?><project-modules id="moduleCoreId" project-version="1.5.0">
    <wb-module deploy-name="修改">
        <wb-resource deploy-path="/" source-path="/target/m2e-wtp/web-resources"/>
        <wb-resource deploy-path="/" source-path="/src/main/webapp" tag="defaultRootSource"/>
        <wb-resource deploy-path="/WEB-INF/classes" source-path="/src/main/java"/>
        <wb-resource deploy-path="/WEB-INF/classes" source-path="/src/main/resources"/>
        <property name="java-output-path" value="/修改/target/classes"/>
        <property name="context-root" value="修改"/>
    </wb-module>
</project-modules>
```
2. maven
pom.xml
```
	<modelVersion>4.0.0</modelVersion>
	<groupId>修改</groupId>
	<artifactId>修改</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>war</packaging>
	<name>修改</name>
...
	<build>
		<!-- war包名称 -->
		<finalName>hytx_oa</finalName>
...
```
3. 编译
右击项目--->点击Debug ----->点击 Maven install进行编译,编译成功后入图(要出现success字样编译才算成功)
![](https://upload-images.jianshu.io/upload_images/13055171-83b807ce52ad1dc8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
4. 打包
右击项目--->点击Debug as ---->点击 Maven build...
在Goals中输入`clean compile package`
![](https://upload-images.jianshu.io/upload_images/13055171-83b807ce52ad1dc8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
> Good Game

