# scheduleMutltiThreadsModifyHybaseData

#### 项目介绍
配置文件已置空，包含隐私。
写这工程的时候用的是jdk1.7，发布的时候我jdk改为1.8，这工程实战了多次，执行过不同的任务，
因此代码可能多且混乱，不必查看所有代码，请看软件架构。
基于springboot集成Quartz的定时任务，采用多线程，批量修复数据的项目。
需要先配置Quartz，在config包下，需要配置定时周期，和需要执行的方法。
任务的入口在scheduler包下的StartThredsJob类，执行startWithThread()方法，
方法体有注释

English version:
application.properties is blank because it includes private information.
I was using jdk7 while making this project,but the jdk version was replaced by 8 when I released this project.
And this project actually has done several major tasks,so maybe there are redundant code in it,
it is unnecessary that you check all the code,please read Architecture underneath.
This is a schedule task based on Springboot with Quartz, and running with multiple threads,and it's mission 
is to update data for multiple tables.
The priority is to configure Quartz,please check package config,which inclues a class for configuring 
timing cycle and the method that should be invoked.
The entrance of the task is in the class StartThreadsJob,which is in the package schedule,and the method 
startWithThread() will be executed,there is commentary in the body of the method.

#### 软件架构 Architecture
软件架构说明
scheduler下的类开启多线程任务，并发去获取多张表的数据，
每张表的数据，再采用多条线程进行处理，处理结束后更新数据，待所有线程执行结束，
才算一次任务的结束，然后等待定时调度。

StartThredsJob开启任务，然后是那些MultiProcess类执行任务，一条线程负责一个类，
每条线程会去获取数据，然后根据每条线程获取的数据量计算出需要多少子线程来处理，
处理结束还是由子线程负责更新。

English version:
The class in package schedule will start a multi-thread task, and it will fetch data from multiple tables 
concurrently, then these data will be processed by multiple threads,after that,data will be updated.
After each thread have done its own job, that is one round over, and wait for schedule for next round.

StartThredsJob will start task,and those classes as MultiProcess actually do the real job, each thread in class
StartThredsJob manages each MultiProcess, it will fetch data from one table, then calculate the amount of thread
so as to process the data, then each thread will process data and update data.


#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明

1. xxxx
2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [http://git.mydoc.io/](http://git.mydoc.io/)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)