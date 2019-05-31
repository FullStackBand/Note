# Optional容器类

>　用于尽量避免空指针异常
## 1.方法
### 静态方法

|方法名|描述|
|-|-|
|static <T> Optional<T> empty()  |空的Optional实例|
|static <T> Optional<T> of(T value)  |返回具有Optional的当前非空值的Optional**,如果为空发生异常**|
|static <T> Optional<T> ofNullable(T value)  |返回一个Optional指定值的Optional，如果非空，则返回一个空的Optional，**不会发生异常**|
* 实例
1. empty
```java
        //of 获取一个非null的对象，如果为null则发生异常
        Optional<Employee> employee = Optional.of(new Employee());
        System.out.println(employee);
        //empty 获取一个空的自己
        Optional<Employee> empty = Optional.empty();
        System.out.println(empty);
        //和 of不同，ofNullable 允许获取一个null对象；
        Optional<Employee> employee1 = Optional.ofNullable(new Employee());
        Optional<Employee> employee2 = Optional.ofNullable(null);
        System.out.println(employee1);
        System.out.println(employee2);
```

###其他常用方法

|方法|描述|
|-|-|
|isPresent()|判断是否包含值|
|orElse(T t)| 如果调用对象包含值，返回该值，否则返回t|
|orElseGet(Supplier s) |如果调用对象包含值，返回该值，否则返回 s 获取的值|
|map(Function f)| 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()|
|flatMap(Function mapper)|与 map 类似，要求返回值必须是Optional|

