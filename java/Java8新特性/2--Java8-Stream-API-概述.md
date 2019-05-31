# 基本操作步骤

1. 创建Stream
2. 中间操作
3. 终止操作

## 1.创建Stream

1. Collection提供了两个方法
  * stream()
    获取一个顺序流
```java
Stream<String> stream = list.stream();
```
  * parallelStream()
    获取一个并行流
```java
Stream<String> parallelStream = list.parallelStream();
```
> **并行流** : 就是把一个内容分成多个数据块，并用不同的线程分 别处理每个数据块的流
2. 通过Arrays 中的 stream() 获取一个数组流
```java
        Integer[] nums = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(nums);
```

3.通过Stream中静态方法of()创建
```java
Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
```

4. 创建无限流
* 迭代方式
```java
        Stream<Integer> stream3 = Stream.iterate(0, x -> x + 3);
        stream3.forEach(System.out::println);
```
* 生成方式
```java
        Stream<Double> stream4 = Stream.generate(Math::random);
        stream4.forEach(System.out::println);
```
## 2.中间操作

[**请看下一节：3. Java8 Stream 操作**]

## 3. 终止操作
## 1. 查找与匹配

|方法|描述|
|-|-|
|allMatch|检查是否匹配所有元素|
|anyMatch|检查是否至少匹配一个元素|
|noneMatch|检查是否没有匹配的元素|
|findFirst|返回第一个元素|
|findAny|返回当前流中的任意元素|
|count|返回流中元素的总个数|
|max|返回流中最大值|
|min|返回流中最小值|

* 例子
```java
        //筛选
        //allMatch——检查是否匹配所有元素
        boolean match = emps.stream().allMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        //anyMatch——检查是否至少匹配一个元素
        boolean match1 = emps.stream().anyMatch(e -> e.getStatus().equals(Employee.Status.FREE));
        //noneMatch——检查是否没有匹配的元素
        boolean match3 = emps.stream().noneMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        //findFirst——返回第一个元素
        Optional<Employee> first = emps.stream().findFirst();
        Employee employee = first.get();
        //findAny——返回当前流中的任意元素
        Optional<Employee> any = emps.stream().findAny();
        Employee employee1 = any.get();
        //count——返回流中元素的总个数
        long count = emps.stream().count();
        //max——返回流中最大值
        Optional<Integer> max = emps.stream().map(Employee::getAge).max(Integer::compare);
        Integer integer = max.get();
        //min——返回流中最小值
        emps.stream().map(Employee::getId).min(Integer::compare);
```

> 注意：流进行了终止操作后，不能再次使用

## 2. 归约

|方法|描述|
|-|-|
|reduce(T iden, BinaryOperator b)|可以将流中元素反复结合起来，得到一个值。返回T|
|reduce(BinaryOperator b)|可以将流中元素反复结合起来，得到一个值。返回Optional<T>|

```java
        Optional<Integer> reduce = numList.stream().reduce((x, y) -> x + y);
        int s = reduce.get();
        System.out.println(s);

        Optional<Integer> reduce1 = emps.stream().map(Employee::getAge).reduce(Integer::max);
        System.out.println(reduce1.get());
```
