# 一、使用LocalDate、LocalTime、LocalDateTime

LocalDate、LocalTime、LocalDateTime 类的实例是不可变的对象，分别表示使用ISO-8601日历系统的日期、时间、日期和时间。它们提供了简单的日期或时间，并不包含当前的时间信息。也不包含与时区相关的信息。
```java
        //获取当前日期
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        //获取当前时间
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        //获取当前的时间和日期
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
```

|方法|描述|
|-|-|
|now()|静态方法，根据当前时间创建对象|
|of()|静态方法，根据指定日期/时间创建对象|
|plusDays,plusWeeks,plusMonths,plusYears|向当前LocalDate对象添加几天、几周、几个月、几年|
|minusDays,minusWeeks,minusMonths,minusYears|从当前LocalDate对象减去几天、几周、几个月、几年|
|plus,minus|添加或减少一个Duration或Period|
|withDayOfMonth,withDayOfYear,withMonth,withYear|将月份天数、年份天数、月份、年份修改为指定的值并返回新的LocalDate对象|
|getDayOfMonth|获得月份天数(1-31)|
|getDayOfYear|获得年份天数(1-366)|
|getDayOfWeek|获得星期几(返回一个DayOfWeek枚举值)|
|getMonth|获得月份,返回一个Month枚举值|
|getMonthValue|获得月份(1-12)|
|getYear|获得年份|
|until|获得两个日期之间的Period对象，或者指定ChronoUnits的数字|
|isBefore,isAfter|比较两个LocalDate|
|isLeapYear|判断是否是闰年|



# 二、Instant 时间戳

用于“时间戳”的运算。它是以Unix元年(传统的设定为UTC时区1970年1月1日午夜时分)开始所经历的描述进行运算

# 三、Duration 和Period

* Duration:用于计算两个“时间”间隔
* Period:用于计算两个“日期”间隔

> 很简单，自己看API 懒得写了哈哈哈哈啊哈哈

