package com.damon.scheduler;

import com.damon.multiThreadsProcess.*;
import com.damon.threadJob.*;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@Component
@DisallowConcurrentExecution
public class StartThredsJob {
    private int i;

    @Autowired
    AppMultiProcess appMultiProcess;
    @Autowired
    SzbMultiProcess szbMultiProcess;
    @Autowired
    WebsiteMultiProcess websiteMultiProcess;
    @Autowired
    WeixinMultiProcess weixinMultiProcess;
    @Autowired
    WeiboMultiProcess weiboMultiProcess;
    @Autowired
    SavePicToMysql savePicToMysql;
    @Autowired
    SavePicMultiProcess savePicMultiProcess;
    @Autowired
    HttpMultiProcess httpMultiProcess;

    @Autowired
    RestTemplate restTemplate;
    public static final int forTime = 2;

    private static Executor executor = Executors.newFixedThreadPool(forTime);

    public void startWithThread(){
        /**
         * 任务的起始点是这里
         * 先说一下任务目的和思路：
         * 修复N张表中的数据。首先可以肯定这是一个定时任务。
         * 然后通过N条线程去获取数据，每条线程都获取了很多数据，
         * 接下去的解释只针对单条线程的数据 -> 通过多线程去处理这些数据，怎么确定需要多少线程呢？
         * -> 根据数据量和一个固定的基数，就可以算出需要M条线程去处理这些数据
         * -> 每条线程处理完数据，然后更新数据。因此处理和更新是同一条线程处理的。
         *
         * English version:
         * The entrance of task is here,so let me tell you the thought of mine:
         * The mission is to modify and update data for multiple tables,so it must be schedule task.
         * This class will generate X threads,each is will fetch lots of data from each table,
         * the following explanation is based on one thread -> after data fetched,the data should be processed
         * by multiple threads,but how to calculate the amount of thread?
         * -> We need 2 figures,one is the amount of data,the other is a constant number,then we will
         * know we need X threads to process the data,and also update data.
         */

        /**
         * 再说代码的实现
         * CountDownLatch类有阻塞的作用,需要两个实例，一个startLatch，一个endLatch
         * 使用线程池管理线程，endLatch构造器传参int，
         * startLatch是总阀门，它一声令下，任务才可以执行，可以看MyThread类中的注释，
         * 而endLatch，它是控制收尾工作的，只有所有线程都运行结束，那么主线程才会接着向下执行
         *
         * English version:
         * Some explanation about code:
         * The function of CountDownLatch is blocking thread,and we need two of them, a startLatch,an endLatch.
         * The therad pool will manage threads,and a int should be passed into constructor of CountDownLatch.
         * startLatch is the main valve,after signal sent by it,those threads will starting running,please read
         * the commentary of inner class MyThread.
         * While endLatch is blocking the main thread,after all the threads have done their jobs,the main thread
         * then will keep running.
         */
        CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(forTime);
        for (i = 0; i < forTime; i++) {
            Thread thread = new Thread(new MyThread(i,startLatch,endLatch));
            executor.execute(thread);
        }
        startLatch.countDown();//发出信号，可以执行上面N个线程的任务 send signal,threads in the for loop can run.
        try {
            //此时在执行N个线程任务，下面的await等待所有任务执行完毕，主线程才会往下执行，输出日志log.info("Job done");
            //those threads in the for loop are runnning,but the main thread cannot keep running,
            // after sub threads have done their jobs,the main thread will run.
            endLatch.await();
           /* Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
//                    weiboMultiProcess.MultiProcess();
//                    savePicToMysql.savePictureToMysqlAndupdateMlfFormalAndOriginal(savePicToMysql.fetchOrigianlData());
//                    savePicMultiProcess.MultiProcess();
//                    httpMultiProcess.MultiProcess();
                }
            });
            thread.run();*/
            log.info("Job done");
        } catch (Exception e) {
            log.error(String.format("thread running error,msg:[%s]",e));
        }
    }

    class MyThread implements Runnable {
        int i;
        CountDownLatch startLatch;
        CountDownLatch latch;

        public MyThread(int i,CountDownLatch startLatch,CountDownLatch latch){
            this.i = i;
            this.startLatch = startLatch;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                if (i == 0){
                    try {
                        //startLatch是控制总阀，weiboMultiProcess任务现在待命，但是不能执行
                        //startLatch is main valve,weiboMultiProcess is ready,but it cannot be executed
                        startLatch.await();
                        weiboMultiProcess.MultiProcess();
                    } catch (Exception e) {
                        log.error(String.format("weibo thread error,msg:[%s]",e));
                    }
                } else if (i == 1){
                    try {
                        startLatch.await();
                        appMultiProcess.MultiProcess();
                    } catch (InterruptedException e) {
                        log.error(String.format("app thread error,msg:[%s]",e));
                    }
                }
            }catch (Exception e){
                log.error(String.format("thread error,msg:[%s]",e));
            } finally {
                //注意这里一定要写在finally中
                //it must be written here
                latch.countDown();
            }

        }
    }
}
