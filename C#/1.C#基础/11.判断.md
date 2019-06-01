# C# 判断

判断结构要求程序员指定一个或多个要评估或测试的条件，以及条件为真时要执行的语句（必需的）和条件为假时要执行的语句（可选的）。

下面是大多数编程语言中典型的判断结构的一般形式：

![img](assets/if.png)

## 1.判断语句

C# 提供了以下类型的判断语句。点击链接查看每个语句的细节。

| 语句             | 描述                                                         |
| :--------------- | :----------------------------------------------------------- |
| if 语句          | 一个 **if 语句** 由一个布尔表达式后跟一个或多个语句组成。    |
| if...else 语句   | 一个 **if 语句** 后可跟一个可选的 **else 语句**，else 语句在布尔表达式为假时执行。 |
| 嵌套 if 语句     | 您可以在一个 **if** 或 **else if** 语句内使用另一个 **if** 或 **else if** 语句。 |
| switch 语句      | 一个 **switch** 语句允许测试一个变量等于多个值时的情况。     |
| 嵌套 switch 语句 | 您可以在一个 **switch** 语句内使用另一个 **switch** 语句。   |

### 1.1 `if`语句

1. 语法

   C# 中 **if** 语句的语法：

   ```
   if(boolean_expression)
   {
      /* 如果布尔表达式为真将执行的语句 */
   }
   ```

2. 流程图

   ![img](assets/if_statement.jpg)

3. 实例

   ```c#
   using System;
   
   namespace DecisionMaking
   {
       
       class Program
       {
           static void Main(string[] args)
           {
               /* 局部变量定义 */
               int a = 10;
   
               /* 使用 if 语句检查布尔条件 */
               if (a < 20)
               {
                   /* 如果条件为真，则输出下面的语句 */
                   Console.WriteLine("a 小于 20");
               }
               Console.WriteLine("a 的值是 {0}", a);
               Console.ReadLine();
           }
       }
   }
   ```

   当上面的代码被编译和执行时，它会产生下列结果：

   ```
   a 小于 20
   a 的值是 10
   ```

### 1.2 `if...else`语句

1. 语法

   C# 中 **if...else** 语句的语法：

   ```c#
   if(boolean_expression)
   {
      /* 如果布尔表达式为真将执行的语句 */
   }
   else
   {
     /* 如果布尔表达式为假将执行的语句 */
   }
   ```

   ```c#
   if(boolean_expression 1)
   {
      /* 当布尔表达式 1 为真时执行 */
   }
   else if( boolean_expression 2)
   {
      /* 当布尔表达式 2 为真时执行 */
   }
   else if( boolean_expression 3)
   {
      /* 当布尔表达式 3 为真时执行 */
   }
   else 
   {
      /* 当上面条件都不为真时执行 */
   }
   ```

   

   如果布尔表达式为 **true**，则执行 **if** 块内的代码。如果布尔表达式为 **false**，则执行 **else** 块内的代码。

   

2. 流程图

   ![img](assets/if_else_statement.jpg)

3. 实例

   * if…else

   ```c#
   using System;
   
   namespace DecisionMaking
   {
       
       class Program
       {
           static void Main(string[] args)
           {
   
               /* 局部变量定义 */
               int a = 100;
   
               /* 检查布尔条件 */
               if (a < 20)
               {
                   /* 如果条件为真，则输出下面的语句 */
                   Console.WriteLine("a 小于 20");
               }
               else
               {
                   /* 如果条件为假，则输出下面的语句 */
                   Console.WriteLine("a 大于 20");
               }
               Console.WriteLine("a 的值是 {0}", a);
               Console.ReadLine();
           }
       }
   }
   ```

   当上面的代码被编译和执行时，它会产生下列结果：

   ```
   a 大于 20
   a 的值是 100
   ```

   * if…else   if…else

   ```c#
   using System;
   
   namespace DecisionMaking
   {
       
       class Program
       {
           static void Main(string[] args)
           {
   
               /* 局部变量定义 */
               int a = 100;
   
               /* 检查布尔条件 */
               if (a == 10)
               {
                   /* 如果 if 条件为真，则输出下面的语句 */
                   Console.WriteLine("a 的值是 10");
               }
               else if (a == 20)
               {
                   /* 如果 else if 条件为真，则输出下面的语句 */
                   Console.WriteLine("a 的值是 20");
               }
               else if (a == 30)
               {
                   /* 如果 else if 条件为真，则输出下面的语句 */
                   Console.WriteLine("a 的值是 30");
               }
               else
               {
                   /* 如果上面条件都不为真，则输出下面的语句 */
                   Console.WriteLine("没有匹配的值");
               }
               Console.WriteLine("a 的准确值是 {0}", a);
               Console.ReadLine();
           }
       }
   }
   ```

   

   当上面的代码被编译和执行时，它会产生下列结果：

   ```
   没有匹配的值
   a 的准确值是 100
   ```

