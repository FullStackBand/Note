# C# 循环

有的时候，可能需要多次执行同一块代码。一般情况下，语句是顺序执行的：函数中的第一个语句先执行，接着是第二个语句，依此类推。

编程语言提供了允许更为复杂的执行路径的多种控制结构。

循环语句允许我们多次执行一个语句或语句组，下面是大多数编程语言中循环语句的一般形式：



## 1.循环类型

C# 提供了以下几种循环类型。点击链接查看每个类型的细节。

| 循环类型                                                     | 描述                                                         |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [while 循环](https://www.runoob.com/csharp/csharp-while-loop.html) | 当给定条件为真时，重复语句或语句组。它会在执行循环主体之前测试条件。 |
| [for/foreach 循环](https://www.runoob.com/csharp/csharp-for-loop.html) | 多次执行一个语句序列，简化管理循环变量的代码。               |
| [do...while 循环](https://www.runoob.com/csharp/csharp-do-while-loop.html) | 除了它是在循环主体结尾测试条件外，其他与 while 语句类似。    |
| [嵌套循环](https://www.runoob.com/csharp/csharp-nested-loops.html) | 您可以在 while、for 或 do..while 循环内使用一个或多个循环。  |

### 1.1 while循环

**实例**

```c#
using System;

namespace Loops
{
    
    class Program
    {
        static void Main(string[] args)
        {
            /* 局部变量定义 */
            int a = 10;

            /* while 循环执行 */
            while (a < 20)
            {
                Console.WriteLine("a 的值： {0}", a);
                a++;
            }
            Console.ReadLine();
        }
    }
} 
```



### 1.2 for/foreach循环

**`for`实例**

```c#
using System;

namespace Loops
{
    
    class Program
    {
        static void Main(string[] args)
        {
            /* for 循环执行 */
            for (int a = 10; a < 20; a = a + 1)
            {
                Console.WriteLine("a 的值： {0}", a);
            }
            Console.ReadLine();
        }
    }
} 
```

**`foreach`实例**

```c#
class ForEachTest
{
    static void Main(string[] args)
    {
        int[] fibarray = new int[] { 0, 1, 1, 2, 3, 5, 8, 13 };
        foreach (int element in fibarray)
        {
            System.Console.WriteLine(element);
        }
        System.Console.WriteLine();


        // 类似 foreach 循环
        for (int i = 0; i < fibarray.Length; i++)
        {
            System.Console.WriteLine(fibarray[i]);
        }
        System.Console.WriteLine();


        // 设置集合中元素的计算器
        int count = 0;
        foreach (int element in fibarray)
        {
            count += 1;
            System.Console.WriteLine("Element #{0}: {1}", count, element);
        }
        System.Console.WriteLine("Number of elements in the array: {0}", count);
    }
}
```



### 1.3 do…while 循环

**实例**

```c#
using System;

namespace Loops
{
    
    class Program
    {
        static void Main(string[] args)
        {
            /* 局部变量定义 */
            int a = 10;

            /* do 循环执行 */
            do
            {
               Console.WriteLine("a 的值： {0}", a);
                a = a + 1;
            } while (a < 20);

            Console.ReadLine();
        }
    }
} 
```



### 1.4 嵌套循环

**实例**

找到100以内的质数

```c#
using System;

namespace Loops
{
    
   class Program
   {
      static void Main(string[] args)
      {
         /* 局部变量定义 */
         int i, j;

         for (i = 2; i < 100; i++)
         {
            for (j = 2; j <= (i / j); j++)
               if ((i % j) == 0) break; // 如果找到，则不是质数
            if (j > (i / j)) 
               Console.WriteLine("{0} 是质数", i);
         }

         Console.ReadLine();
      }
   }
} 
```



## 2.循环控制语句

循环控制语句更改执行的正常序列。当执行离开一个范围时，所有在该范围中创建的自动对象都会被销毁。

C# 提供了下列的控制语句。点击链接查看每个语句的细节。

| 控制语句                                                     | 描述                                                         |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [break 语句](https://www.runoob.com/csharp/csharp-break-statement.html) | 终止 **loop** 或 **switch** 语句，程序流将继续执行紧接着 loop 或 switch 的下一条语句。 |
| [continue 语句](https://www.runoob.com/csharp/csharp-continue-statement.html) | 引起循环跳过主体的剩余部分，立即重新开始测试条件。           |

### 2.1终止循环break

实例

```c#
using System;

namespace Loops
{
    
    class Program
    {
        static void Main(string[] args)
        {
            /* 局部变量定义 */
            int a = 10;

            /* while 循环执行 */
            while (a < 20)
            {
                Console.WriteLine("a 的值： {0}", a);
                a++;
                if (a > 15)
                {
                    /* 使用 break 语句终止 loop */
                    break;
                }
            }
            Console.ReadLine();
        }
    }
} 
```



结果 没有大于15的

```cmd
a 的值： 10
a 的值： 11
a 的值： 12
a 的值： 13
a 的值： 14
a 的值： 15
```

### 2.2跳过本次循环continue

实例

```C#
using System;

namespace Loops
{
    
    class Program
    {
        static void Main(string[] args)
        {
            /* 局部变量定义 */
            int a = 10;

            /* do 循环执行 */
            do
            {
                if (a == 15)
                {
                    /* 跳过迭代 */
                    a = a + 1;
                    continue;
                }
                Console.WriteLine("a 的值： {0}", a);
                a++;

            } while (a < 20);
 
            Console.ReadLine();
        }
    }
} 
```



结果 没有15

```
a 的值： 10
a 的值： 11
a 的值： 12
a 的值： 13
a 的值： 14
a 的值： 16
a 的值： 17
a 的值： 18
a 的值： 19
```

## 3.无限循环

如果条件永远不为假，则循环将变成无限循环。**for** 循环在传统意义上可用于实现无限循环。由于构成循环的三个表达式中任何一个都不是必需的，您可以将某些条件表达式留空来构成一个无限循环。

```c#
using System;

namespace Loops
{
    
    class Program
    {
        static void Main(string[] args)
        {
            for (; ; )
            {
                Console.WriteLine("Hey! I am Trapped");
            }
 
        }
    }
} 
```



当条件表达式不存在时，它被假设为真。您也可以设置一个初始值和增量表达式，但是一般情况下，程序员偏向于使用 for(;;) 结构来表示一个无限循环。

## 4.goto语句

goto语句用于直接在一个程序中转到程序中的标签指定的位置,标签是有上由标识符加上冒号组成

### 4.1语法

```c#
goto Lable1;
	语句块 1;
Lable1
	语句块 2;
```

如果要跳转到某一个标签指定的位置,直接使用`goto`加标签名, 即先执行语句块 2，再执行语句块 1。

此外，需要注意的是 goto 语句不能跳转到循环语句中，也不能跳出类的范围。

由于 goto 语句不便于程序的理解，因此 goto 语句并不常用。

【实例】使用 goto 语句判断输入的用户名和密码是否正确，如果错误次数超过3次，则输出“用户名或密码错误次数过多！退出！”。

根据题目要求，假设用户名为 aaa、密码为 123，代码如下。

```
class Program
{
    static void Main(string[] args)
    {
        int count = 1;
    login:
        Console.WriteLine("请输入用户名");
        string username = Console.ReadLine();
        Console.WriteLine("请输入密码");
        string userpwd = Console.ReadLine();
        if (username == "aaa" && userpwd == "123")
        {
            Console.WriteLine("登录成功");
        }
        else
        {
            count++;
            if (count > 3)
            {
                Console.WriteLine("用户名或密码错误次数过多！退出！");
            }
            else
            {
                Console.WriteLine("用户名或密码错误");
                goto login;//返回login标签处重新输入用户名密码
            }
        }
    }
}
```