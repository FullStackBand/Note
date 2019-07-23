# 1. 配置 资源 ScheduleFactory

Quartz以模块方式架构,因此,要使它运行,几个组件必须很好的咬合在一起.幸运的是已经有一些现存的助手可以完成这些工作

所有的Scheduler实例由SchedulerFactory创建

Quartz的三个核心概念 : 调度器,任务,触发器

![](D:/3.IT/luke/note/note/java/Quartz/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6(6).png)

- 一个作业,比较重要的三个要素就是Schduler,jobDetail,Trigger;
- 而Trigger对于一个Job而言就好比一个驱动器;
- 没有触发起来定时驱动作业,作业就无法运行;
- 对于Job而言,一个Job可以对应多个Trgger,但对于Trigger而言,一个Trigger智能对应一个Job;
- 所以一个Trigger智能指派给一个Job;
- 如果你需要一个复杂的Tirgger,你可以创建多个Trigger指派给Job

## 1. 1Schedule的创建方式

### 1.1 StdScheduleFactory

**Quartz默认的ScheduleFactory**

- 使用一组参数(java.util.Properties)来创建和初始化Quartz调度器

- 配置参数一般粗存在`quartz.properties`中

- 调用`getSchedule`方法就能创建和初始化调度器对象

  ```java
  ScheduleFactory ScheduleFactory = new StdSchedulerFactory();
  Schedule scheduler = ScheduleFactory.getScheduler();
  ```

#### 用法一: 输出调度器开始时间(重要)

`Date scheduleJob(JobDetail jobDetail , Trigger tirgger)`

```java
Date date = scheduler.scheduleJob(jobDetail, cronTrigger);
System.out.println("调度器开始时间 : " + date);
```

#### 用法二:启动调度任务

`void start()`

```java
Scheduler.start();
```

#### 用法三:调度任务挂起,即暂停操作

`void standby();`

```java
// 执行2秒后挂起
Thread.sleep(2000L);
scheduler.standby();
// 挂起5秒后开启
Thread.sleep(5000L);
scheduler.start();
```

#### 用法四:关闭调度任务

`void  shutdown();`

`shutdown(true)` : 

- 表示等待所有正在执行的job完毕后,再关闭 
- 等待的时候可以对Scheduler进行start和standby操作

`shutdown(false)` : 

- 表示直接关闭
- 不可以进行其他操作

### 1.2 DirectSchedulerFactory

DirectSchedulerFactory是对SchedulerFactory的直接实现,通过它可以直接构建Scheduler , threadpool等

```java
DirectSchedulerFactory directSchedulerFactory = DicectscheduerFactory.getInstance();
Scheduler scheduler = directSchedulerFactory.getScheduler();
```





# 2. Quart.properties

`org.quartz`中

```properties
# Default Properties file for use by StdSchedulerFactory
# to create a Quartz Scheduler Instance, if a different
# properties file is not explicitly specified.
#

org.quartz.scheduler.instanceName: DefaultQuartzScheduler
org.quartz.scheduler.rmi.export: false
org.quartz.scheduler.rmi.proxy: false
org.quartz.scheduler.wrapJobExecutionInUserTransaction: false

org.quartz.threadPool.class: org.quartz.simpl.SimpleThreadPool
org.quartz.threadPool.threadCount: 10
org.quartz.threadPool.threadPriority: 5
org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread: true

org.quartz.jobStore.misfireThreshold: 60000

org.quartz.jobStore.class: org.quartz.simpl.RAMJobStore
```

也可以在项目资源下自己配置quartz.properties文件,去覆盖低层的配置文件

## 2.1 调度器属性

- `org.quartz.scheduler.instanceName : DefaultQuartzScheduler`

  用来区分特定的调度器实例,可以按照功能用途来给调度去起名.

- `org.quartz.jobStore.instanceNameId: 60000`

  这个值必须在所有调度器实例中唯一的,尤其是在一个集群环境,作为集群的唯一key.如果需要quartz帮你生成这个值得话,可以设置为`AUTO`



## 2.2 线程池属性

- `org.quartz.threadPool.threadCount: 10`

  处理Job的线程个数,至少为1,最多必要超过100,在多数机器上设置该值超过100的话,就显得相当不实用了,特别是在Job执行时间较长的情况下

- `org.quartz.threadPool.threadPriority: 5`

  线程优先级,最小为1,最大为10,默认为5

