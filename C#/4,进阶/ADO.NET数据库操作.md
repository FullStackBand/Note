# ADO.NET 数据库操作

任何一个应用程序都离不开数据的存储，数据可以在内存中存储，但只能在程序运行时存取，无法持久保存。

数据还可以在磁盘中以文件的形式存储，但文件的管理和查找又十分烦琐无法胜任大数量的存储。

将数据存储到数据库中是在应用程序中持久存储数据的常用方式。

在 C# 语言中提供了 ADO.NET 组件来实现连接数据库以及操作数据库中数据的功能。

## 1. C# ADO.NET数据库操作及常用类概述
## 2. C# Connection：连接数据库
## 3. C# Command：操作数据库
## 4. C# DataReader：读取查询结果
## 5. C# DataSet和DataTable：将查询结果保存到DataSet或DataTable中
## 6. C# DataRow和DataColumn：更新数据表
## ## ## ## 7. C# DataSet：更新数据库
## ## ## 8. C# ComboBox：组合框控件数据绑定
## ## 9. C# DataGridView：数据表格控件数据绑定
## 10. C# 数据表格（DataGridView）控件的应用案例









> 测试

```c#
using System;
using System.Text.RegularExpressions;
using MySql.Data.MySqlClient;

namespace ConsoleApp1
{
    class Program1
    {
        static void Main(string[] args)
        {
            //Ip+端口+数据库名+用户名+密码
            string connectStr = "server=127.0.0.1;port=3306;database=world;user=root;password=root;SslMode=none;";
            MySqlConnection conn = new MySqlConnection(connectStr); ;
            try//使用try关键字
            {
                conn.Open();//跟数据库建立连接，并打开连接
                string sql = "select * from city";//MySql语句，查询列表内容
                MySqlCommand cmd = new MySqlCommand(sql, conn);
                MySqlDataReader reader = cmd.ExecuteReader();//执行一些查询
                //cmd.ExecuteScalar();//执行一些查询，返回一个单个的值
                //读取第一次Read()，ke输出读取第一列数据，如果再Read()一次，可输出读取第二列数据，但是只能读取第二列数据
                //reader.Read();//读取一列数据如果读取(有数据)成功，返回True,如果没有（数据），读取失败的话返回false
                while (reader.Read())//使用while循环可读取所有user列表里的数据
                {
                    Console.WriteLine(reader.GetInt32("id") + " " + reader.GetString("Name") + " " + reader.GetString("CountryCode"));
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
            }
            finally
            {
                conn.Clone();
            }
            Console.ReadKey();

        }
    }
}
```



