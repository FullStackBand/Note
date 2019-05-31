**************************
> [String官方文档](http://redisdoc.com/string/index.html)
# String
|命令|语法|释义|返回值|说明 
|-|-|-|-|-|
|set|`SET key value [EX seconds] [PX milliseconds] [NX|XX]`|将字符串`value`关联到`key`|`ok`| - ***ex***:存在秒;     - ***px***:存在毫秒; - ***nx***:`key`不存在时操作;  - ***xx***:`key`存在时操作
|setnx|`SETNX key value` |`key`不存在的时候写入`value`| - <u>**成功**</u>:`1`; - <u>**失败**</u>:`0`|
|setex|`SETEX key seconds value`|将`key`的值设为`value`,并将生命值设为`seconds`|<u>**成功**</u>:`ok`|
|psetex|`PSETEX key milliseconds value`|和`setex`相似但是设置时间为毫秒|- <u>**成功**</u>:`ok`|
|get|`GET key  `|返回`key`的值|- **<u>不存在</u>**:`nil` - <u>**存在字符串**</u>:value值; - **<u>非字符串</u>**:返回错误|
|getset|`GETSET key value`|将键 `key`的值设为 `value` ， 并返回键 `key` 在被设置之前的旧值| - 返回旧值 - 不存在旧值返回`nil` - 存在不是字符串返回错误|
|strlen|`STRLEN key`|返回`key`储存的字符串长度| - 返回字符串长度;  - 不存在返回`0` - 不是字符串返回错误|
|append|`APPEND key value`|追加`value`,如果不存在`key`新增 key 写入value|返回追加后的长度|
|setrange|`SETRANGE key offset value`|从偏移量`offset`开始用`value`覆写|返回字符串的长度| - 不存在,当做空白字符串处理 - 偏移量超过总量,中间使用`\x00`填充,不可以溢出(512MB)|
|getrange|`GETRANGE key start end`|返回start和end两个偏移量的内容|返回指定位置的字符串|`-1`表示倒数第一个字符串,`-2`表示倒数第二个,不支持回绕操作
|incr|`INCR key`|为`key`加上1 | 返回操作之后的值 | - 不存在写入`0` - 不能解释为数字返回错误 - 必须是64位(bit)以内的数字
|incrby|`INCRBY key increment`| 指定加上`increment`|同上|同上|
|incrbyfloat|`INCRBYFLOAT key increment`| 指定增加浮点数 | 同上 | -  目标值和增加值都必须是浮点数 - 其余同上|
|decr|`DECR key`| 减去`1`|同上| 同`incr`|
|decrby|`DECRBY key decrement`|减去指定的`decrement`|同上|同上|
|mset|`MSET key value [key value …]`| 同时赋值多个|返回 ok| 原子统一性|
|msetnx|`MSETNX key value [key value …]`|同时给多个不存在的key赋值| - 成功:`1` - 失败:`0`| 同上(如果有一个存在,所有都赋值失败)
|mget|`MGET key [key ...]`|返回一个或多个值|不存在返回`nil`|