- `org.quartz.threadPool.class:  org.quartz.simpl.SimpleThreadPool`

  一个实现了`org.quartz.spi.ThreadPoll`接口的类,Quartz自带线程池类` org.quartz.simpl.SimpleThreadPool`



## 2.3 作业存储设置

描述了再调度实例的生命周期中,Job和Trigger信息是如何被存储的.

## 2.4 插件设置

满足特定需求用到的Quartz插件的配置

例子:

```properties
#===============================================================     
#Configure Main Scheduler Properties     调度器属性
#===============================================================  
#调度器的实例名     
org.quartz.scheduler.instanceName = QuartzScheduler     
#调度器的实例ID，大多数情况设置为auto即可  
org.quartz.scheduler.instanceId = AUTO     
 
#===============================================================     
#Configure ThreadPool     线程池属性
#===============================================================   
#处理Job的线程个数，至少为1，但最多的话最好不要超过100，在多数机器上设置该值超过100的话就会显得相当不实用了，特别是在你的 Job 执行时间较长的情况下
org.quartz.threadPool.threadCount =  5     
#线程的优先级，优先级别高的线程比级别低的线程优先得到执行。最小为1，最大为10，默认为5
org.quartz.threadPool.threadPriority = 5 
#一个实现了 org.quartz.spi.ThreadPool 接口的类，Quartz 自带的线程池实现类是 org.quartz.smpl.SimpleThreadPool      
org.quartz.threadPool.class = org.quartz.simpl.SimpleThreadPool     
 
#===============================================================     
#Configure JobStore 作业存储设置
#===============================================================      
#要使 Job 存储在内存中需通过设置  org.quartz.jobStrore.class 属性为 org.quartz.simpl.RAMJobStore 
org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore     
 
#===============================================================     
#Configure Plugins    插件配置 
#===============================================================       
org.quartz.plugin.jobInitializer.class =       
org.quartz.plugins.xml.JobInitializationPlugin       
      
org.quartz.plugin.jobInitializer.overWriteExistingJobs = true      
org.quartz.plugin.jobInitializer.failOnFileNotFound = true      
org.quartz.plugin.jobInitializer.validating=false

```

java配置

```java
package cn.luke;

import java.util.Properties;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzProperties {
	public static void main(String[] args) {
		// 创建工厂实例
		StdSchedulerFactory factory = new StdSchedulerFactory();

		// 创建Properties
		Properties properties = new Properties();
		properties.put(StdSchedulerFactory.PROP_THREAD_POOL_CLASS,
				"org.quartz.simpl.SimpleThreadPool");
		properties.put("org.quartz.threadPool.threadCount", "5");

		try {
			// 初始化工厂
			factory.initialize(properties);

			Scheduler scheduler = factory.getScheduler();

			scheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
```

# 4. 监听器

## 4.1 概念

`Quartz`的监听器用于当任务调度中你所关注事件发生时,能够及时获取这一事件的通知。类似于任务制定过程中的邮件、短信类的提醒。`Quartz`监听主要有`JobListener`、`TriggerListener`、`ScheduleListener`三种，顾名思义，分别为任务、触发器、调度器对应的监听器。三者使用方法类似，在开始介绍监听器之前，需要明确两个概念：

1. **全局监听器**

   全局监听器能够接收到所有的Job/Trigger的事件通知。

2. **非全局监听器**

   非全局监听器只能接受到在其上注册的Job或Trigger的时间。

## 4.2 `JobListener`

任务调度过程中，与任务Job相关的事件包括：

* job开始要执行的提示；
* job执行完成的提示；

```java
public interface JobListener{
    
    String getName();
    
    void jobToBeExecuted(JobExectionContext context);
    
    void jobExeectionVetoed(JobExectionContext context);
    
    void jobWasExecuted(JobExecutionContext context , JobExecutionException jobException);
    
}
```

1. getName：用于获取该JobListener的名称。
2. jobToBeExecuted：Schedule在JobDetail将要被执行是调用这个方法。
3. jobExecutionVetoed：Scheduler在JobDetail即将被执行，但又被TriggerListener否决时调用的该方法。
4. jobWasExecuted：Scheduler在JobDetail被执行之后调用这个方法

**例子：**

1. Job

