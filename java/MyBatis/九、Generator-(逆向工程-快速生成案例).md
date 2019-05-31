#jar包
* mybatis generatot .jar
#xml配置
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
  PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
  "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
	<context id="DB2Tables" targetRuntime="MyBatis3">
	    <!-- 其他设置 -->
		<commentGenerator>
		    <!-- 取消注释 -->
			<property name="suppressAllComments" value="true" />
		</commentGenerator>
		<!-- 数据库连接 -->
		<jdbcConnection driverClass="oracle.jdbc.OracleDriver"
			connectionURL="jdbc:oracle:thin:@127.0.0.1:1521:orcl" userId="scott"
			password="java">
		</jdbcConnection>
		<!-- 设置解析器 -->
		<javaTypeResolver>
		    <!-- 关闭 BigDecimals 类型-->
			<property name="forceBigDecimals" value="false" />
		</javaTypeResolver>
		<!-- javaBean生成地址 
			targetPackage：包
			targetProject：目录
		-->
		<javaModelGenerator targetPackage="com.po"
			targetProject="src">
			<property name="enableSubPackages" value="true" />
			<property name="trimStrings" value="true" />
			<!-- sql生成地址 -->
		</javaModelGenerator>
		<sqlMapGenerator targetPackage="com.mapper"
			targetProject="src">
			<property name="enableSubPackages" value="true" />
		</sqlMapGenerator>
		<!-- mapper地址 -->
		<javaClientGenerator type="XMLMAPPER"
			targetPackage="com.mapper" targetProject="src">
			<property name="enableSubPackages" value="true" />
		</javaClientGenerator>
		<!-- 需要生成的表和实体类
			tableName：表名
			domainObjectName：类名
		 -->
		<table tableName="BONUS" domainObjectName="Bonus"></table>
		<table tableName="DEPT" domainObjectName="Dept"></table>
		<table tableName="DIARY" domainObjectName="Diary"></table>
		<table tableName="DIARY_GROUP" domainObjectName="Diary_Group"></table>
		<table tableName="EMP" domainObjectName="Emp"></table>
		<table tableName="SALGRADE" domainObjectName="Salgrade"></table>
		<table tableName="USERS" domainObjectName="Users"></table>
	</context>
</generatorConfiguration>

```
#执行
```
//main 方法...
   List<String> warnings = new ArrayList<String>();
   boolean overwrite = true;
   File configFile = new File("generatorConfig.xml");
   ConfigurationParser cp = new ConfigurationParser(warnings);
   Configuration config = cp.parseConfiguration(configFile);
   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
   myBatisGenerator.generate(null);
```
