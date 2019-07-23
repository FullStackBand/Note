# 1. Job和JobDetail

## 1.1 `Job`

  * 工作任务调度的接口，任务类需要实现该接口。该接口中定义`execute`方法，类似JDK中TimeTask类提供的run方法。在里面编写任务执行的业务逻辑。
  * ·Job`实例在Quartz中的生命周期：每次调度器执行Job时，它在调用·execute·方法前会创建一个新的Job实例，当调用完成后，关联的Job对象实例会被释放，释放的实例会被垃圾回收机制回收。

## 1.2 `JobDetail`

  * `JobDetail`为`Job`实例提供了许多设置属性，以及`JobDataMap`成员变量属性，他用来存储特定`Job`实例的状态信息，调度器需要借助`JobDetail`对象来添加`Job`实例
  * `JobDetail`重要属性：`name`、`group`、`jobClass`、`jobDataMap`

  ```java
  JobDetail job = JobBuilder.newJob(HelloJob.class)
      						.withIdentity("job","group1")//定义该实例唯一标示和指定一个组
      						.build();
  
  System.out.println("name:"+job.getKey().getName());
  System.out.println("name:"+job.getKey().getGroup());
  System.out.println("name:"+job.getJobClass().getName());
  ```

  

# 2. JobExecutionContext

* 当`Scheduler`调用一个`Job`，就会将`JobExecutionContext`传递给`Job`的`execute()`方法；
* `Job`能透过`JobExectionContext`对象访问到`Quartz`运行时候的环境以及`Job`本身的明细数据。

```java
public void execute(JobExecutionContext context){
    //可以获取相关的所有数据
    JobKey key = context.getJobDetail().getKey();
    String name = key.getName();//任务名称
    String group = key.getGroup();//任务组
    String jobName = context.getJobDetail().getJobClass().getName();//任务类名称
}
```



# 3. JobDataMap

## 3.1 使用Map获取

   * 在进行任务调度室,JobDataMap存储在JobExecutionContext中,非常方便获取

   * JobDataMap可以用来装载任何可序列化的数据对象,当Job实例对象被执行时这些参数会传递给它

   * JobDataMap实现了JDK的Map接口,并且添加了非常方便的方法用来存取基本数据类型

   * 声明JobDetail

     ```java
     public class MyJob implements Job {
     
     	public void execute(JobExecutionContext context)
     			throws JobExecutionException {
     		// TODO Auto-generated method stub
     		System.out.println("************************");
     		Date date = new Date();
     		Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     		String time = format.format(date);
     		System.out.println("当前时间 : " + time);
     
     		// Job相关数据
     		String Jname = context.getJobDetail().getKey().getName();// 任务名称
     		String JCname = context.getJobDetail().getJobClass().getName();// 任务类名称
     		String Jmsg = context.getJobDetail().getJobDataMap().getString("msg");// 传递的参数
     		System.out.println("任务名称 = " + Jname);
     		System.out.println("任务类 = " + JCname);
     		System.out.println("任务参数 = " + Jmsg);
     		// Trigger相关数据
     		String Tname = context.getTrigger().getKey().getName();
     		String Tmsg = context.getTrigger().getJobDataMap().getString("msg");
     		System.out.println("触发器名称 = " + Tname);
     		System.out.println("触发器参数 = " + Tmsg);
     		System.out.println("************************");
     
     	}
     
     }
     ```

     

   * 调用方法

     ```java
     		// 任务类
     		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)// 加载任务类
     				.withIdentity("myJob")// 任务名称
     				.usingJobData("msg", "我是任务类")// 传递参数
     				.build();
     		SimpleScheduleBuilder.simpleSchedule();
     		// 触发器
     		SimpleTrigger trigger = TriggerBuilder.newTrigger()
     				.withIdentity("myTrigger")// 触发器名
     				.startNow()// 马上启动触发器
     				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))// SimpleTrigger
     				.usingJobData("msg", "我是触发器")// 传递参数
     				.build();
     		//调度器
     		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
     		scheduler.start();
     		scheduler.scheduleJob(jobDetail, trigger);
     ```

   * 结果

     ```cmd
     ************************
     当前时间 : 2019-04-12 07:30:54
     任务名称 = myJob
     任务类 = cn.luke.MyJob
     任务参数 = 我是任务类
     触发器名称 = myTrigger
     触发器参数 = 我是触发器
     ************************
     ************************
     当前时间 : 2019-04-12 07:30:59
     任务名称 = myJob
     任务类 = cn.luke.MyJob
     任务参数 = 我是任务类
     触发器名称 = myTrigger
     触发器参数 = 我是触发器
     ************************
     ************************
     当前时间 : 2019-04-12 07:31:04
     任务名称 = myJob
     任务类 = cn.luke.MyJob
     任务参数 = 我是任务类
     触发器名称 = myTrigger
     触发器参数 = 我是触发器
     ************************
     ```

     

# 4. 有状态Job和无状态Job

`@PersistJobDataAfterExecution`**注解的使用**

有状态的Job可以理解为多次Job调用期间可以持续持有的一些状态信息,这些状态信息存储在JobDataMap中,而默认的无状态Job每次调用时都会创建一个新的JobDataMap。

## 4.1 无状态

### 4.1.1 修改`main`方法.usingJobData("count",0)`表示计时器.

   ```java
   // 任务类
   JobDetail jobDetail = JobBuilder.newJob(MyJob.class)// 加载任务类
       .withIdentity("myJob")// 任务名称
       .usingJobData("msg", "我是任务类")// 传递参数
       .usingJobData("count", 0)// 传递参数
       .build();
   ```

