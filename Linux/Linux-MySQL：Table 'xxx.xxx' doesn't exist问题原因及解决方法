# 由报错Table ‘xxx.xxxxx’ doesn’t exist可知，其中的mysql.proc表不存在而发生错误。

【1】插入数据或更改数据时使用的表输入错误
【2】linux的mysql区分大小写，数据库中的表名与输入的sql语句中的使用的表名大小写不一致导致的
【3】数据库操作时，误删mysql的文件导致(常见于数据库升级或迁移)
【4】在编译安装mysql时，没有指定innodb存储引擎
解决步骤
【1】查看自己的sql语句是否正确
如此语句正确，则看下一条方法

解决方法如下：

<1>不正确请改正

【2】查看是否有此表，不要忽视大小写
如此表存在，则是linux的mysql区分大小写导致；如此表不存在，则看下一条方法

解决方法如下：

<1>查找该mysql数据库的配置文件my.cnf的路径

<2>在my.cnf中的[mysqld]下,追加lower_case_table_names = 1
1表示不区分大小写，0区分大小写



<3>重启mysql,重新在此表插入数据，看是否可用

systemctl restart maraidb/mysqld(centos7)

/etc/init.d/mysqld restart(centos6)

【3】如该表真的不存在，则可能是表被误删或数据库迁移缺失文件等原因导致。
解决方法如下：

方法一：创建此表。(不同表结构根据实际情况)

例如：mysql.servers表的创建。

 CREATE TABLE `mysql.servers` (
        `Server_name` char(64) NOT NULL,
        `Host` char(64) NOT NULL,`Db` char(64) NOT NULL,
        `Username` char(64) NOT NULL,
        `Password` char(64) NOT NULL,
        `Port` int(4) DEFAULT NULL,
        `Socket` char(64) DEFAULT NULL,
        `Wrapper` char(64) NOT NULL,
        `Owner` char(64) NOT NULL,
        PRIMARY KEY (`Server_name`)
        ) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='MySQL Foreign Servers table';

flush privileges;
1
2
3
4
5
6
7
8
9
10
11
12
13
方法二：修复损坏表
(修复方式还有很多种，请查询，只列一种)

repair table 表名；

方法三：拷贝缺失文件(最常用方法)

原理：

当表类型是MyISAM时,数据文件则以”Table.frm””Table.MYD””Table.MYI””三个文件存储于”/data/$databasename/”目录中。

当表类型是InnoDB时,数据文件则存储在”$innodb_data_home_dir/″中的ibdata1文件中(一般情况)，结构文件存在于table_name.frm中。 

MySQL的数据库文件直接复制便可以使用，但是那是指“MyISAM”类型的表。 而使用MySQL-Front直接创建表，默认是“InnoDB”类型，这种类型的一个表在磁盘上只对应一个“*.frm”文件，不像MyISAM那样还“*.MYD,*.MYI”文件。

MyISAM类型的表直接拷到另一个数据库就可以直接使用，但是InnoDB类型的表却不行。 解决方法就是同时拷贝innodb数据库表“*.frm”文件和innodb数据“ibdata1”文件到合适的位置。
1
2
3
4
5
6
7
8
9
<1>从另外相同的mysql数据库或之前的数据库备份中导出该表的数据，然后通过命令行导入进去

<2>或直接拷贝原有数据库文件".frm"、".MYD"、"*.MYI"等文件，如果原数据库引擎是InnoDB,切记还需拷贝ibdata1文件
（暴力点的是直接拷贝之前备份了的data）

<3>重启数据库

【4】如果是编译安装mysql时，没有指定innodb存储引擎
<1>重新编译

cmake -DCMAKE_INSTALL_PREFIX=/usr/local/mysql -DMYSQL_DATADIR=/usr/local/mysql/data -DSYSCONFDIR=/etc -DWITH_MYISAM_STORAGE_ENGINE=1 -DWITH_INNOBASE_STORAGE_ENGINE=1 -DWITH_MEMORY_STORAGE_ENGINE=1 -DWITH_READLINE=1 -DMYSQL_UNIX_ADDR=/tmp/mysqld.sock -DMYSQL_TCP_PORT=3306 -DENABLED_LOCAL_INFILE=1 -DWITH_PARTITION_STORAGE_ENGINE=1 -DEXTRA_CHARSETS=all -DDEFAULT_CHARSET=utf8 -DDEFAULT_COLLATION=utf8_general_ci
 24 

make && make install
--------------------- 
作者：漠效 
来源：CSDN 
原文：https://blog.csdn.net/GX_1_11_real/article/details/81347343 
版权声明：本文为博主原创文章，转载请附上博文链接！