### 1.3 嵌套 `if` 语句

1. 语法

   C# 中 **嵌套 if** 语句的语法：

   ```
   if( boolean_expression 1)
   {
      /* 当布尔表达式 1 为真时执行 */
      if(boolean_expression 2)
      {
         /* 当布尔表达式 2 为真时执行 */
      }
   }
   ```

### 1.4 `switch` 语句

1. 语法

	* C# 中 **switch** 语句的语法：

	```
	switch(expression){
    case constant-expression  :
       statement(s);
       break; 
    case constant-expression  :
       statement(s);
       break; 
    
    /* 您可以有任意数量的 case 语句 */
    default : /* 可选的 */
       statement(s);
       break; 
}
	```

	* **switch** 语句必须遵循下面的规则：

	  1. **switch** 语句中的 **expression** 必须是一个整型或枚举类型，或者是一个 class 类型，其中 class 有一个单一的转换函数将其转换为整型或枚举类型。
	  2. 在一个 switch 中可以有任意数量的 case 语句。每个 case 后跟一个要比较的值和一个冒号。
	  3. case 的 **constant-expression** 必须与 switch 中的变量具有相同的数据类型，且必须是一个常量。
	  4. 当被测试的变量等于 case 中的常量时，case 后跟的语句将被执行，直到遇到 **break** 语句为止。
	  5. 当遇到 **break** 语句时，switch 终止，控制流将跳转到 switch 语句后的下一行。
	  6. 不是每一个 case 都需要包含 **break**。如果 case 语句为空，则可以不包含 **break**，控制流将会 *继续* 后续的 case，直到遇到 break 为止。
	  7. C# 不允许从一个开关部分继续执行到下一个开关部分。如果 case 语句中有处理语句，则必须包含 **break** 或其他跳转语句。
	  8. 一个 **switch** 语句可以有一个可选的 **default** case，出现在 switch 的结尾。default case 可用于在上面所有 case 都不为真时执行一个任务。default case 中的 **break** 语句不是必需的。
	  9. C# 不支持从一个 case 标签显式贯穿到另一个 case 标签。如果要使 C# 支持从一个 case 标签显式贯穿到另一个 case 标签，可以使用 goto 一个 switch-case 或 goto default。
	
2. 流程图

   ![img](assets/switch_statement.jpg)

3. 实例

   ```c#
   using System;
   
   namespace DecisionMaking
   {
       
       class Program
       {
           static void Main(string[] args)
           {
               /* 局部变量定义 */
               char grade = 'B';
   
               switch (grade)
               {
                   case 'A':
                       Console.WriteLine("很棒！");
                       break;
                   case 'B':
                   case 'C':
                       Console.WriteLine("做得好");
                       break;
                   case 'D':
                       Console.WriteLine("您通过了");
                       break;
                   case 'F':
                       Console.WriteLine("最好再试一下");
                       break;
                   default:
                       Console.WriteLine("无效的成绩");
                       break;
               }
               Console.WriteLine("您的成绩是 {0}", grade);
               Console.ReadLine();
           }
       }
   }
   ```

   当上面的代码被编译和执行时，它会产生下列结果：

   ```
   做得好
   您的成绩是 B
   ```

### 1.5 嵌套 `switch` 语句

 1. 语法

    ​	您可以把一个 **switch** 作为一个外部 **switch** 的语句序列的一部分，即可以在一个 **switch** 语句内使用另一个 **switch** 语句。即使内部和外部 switch 的 case 常量包含共同的值，也没有矛盾。

    C# 中 **嵌套 switch** 语句的语法：

    ```c#
    switch(ch1) 
    {
       case 'A': 
          printf("这个 A 是外部 switch 的一部分" );
          switch(ch2) 
          {
             case 'A':
                printf("这个 A 是内部 switch 的一部分" );
                break;
             case 'B': /* 内部 B case 代码 */
          }
          break;
       case 'B': /* 外部 B case 代码 */
    }
    ```

    

## 2.三元表达式 ? : 

我们已经在前面的章节中讲解了 **条件运算符 ? :**，可以用来替代 **if...else** 语句。它的一般形式如下：

```
Exp1 ? Exp2 : Exp3;
```

其中，Exp1、Exp2 和 Exp3 是表达式。请注意，冒号的使用和位置。

? 表达式的值是由 Exp1 决定的。如果 Exp1 为真，则计算 Exp2 的值，结果即为整个 ? 表达式的值。如果 Exp1 为假，则计算 Exp3 的值，结果即为整个 ? 表达式的值。

实例

```c#
string flag = 1 > 2 ? "假的" : "真的";
```

