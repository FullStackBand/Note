# 一、筛选与切片

|方法|描述|
|-|-|
| filter|接收 Lambda ， 从流中排除某些元素。|
|limit|截断流，使其元素不超过给定数量。|
| skip(n) |跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补|
| distinct|筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素|

1. filter：过滤
```
emps.stream().filter(e -> e.getAge() > 35).forEach(System.out::println);
```
2. limit：截断
```
emps.stream().limit(3).forEach(System.out::println);
```
3.skip：跳过
```
emps.stream().skip(2).forEach(System.out::println);
```

4.distinct：去掉重复（取决于hashCode() 和 equals()的算法）
```
emps.stream().distinct().forEach(System.out::println);
```

# 二、映射

|方法|描述|
|-|-|
|map|接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。|
|flatMap|接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流|

```
List<String> strList = Arrays.asList("123", "456", "789", "abc", "def");
//转换为大写
strList.stream().map(String::toUpperCase).forEach(System.out::println);
//字符串截取
strList.stream().map(e->e.substring(1)).forEach(System.out::println);
```

# 三、排序

|方法|描述|
|-|-|
|sorted()|自然排序|
|sorted(Comparator com)|定制排序|

1. 自然排序
```
strList.stream().sorted().forEach(System.out::println);
```
2. 定制排序
* 例1：
```
emps.stream().sorted((e1, e2) -> e1.getStatus().compareTo(e2.getStatus())).forEach(System.out::println);
```
* 例2：

```
        emps.stream().sorted((x,y)->{
            if (x.getAge()==y.getAge()){
                return x.getName().compareTo(y.getName());
            }else {
                return Integer.compare(x.getAge(), y.getAge());
            }
        }).forEach(System.out::println);
```
> 会根据compare方法的返回值排序，改变顺序，需把两个比较的参数调换位置即可。

​     
