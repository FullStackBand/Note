# Stream 数据流

> 在1.8开始之后 , 提供了一种快速处理数据的能力 , Collection 增加了响应的方法

- 并行数据流计算 : `public default Stream<E> parallelStream();` - CPU多核是推荐
- 数据流计算 : `public default Stream<E> stream();`

> 他比普通的迭代快 100倍

## Stream 基础操作

流的操作是为了避免迭代器对性能的浪费 , 他的设计目的是为了大数据准备的 . 

```java
List<String> all = new ArrayList<>();
Collections.addAll(all, "Java", "JaveScript", "SQL", "Python");
Stream<String> stream = all.stream(); // 转为数据流
System.out.println(stream.filter((ele) -> ele.toLowerCase().contains("j")).count());
```

结果

```cmd
2
```

范例 : 将所有含有`j`的字符串生成新的集合

```java
List<String> all = new ArrayList<>();
Collections.addAll(all, "Java", "JaveScript", "SQL", "Python");
Stream<String> stream = all.stream(); // 转为数据流
System.out.println(stream.filter((ele) -> ele.toLowerCase().contains("j")).collect(Collectors.toList()));
```

结果

```cmd
[Java, JaveScript]
```

> 轻松地采集除了我们需要的内容

范例 :

-  设计取出最大的数据量 : `public Stream<T> limit(long maxSize);`

- 跳过多少数据量 : `public Stream<T> skip(long n);`

```java
List<String> all = new ArrayList<>();
Collections.addAll(all, "Java", "JaveScript", "SQL", "Python");
Stream<String> stream = all.stream(); // 转为数据流
System.out.println(stream.filter((ele) -> ele.toLowerCase().contains("j")).skip(1).limit(1).collect(Collectors.toList()));
```

结果 :

```cmd
[JaveScript]
```

## MapReduce 模型

针对Google提出的分布式数据计算模型进行了实现 , 而这个模型就成为==MapReduce== , 实际这个模型有两个处理阶段 

1. Map处理 : 对数据进行各种前期处理  ;
2. Reduce处理 : 数据的统计计算 ;

范例 : 使用 Stream 实现 MapReduce 处理

```java
List<Order> list = new ArrayList<>();
list.add(new Order("A娃娃", 19.8, 200));
list.add(new Order("B娃娃", 19.8, 300));
list.add(new Order("C娃娃", 19.8, 13));
list.add(new Order("D玩具", 19.8, 1600));
list.add(new Order("E玩具", 19.8, 500));
DoubleSummaryStatistics statistics = list.stream().filter((ele) -> ele.getName().contains("娃娃")).mapToDouble((orderObject) -> orderObject.getPrice() * orderObject.getAmount()).summaryStatistics();
System.out.println("平均" + statistics.getAverage());
System.out.println("数量" + statistics.getCount());
System.out.println("最高" + statistics.getMax());
System.out.println("最低" + statistics.getMin());
System.out.println("总计" + statistics.getSum());
```

结果 : 

```java
平均3385.7999999999997
数量3
最高5940.0
最低257.40000000000003
总计10157.4
```