### 4.1.2 修改`MyJob`类

   添加count的setting和getting方法

   ```java
   private Integer count;
   
   public Integer getCount() {
       return count;
   }
   
   public void setCount(Integer count) {
       this.count = count;
   }
   ```

   execute方法添加

   ```java
   // count处理
   ++count;// 累加
   context.getJobDetail().getJobDataMap().put("count", count);
   System.out.println("************************"+count);
   ```

### 4.1.3 执行结果   每次都是1

   ```cmd
   ************************
   当前时间 : 2019-04-12 07:45:17
   任务名称 = myJob
   任务类 = cn.luke.MyJob
   任务参数 = 我是任务类
   触发器名称 = myTrigger
   触发器参数 = 我是触发器
   ************************1
   ************************
   当前时间 : 2019-04-12 07:45:22
   任务名称 = myJob
   任务类 = cn.luke.MyJob
   任务参数 = 我是任务类
   触发器名称 = myTrigger
   触发器参数 = 我是触发器
   ************************1
   ```

## 4.2 有状态

### 4.2.1 在`MyJob`添加注解

   ```java
   @PersistJobDataAfterExecution//每次执行都会持久化JodDataMap
   public class MyJob implements Job {
       //...省略代码
   }
   ```

### 4.2.2 执行结果

   ```cmd
   ************************
   当前时间 : 2019-04-12 07:48:10
   任务名称 = myJob
   任务类 = cn.luke.MyJob
   任务参数 = 我是任务类
   触发器名称 = myTrigger
   触发器参数 = 我是触发器
   ************************1
   ************************
   当前时间 : 2019-04-12 07:48:15
   任务名称 = myJob
   任务类 = cn.luke.MyJob
   任务参数 = 我是任务类
   触发器名称 = myTrigger
   触发器参数 = 我是触发器
   ************************2
   ************************
   当前时间 : 2019-04-12 07:48:20
   任务名称 = myJob
   任务类 = cn.luke.MyJob
   任务参数 = 我是任务类
   触发器名称 = myTrigger
   触发器参数 = 我是触发器
   ************************3
   ```

   



# 5. Trigger

![](未命名文件(4).png)

Quartz有一些不同的触发器类型,不过用的最多的是`SimpleTriiger`和`CronTrigger`

## 5.1 `jobKey`

   表示job实例的表示,触发器被触发时,该指定的job实例会被执行

## 5.2 `startTime`

   表示触发器的时间表,第一次开始被出发的时间,它的数据类型为`java.util.Date`

## 5.3 `endTime`

   指定触发器终止被触发的时间,它的数据类型是`java.util.Date`

* 设置开始和结束时间

