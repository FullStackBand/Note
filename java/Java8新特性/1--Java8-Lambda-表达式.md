# 一、Lambda表达式
Lambda 是一个**匿名函数**，我们可以吧Lambda表达式理解为一段可以传递的代码（将代码像数据一样传送）。可以写出更加简洁的、更加灵活的代码。作为一种更加紧凑的代码风格，是Java 的语言表达能力得到了提升。

## 1.基础语法

* Java8中引入了一个新的操作符`->`该操作符称之为箭头操作符或Lambda操作符。
* 箭头将Lambda表达式拆分为两部分：
  * 左侧：Lambda表达式的参数列表
  * 右侧：Lambda表达式中所需执行的功能，即Lambda体
##2.语法格式

1. 无参，无返回值
```java
    //无参，无返回值
	@Test
	public void text1() {
		TestClass1 tc = () -> System.out.println("Lambda体");
		tc.test();
	}
```
2. 有一个参数，并且没有返回值
```java
    //一个参数，无返回值
	@Test
	public void test2(){
		TestClass2 tc = (n) -> System.out.println("数字="+n);
		tc.test(231);
	}
```
3. 若只有一个参数，小括号可以省略不写
```java
	// 一个参数，并且没有返回值
	@Test
	public void test3(){
		TestClass2 tc = n -> System.out.println("数字="+n);
		tc.test(564);
	}
```
4. 有两个以上的参数，有返回值
```java
	// 有两个以上的参数，有返回值
	@Test
	public void test4() {
		TestClass3 tc = (a, b, c) -> {
			int d = a * b * c;
			return d;
		};
		System.out.println(tc.test(7, 8, 9));
	}
```
5. 若Lambda体只有一条语句，return 和 大括号都可以不写
```java
    //有两个以上的参数，有返回值
	@Test
	public void test4(){
		TestClass3 tc = (a,b,c) -> a*b*c;
		System.out.println(tc.test(7,8,9));
	}

```
6. Lambda 表达式的参数列表的数据类型可以省略不写，因为接口函数已经命名了JVM可以通过上下文编辑器获取得到类型。

>  左右遇一括号省
>  左侧推断类型省
>  能省则省

> **注意：需要函数式接口的支持**

# 二、函数式接口
## 1. 特点

* 只包含一个抽象方法的接口，称之为函数式接口
* 你可以通过Lambda表达式来创建该接口的对象。（若Lambda表达式抛出一个受检异常，那么该异常需要在目标接口的抽象方法上注明）
* 我们可以在任意函数式接口上使用`@FunctionalInterface`注解，这样做可以检查他是否是一个函数式接口，同时javadoc 也会包含一条声明，说明这是一个函数式接口
## 2. 自定义函数接口
普通函数式接口
```java
@FunctionalInterface
public interface MyFunction {
	public double getValue();
}
```
带泛型的函数式接口
```java
@FunctionalInterface
public interface MyFunction<T> {
	public T getValue(T t);
}

```
## 3.内置四大核心函数式接口



| 类型       | 函数是接口       | 参数类型 | 返回类型 |         方法         | 用途                                                   |
| ---------- | ---------------- | -------- | -------- | :------------------: | ------------------------------------------------------ |
| 消费型接口 | `Consumer<T>`    | T        | void     |  `void accept(T t)`  | 对类型为T的对象应用操作                                |
| 供给型接口 | `Supplier<T>`    | 无       | T        |      `T get();`      | 返回类型为T的对象                                      |
| 函数型接口 | `Function<T, R>` | T        | R        |    `R apply(T t)`    | 对类型为T的对象应用操作，并返回结果。结果是R类型的对象 |
| 断定型接口 | `Predicate<T>`   | T        | boolean  | `boolean test(T t);` | 确定类型为T的对象是否满足某约束，并返回boolean 值      |




> 单词释义：
> Consumer：消费者；用户，顾客
> Supplier  ：供应厂商，供应国；供应者
> Function  ：功能；[数] 函数；职责；盛大的集会
> Predicate  ：断定为…；使…基于

## 4.其他接口

|函数式接口|参数类型|返回类型|用途|
|-|-|-|-|
|`BiFunction<T,U,R>`|T,U|R|对类型为T,U参数应用操作，返回R类型的结果。包含方法为Rapply(Tt,Uu);|
|`UnaryOperator<T>`(Function子接口)|T|T|对类型为T的对象进行一元运算，并返回T类型的结果。包含方法为Tapply(Tt);|
|`BinaryOperator<T>`(BiFunction子接口)|T,T|T|对类型为T的对象进行二元运算，并返回T类型的结果。包含方法为Tapply(Tt1,Tt2);|
|`BiConsumer<T,U>`|T,U|void|对类型为T,U参数应用操作。包含方法为voidaccept(Tt,Uu)|
|`ToIntFunction<T>`，`ToLongFunction<T>`，|`ToDoubleFunction<T>`|T|int，long，double|分别计算int、long、double、值的函数|
|`IntFunction<R>`，`LongFunction<R>`，`DoubleFunction<R>`|int，long，double|R|参数分别为int、long、double类型的函数|

# 三、方法、构造器和数组的引用
## 1. 方法引用
若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用（可以将方法引用理解为 Lambda 表达式的另外一种表现形式）

1. `对象的引用 :: 实例方法名`
```java
        Emp emp = new Emp(1, "luke", 26, 5000);
        Supplier<Integer> sp = emp::getAge;
        System.out.println(sp.get());
```
2. `类名 :: 静态方法名`
```java
        Consumer<String> con = System.out::println;
        con.accept("hi ! luke...");
```
3. `类名 :: 实例方法名`
```java
        BiPredicate<String,String> bp = String::equals;
        boolean test = bp.test("a", "a");
        System.out.println(test);
```
> 注意：
①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName

##2. 构造器引用
构造器的参数列表，需要与函数式接口中参数列表保持一致！

`类名 :: new`
* 无参构造器
```java
        Supplier<Emp> sp = Emp::new;
        Emp emp1 = sp.get();
        System.out.println(emp1);
```
* 有参构造器
```java
        Function<String,Emp> ft = Emp::new;
        Emp emp = ft.apply("luke");
        System.out.println(emp);
```
* 多参构造器
```java
        BiFunction<Integer,String,Emp> bf = Emp::new;
        Emp emp3 = bf.apply(1, "Class雷");
        System.out.println(emp3);
```
##3. 数组引用

`类型[] :: new`
```java
        Function<Integer,String[]> ft = String[]::new;
        String[] strings = ft.apply(13);
        System.out.println(strings.length);
```











