

```java
// 创建工作
JobDetail jobDetail = JobBuilder.newJob(MyJob.class)// 指定Job
    .withIdentity("myJob1", "group1")// 命名分组
    .build();// 创建
// 创建触发器
Trigger simpleTrigger = TriggerBuilder.newTrigger()
    .withIdentity("myTigger", "group1")//命名分组
    .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(5))//创建一个简单的5秒的触发器
    .build();//创建触发器
// 创建调度器
Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
// 开始
scheduler.start();
// 告诉quartz使用我们的触发器来安排工作
scheduler.scheduleJob(jobDetail, simpleTrigger);
```