```java
public class MyJob implements Job {
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		Date date = new Date();
		Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = format.format(date);
		System.out.println("任务执行时间: " + time);
	}
}
```

2. JobListener

```java
package JobListener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class MyJobListener implements JobListener {

	public String getName() {
		return this.getClass().getSimpleName();
	}

	public void jobToBeExecuted(JobExecutionContext context) {
		String name = context.getJobDetail().getKey().getName();
		System.out.println("\r\n将要被执行是调用这个方法");

	}

	public void jobExecutionVetoed(JobExecutionContext context) {
		String name = context.getJobDetail().getKey().getName();
		System.out.println("即将被执行，但又被TriggerListener否决时调用的该方法");

	}

	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		String name = context.getJobDetail().getKey().getName();
		System.out.println("被执行之后调用这个方法\r\n");

	}

}
```



2. 执行

```java
// 任务类
JobDetail jobDetail = JobBuilder.newJob(MyJob.class)// 加载任务类
    .withIdentity("myJob")// 任务名称
    .usingJobData("msg", "我是任务类")// 传递参数
    .usingJobData("count", 0)// 传递参数
    .build();
SimpleScheduleBuilder.simpleSchedule();
// 触发器
SimpleTrigger trigger = TriggerBuilder.newTrigger()
    .withIdentity("myTrigger")// 触发器名
    .startNow()// 马上启动触发器
    .withSchedule(SimpleScheduleBuilder
						.repeatSecondlyForever(5))// SimpleTrigger
										// 5
										// 秒一次
				.usingJobData("msg", "我是触发器")// 传递参数
				.build();
// 调度器
Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
scheduler.start();
scheduler.scheduleJob(jobDetail, trigger);
```

* 全局监听

```java
// 创建并注册一个全局的JobListener
scheduler.getListenerManager().addJobListener(
    new MyJobListener(), // 监听
    EverythingMatcher.allJobs()// 全局监听
);
```

* 局部监听

```java
// 创建并注册一个局部的JobListener
scheduler.getListenerManager().addJobListener(
    new MyJobListener(), // 监听
    KeyMatcher.keyEquals(JobKey.jobKey("myJob"))// 局部监听
);

```



## 4.3`TriggerListener`

任务调度过程中，与触发器Trigger相关的事件包括：触发器触发、触发器未正常触发、触发器完成等。

```java
public interface TriggerListener{
    
    public String getName();
    
    public void triggerFired(Trigger trigger , JobExecutiongContext context);
    
    public boolean vetoJobExecution(Trigger trigger , JobExecutionContext context);
    
    public void triggerMisfired(Trigger trigger);
    
    public void triggerComplete(Trigger trigger , JobExecutionContext context , int trigggerInstructionCode)
        
}
```

1. getName：用于获取触发器的名称
2. triggerFired：当与监听器发生关联的Trigger被触发，Job的execute()方法被执行时，Scheduler就调用该方法。
3. vetoJobExecution：在Trigger触发后，Job将要被执行时由Scheduler调用这个方法。TriggerListener给了一个选择去否决Job的执行。加入这个方法返回true，这个Job将不会为此次Trigger触发而得到执行。
4. triggerMisfired：Scheduler调用这个方法是在Trigger错过触发时。你应该关注此方法中持续时间长的逻辑：在出现许多错过触发的Trigger时，长逻辑会导致骨牌效应。你应当保持这个方法尽量的小。
5. triggerCompleteTrigger被触发并且完成了Job的执行时，Scheduler调用这个方法。



**例子**

1. MyTriggerListener

   ```java
   public class MyTriggerListener implements TriggerListener {
   	private String name;
   	public MyTriggerListener(String name) {
   		this.name = name;
   	}
   	public String getName() {
   		return name;
   	}
   
   	public void triggerFired(Trigger trigger, JobExecutionContext context) {
   		System.out.println("触发器被触发");
   	}
   
   	public boolean vetoJobExecution(Trigger trigger,
   			JobExecutionContext context) {
   		System.out.println("没有否决触发器");
   		// return true;// true表示不会执行
   		return false;// false表示会执行
   	}
   
   	public void triggerMisfired(Trigger trigger) {
   		System.out.println("错过触发");
   	}
   
   	public void triggerComplete(Trigger trigger,
   			JobExecutionContext context,
   			CompletedExecutionInstruction triggerInstructionCode) {
   		System.out.println("完成后触发");
   	}
   
   }
   ```

   