```java
public static void main(String[] args) throws SchedulerException {
    // 任务类
    JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)// 加载任务类
        .withIdentity("myJob")// 任务名称
        .usingJobData("count", 0)// 传递参数
        .build();
    SimpleScheduleBuilder.simpleSchedule();
    // 触发器
    SimpleTrigger trigger = TriggerBuilder.newTrigger()
        .withIdentity("myTrigger")// 触发器名
        // .startNow()// 马上启动触发器
        .startAt(new Date())// 开始时间*********************************************
        .endAt(new Date(new Date().getTime() + 10000))// 结束时间*******************
        .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2))// SimpleTrigger
        .build();
    // 调度器
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler.start();
    scheduler.scheduleJob(jobDetail, trigger)
}
```

* 查看当前任务的信息

```java
public void execute(JobExecutionContext context)
		throws JobExecutionException {
	// TODO Auto-generated method stub
	System.out.println("************************");
	Date date = new Date();
	Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	String time = format.format(date);
	System.out.println("当前时间 : " + time);
	System.out.println("工作内容是 : __________________________");
	System.out.println("JobKey name = " +context.getTrigger().getJobKey().getName());
	System.out.println("JobKey group= " +context.getTrigger().getJobKey().getGroup());
	System.out.println("任务开始时间 = " + format.format(context.getTrigger().getStartTime()));
	System.out.println("任务结束时间 = " + format.format(context.getTrigger().getEndTime()));
}
```

* 执行结果

```java
************************
当前时间 : 2019-04-15 07:50:11
工作内容是 : __________________________
JobKey name = myJob
JobKey group= DEFAULT
任务开始时间 = 2019-04-15 07:50:11
任务结束时间 = 2019-04-15 07:50:21
************************
当前时间 : 2019-04-15 07:50:13
工作内容是 : __________________________
JobKey name = myJob
JobKey group= DEFAULT
任务开始时间 = 2019-04-15 07:50:11
任务结束时间 = 2019-04-15 07:50:21
************************
当前时间 : 2019-04-15 07:50:15
工作内容是 : __________________________
JobKey name = myJob
JobKey group= DEFAULT
任务开始时间 = 2019-04-15 07:50:11
任务结束时间 = 2019-04-15 07:50:21
************************
当前时间 : 2019-04-15 07:50:17
工作内容是 : __________________________
JobKey name = myJob
JobKey group= DEFAULT
任务开始时间 = 2019-04-15 07:50:11
任务结束时间 = 2019-04-15 07:50:21
************************
当前时间 : 2019-04-15 07:50:19
工作内容是 : __________________________
JobKey name = myJob
JobKey group= DEFAULT
任务开始时间 = 2019-04-15 07:50:11
任务结束时间 = 2019-04-15 07:50:21
```



# 6. SimpleTrigger

`SimpleTrigger`对于设置和使用是最为简单的QuartzTrigger

他是为那种需要在特定的日期/时间启动,且以一个可能的间隔时间重复执行n次的Job所涉及的.

案例一 : 表示在一个指定的时间段内,执行一次作业任务 .

案例二 : 在指定时间内,完成多次的任务调度

# 7. CronTrigger

如果你需要像日历那样按照日程来触发任务,而不是像`SimpleTrigger`一样每个特定的间隔时间触发,`CronTriigers`通常比`SimpleTrigger`更有用,因为它是基于日历的作业调度.

使用`CronTrigger`,你可以指定诸如"每个周五的中午"之类的任务,也可以指定开始时间和结束时间.

## 7.1 `CronExpressions` ~~--~~ `Cron`表达式

   Cron表达式被用来配置CronTrigger实例,Cron表达式是由一个7个子表达式组成的字符串.每个表达式都可以描述一个单独的日程细节.这些表达式用空格分割,分别表示 :

### 7.1.1 七个域列表

      | 名称 | 是否必须 | 允许值 | 特殊字符        |
      | :--- | :------- | :----- | :-------------- |
      |  秒  | 是       | 0~59   | , - * / |
      | 分 | 是 | 0~59 |       , - * /   |
      | 时 | 是 | 0~23 |    , - * /      |
      | 日 | 是 | 1~31 |    , - * / L W C    |
      | 月 | 是 | 1~12 或 JAN~DEC | , - * / |
      | 周 | 是 | 1~7 或 SUN~SAT(1对应的礼拜天) | , - * / L C # |
      | 年 | 否 | 空 或 1970~2099 | , - * / |

