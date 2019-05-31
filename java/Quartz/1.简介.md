---
typora-root-url: ./

---

# 1. 概念

Quartz是OpenSymphony开原组织在Job 刹车都灵领域又一个开源项目,它可以与j2ee与j2se应用程序结合使用也可以单独使用.

quartz是开源且具有丰富特性的"任务调度库",能够集成于各种java应用,小到独立的应用,大至电子商与系统.quartz能够创建亦简单亦复杂的的调度,以执行上十 上百 甚至上万的任务. 任务job被定义为标准的java组价,能够执行你想要实现的功能. quartz调度框架包含很多企业级特性,如JTA事务 集群的支持.

简而言之,quartz就是基于java实现的任务调度框架,用于执行你想要的任何任务

# 2. Quartz运行环境

- Quartz可以运行前任在另一个独立式应用程序中.
- Quartz可以在应用程序服务器(或servlet容器中)内被实例化,并且参与事务.
- Quartz可以作为一个独立的程序运行(其自己的java虚拟机内),可以通过RMI使用.
- Quartz可以被实例化,作为独立的项目集群(负载平衡和故障转移功能),用于作业执行.

# 3. Quartz设计模式

- Builder模式
- Factory模式
- 组件模式
- 链式编程

# 4. Quartz学习核心概念

- 任务 `Job`

  `Job`就是你想要实现的任务类,每个`Job`都必须实现`org.quartz.Job`接口,并且只需实现`execute()`方法 

- 触发器`Trigger`

  `Trigger`为你执行任务的触发器,比如你想每天定时3点发送一份统计邮件,Trigger将会设置3点执行该任务.

  `Trigger`主要包含两种:

  - `SimpleTrigger` : 定时触发器
  - `CronTigger` : 日历触发器

- 调度器`Scheduler`

  `Scheduler`为任务的调度器,它会将任务`Job`以及触发器`Trigger`整合起来,负责基于`Trigger`设定的时间来执行`Job`

# 5. 体系结构

![](D:/3.IT/%E7%AC%94%E8%AE%B0/java/Quartz/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6(3).png)

# 6. Quartz 常用API

一下是Quartz常用的几个重要接口,也是Quartz的重要组件

1. `Scheduler`用于与调度程序交互的主程序接口

   `Scheduler`调度程序-任务执行计划表,只有安排进执行计划的任务`job`(通过scheduler.schedulejob方法安排进行执行计划),当它预先定义的执行时间到了的时候(任务触发器trigger),该任务才会执行

2. `Job`我们预先定义在未来需要被调度的执行程序的任务类.

3. `JobDetail`使用jobdetalil来定义定时任务的实例,jobdetail实力是通过`JobBuilder`类创建的.

4. `JobDataMap`可以包含不限量的(序列化的)数据对象,在Job实例执行的时候,可以使用其中的数据;`JobDataMap`是一个Map的实现,额外增加了一些标语存取的基本数据类型的方法

5. `Trigger`触发器,Trigger对象是用来触发执行Job的.当调度一个Job时,我们实例一个触发器后调整它的属性来满足job的执行条件.表明任务在什么时候会执行.定义了一个已经被安排的任务将会在什么时候执行的时间条件.

6. `JobBuilder`用于声明一个任务实例,也可以定义关于该任务的详情,比如任务名 组名等,这个生命的实例将会作为一个实际执行的任务.

7. `TriggerBuilder`触发器创建,用于创建触发器trigger实例

8. `JobListener` `Triggerlistener` `SchedulerListener`监听器,用于对组件的监听