2. MyJobListener

   * 同上

3. 执行

```java
// 任务类
JobDetail jobDetail = JobBuilder.newJob(MyJob.class)// 加载任务类
    .withIdentity("myJob")// 任务名称
    .usingJobData("msg", "我是任务类")// 传递参数
    .usingJobData("count", 0)// 传递参数
    .build();
SimpleScheduleBuilder.simpleSchedule();
// 触发器
SimpleTrigger trigger = TriggerBuilder.newTrigger()
    .withIdentity("myTrigger")// 触发器名
    .startNow()// 马上启动触发器
    .withSchedule(SimpleScheduleBuilder
						.repeatSecondlyForever(5))// SimpleTrigger5秒一次
				.usingJobData("msg", "我是触发器")// 传递参数
				.build();
// 调度器
Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
scheduler.start();
scheduler.scheduleJob(jobDetail, trigger);
// 创建并注册一个局部的JobListener
scheduler.getListenerManager().addJobListener(
    new MyJobListener("我的Job监听器"),
    KeyMatcher.keyEquals(JobKey.jobKey("myJob")));
```


* 全局

```java
// 创建并注册一个全局的TriggerListener
scheduler.getListenerManager().addTriggerListener(
	new MyTriggerListener(), // TriggerListener
	EverythingMatcher.allTriggers() // 全局的
);
```

* 局部

```java
// 创建并注册一个局部的TriggerListener
scheduler.getListenerManager().addTriggerListener(
	new MyTriggerListener("我的触发器监听器"), // TriggerListener
	KeyMatcher.keyEquals(TriggerKey.triggerKey("myTrigger"))// 局部的
);
```

## 4.4 `SchedulerListener`

```java
public interface SchedulerListener {

	public void jobScheduled(Trigger trigger);

	public void jobUnscheduled(TriggerKey triggerKey);

	public void triggerFinalized(Trigger trigger);

	public void triggerPaused(TriggerKey triggerKey);

	public void triggersPaused(String triggerGroup);

	public void triggerResumed(TriggerKey triggerKey);

	public void triggersResumed(String triggerGroup);

	public void jobAdded(JobDetail jobDetail);

	public void jobDeleted(JobKey jobKey);

	public void jobPaused(JobKey jobKey);

	public void jobsPaused(String jobGroup);

	public void jobResumed(JobKey jobKey);

	public void jobsResumed(String jobGroup);

	public void schedulerError(String msg, SchedulerException cause);

	public void schedulerInStandbyMode();

	public void schedulerStarted();

	public void schedulerStarting();

	public void schedulerShutdown();

	public void schedulerShuttingdown();

	public void schedulingDataCleared();

}

```

1. public void jobScheduled(Trigger trigger);
   
    * 调度程序在调度JobDetailis时调用；
    
2. public void jobUnscheduled(TriggerKey triggerKey) ;

    * 当JobDetailis未调度时，由调度程序调用。

3. public void triggerFinalized(Trigger trigger) ;

    * 当触发器达到不再触发的状态时，由调度程序调用。除非这个Job已经设置为持久性，否则他就会从Scheduler中移除

4. public void triggerPaused(TriggerKey triggerKey) ;

    * 当触发器暂停时，由调度程序调用。

    如果是Trigger组是，triggerName = null

5. public void triggersPaused(String triggerGroup) ;

    * 当一组触发器暂停时，由调度程序调用。

    * 如果所有组都暂停了，triggerGroup将为空

6. public void triggerResumed(TriggerKey triggerKey) ;

    * 当取消暂停触发器时，由调度程序调用。

7.  public void triggersResumed(String triggerGroup) ;

    * 当取消暂停一组触发器时，由调度程序调用。

8. public void jobAdded(JobDetail jobDetail) ;

    * 当JobDetailhas被添加时，调度程序调用。

9. public void jobDeleted(JobKey jobKey) ;

    * 当JobDetailhas被删除时，由调度程序调用。

10. public void jobPaused(JobKey jobKey);

    * 当JobDetailhas被暂停时，由调度程序调用。

11. public void jobsPaused(String jobGroup) ;

     * 当暂停了一组JobDetails时，由调度程序调用。

12. public void jobResumed(JobKey jobKey) ;

     * 当JobDetailhas从暂停到恢复时，由调度程序调用。