### 7.1.2 特殊符号

      | 符号 | 说明                                                         |
      | :--- | :----------------------------------------------------------- |
      | `,`  | `x,y` 表示x和y                                               |
      | `-`  | `x-y` 表示x到y                                               |
      | `*`  | 表示每个Time                                                 |
      | `/`  | `x/y` 表示从x起每隔y                                         |
      | `?`  | 不指定                                                       |
      | `L`  | `L2` 表示末尾的两天 , `L-3` 表示倒数第3                      |
      | `W`  | `15W` 表示最接近15号的工作日,可能是15号,也可能是15号前后(日)专用 |
      | `C`  | 表示和Calendar计划关联的值,如`1C`表示,1日或1日后包括Carlendar的第一天 |
      | `LW` | L和W的组合,某月的最后一个工作日(日专用)                      |
      | `#`  | 表示第几个周几,`x#y` y表示第几个,x表示周的值,如`6#2`,表示第二个周五(6表示周五) |

### 7.1.3 示例:

      | 表达式                       | 注释                                                       |
      | ---------------------------- | ---------------------------------------------------------- |
      | `"0 0 10,14,16 * * ?"`       | 每天上午10点,下午2点,4点                                   |
      | `"0 0/30 9-17 * *"`          | 朝九晚五工作时间没半个小时,从0分开始每隔30分发送一次       |
      | `"0 0 12 ? * WED"`           | 每个星期三中午12点                                         |
      | `"0 0 12 * * ?"`             | 每天中午12点                                               |
      | `"0 15 10 ? * *"`            | 每天上午10点15分                                           |
      | `"0 15 10 * * ?"`            | 每天上午10点15分                                           |
      | `"0 15 10 * * ? *"`          | 每天上午10点15分                                           |
      | `"0 15 10 * * ? 2005"`       | 2005年的每天上午10点15分                                   |
      | `"0 * 14 * * ?"`             | 每天下午2点到2点59期间,每分钟                              |
      | `"0 0/55 14 * * ?"`          | 每天下午2点到2点55期间,从0开始到55分钟触发                 |
      | `"0 0/55 14,18 * * ?"`       | 每天下午2点到2点55期间和6点到6点55期间,从0开始到55分钟触发 |
      | `"0 0-5 14 * * ?"`           | 每天下午2点到2点05期间,每分钟                              |
      | `"0 10,44 14 ? 3 WED"`       | 每年三月的星期三的下午2点10分和2点44分触发                 |
      | `"0 15 10 ? * MON-FRI"`      | 周一至周五的上午10点15触发                                 |
      | `"0 15 10 15 * ?"`           | 每月15日上午10点15分触发                                   |
      | `"0 15 10 L * ?"`            | 每月最后一日的上午10点15分                                 |
      | `"0 15 10 ? * 6L"`           | 每月的最后一个星期五上午10点15分                           |
      | `"0 15 10 ? * 6L 2002-2005"` | 2002年到2005年每月的最后一个星期五上午10点15分             |
      | `"0 15 10 ? * 6#3"`          | 每月的第三个星期五上午10点15分                             |



## 7.2 案例

### 7.2.1Job

     ```java
     public class MyJob2 implements Job {
     	public void execute(JobExecutionContext context)
     			throws JobExecutionException {
     		// TODO Auto-generated method stub
     		System.out.println("************************");
     		Date date = new Date();
     		Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     		String time = format.format(date);
     		System.out.println("当前时间 : " + time);
     	}
     }
     ```

### 7.2.2CronTrigger

```java
public static void main(String[] args) throws SchedulerException {
    // 任务类
    JobDetail jobDetail = JobBuilder.newJob(MyJob2.class)// 加载任务类
        .withIdentity("myJob")// 任务名称
        .build();
    // 触发器
    CronTrigger cronTrigger = TriggerBuilder.newTrigger()
        .withIdentity("myTrigger")// 触发器名
        .startNow()// 马上启动触发器
        .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))// CronTrigger
        .build();
    // 调度器
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler.start();
    scheduler.scheduleJob(jobDetail, cronTrigger);
}
```


> 小提示
>
> 1. L和W可以一起使用(企业可以用在工资计算)
> 2. #可表示月中的第几个周(可用在计算父亲节母亲节)
> 3. 周的字段英文不区分大小写(MON == mon)
> 4. 利用工具在线生成

