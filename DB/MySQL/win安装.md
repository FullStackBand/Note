1. 解压安装包

   根据自己的喜好选择路径，我选择的路径是`F:\Program Files\mysql`，因此MySQL的完整路径为：`F:\Program Files\mysql\mysql-5.7.26-winx64`

2. 配置环境变量

   键名：`MYSQL_HOME`

   值为：`F:\Program Files\mysql\mysql-5.7.26-winx64`

3. 准备好`my.ini`

   ```ini
   [mysqld]
   port = 3306
   basedir=F:/Program Files/mysql/mysql-5.7.26-winx64
   datadir=F:/Program Files/mysql/mysql-5.7.26-winx64/data
   max_connections=200
   character-set-server=utf8
   default-storage-engine=INNODB
   sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES
   [mysql]
   default-character-set=utf8
   
   ```

4. 安装

   ```cmd
   cd 'F:\Program Files\mysql\mysql-5.7.26-winx64\bin'
   .\mysqld -install
   ```

   表示成功

   ```cmd
   Service successfully installed. 
   ```

5. 初始化

   ```cmd
   .\mysqld --initialize-insecure --user=mysql
   ```

6. 启动

   ```cmd
   net start mysql
   ```

7. 设置密码

   ```cmd
   .\mysqladmin -u root -p password root
   ```

8. 登陆

   ```cmd
   .\mysql -u root -p
   ```

   