13. public void jobsResumed(String jobGroup) ;

     * 当一组JobDetailhas从暂停到恢复时，由调度程序调用

14. public void schedulerError(String msg, SchedulerException cause) ;

     * 当调度程序中发生严重错误时由调度程序调用-例如JobStore中的重复失败，或者在触发触发器时无法实例化作业实例。

     * 给定schedulerException的getErrorCode()方法可用于确定有关遇到的错误类型的更具体信息。

15. public void schedulerInStandbyMode() ;

     * 由调度程序调用，通知侦听器它已移动到待机模式。

16. public void schedulerStarted() ;

     * 由调度程序调用以通知侦听器它已启动。

17. public void schedulerStarting() ;

     * 由调度程序调用以通知侦听器它正在启动。

18. public void schedulerShutdown() ;

     * 由调度程序调用，以通知侦听器它已关闭。

19. public void schedulerShuttingdown() ;

     * 由调度程序调用，通知侦听器它已开始关闭序列。

20. public void schedulingDataCleared();

     * 由调度程序调用，通知侦听器删除了所有作业、触发器和日历。

**例子**

1. SchedulerListener

```java
package JobListener;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public class MySchedulerListener implements SchedulerListener {

	public void jobScheduled(Trigger trigger) {
		System.out.println("Job部署完成");
	}

	public void jobUnscheduled(TriggerKey triggerKey) {
		System.out.println("Job完成卸载");
	}

	public void triggerFinalized(Trigger trigger) {
		System.out.println("Trigger移除");
	}

	public void triggerPaused(TriggerKey triggerKey) {
		System.out.println("Trigger正在暂停");
	}

	public void triggersPaused(String triggerGroup) {
		System.out.println("Trigger组正在暂停");
	}

	public void triggerResumed(TriggerKey triggerKey) {
		System.out.println("Trigger从暂停中恢复");
	}

	public void triggersResumed(String triggerGroup) {
		System.out.println("Trigger组从暂停中恢复");
	}

	public void jobAdded(JobDetail jobDetail) {
		System.out.println("添加一个Job");
	}

	public void jobDeleted(JobKey jobKey) {
		System.out.println("删除一个Job");
	}

	public void jobPaused(JobKey jobKey) {
		System.out.println("Job正在被暂停");
	}

	public void jobsPaused(String jobGroup) {
		System.out.println("Job组正在被暂停");
	}

	public void jobResumed(JobKey jobKey) {
		System.out.println("Job正在被恢复");
	}

	public void jobsResumed(String jobGroup) {
		System.out.println("Job组正在被恢复");
	}

	public void schedulerError(String msg, SchedulerException cause) {
		System.out.println("Scheduler产生严重错误");
	}

	public void schedulerInStandbyMode() {
		System.out.println("Scheduler挂起");
	}

	public void schedulerStarted() {
		System.out.println("Scheduler开启");
	}

	public void schedulerStarting() {
		System.out.println("Scheduler正在开启");
	}

	public void schedulerShutdown() {
		System.out.println("Scheduler关闭");
	}

	public void schedulerShuttingdown() {
		System.out.println("Scheduler正在关闭");
	}

	public void schedulingDataCleared() {
		System.out.println("Scheduler数据被清除");
	}

}

```

2. 执行

```java
// 任务类
JobDetail jobDetail = JobBuilder.newJob(MyJob.class)// 加载任务类
    .withIdentity("myJob")// 任务名称
    .usingJobData("msg", "我是任务类")// 传递参数
    .usingJobData("count", 0)// 传递参数
    .build();
SimpleScheduleBuilder.simpleSchedule();
// 触发器
SimpleTrigger trigger = TriggerBuilder.newTrigger()
    .withIdentity("myTrigger")// 触发器名
    .startNow()// 马上启动触发器
    .withSchedule(SimpleScheduleBuilder
						.repeatSecondlyForever(5)// SimpleTrigger5秒一次
						.withRepeatCount(3)// 执行3次
				).usingJobData("msg", "我是触发器")// 传递参数
				.build();
// 调度器
Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
// 调度器监听
scheduler.getListenerManager().addSchedulerListener(
    new MySchedulerListener());
scheduler.scheduleJob(jobDetail, trigger);
// 开启
scheduler.start();
// 关闭
Thread.sleep(7000l);
scheduler.shutdown();
```



}