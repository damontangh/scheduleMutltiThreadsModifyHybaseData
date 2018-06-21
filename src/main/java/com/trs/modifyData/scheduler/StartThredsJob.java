package com.trs.modifyData.scheduler;

import com.trs.modifyData.multiThreadsProcess.*;
import com.trs.modifyData.threadJob.*;
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
    public static final int forTime = 4;
    /**
     * 给app,szb,weixin,weibo添加hybase_id，
     * 2017.12.06只有weibo还未修复完成，只需要一个主线程就行
     */
    private static Executor executor = Executors.newFixedThreadPool(forTime);
    public void startWithThread(){
        CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(forTime);

        for (i = 0; i < forTime; i++) {
            Thread thread = new Thread(new MyThread(i,startLatch,endLatch));
            executor.execute(thread);//也OK
        }
        startLatch.countDown();//发出信号，可以执行上面N个线程的任务
        //此时在执行N个线程任务，下面的await等待所有任务执行完毕
        try {
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

                switch (i){
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        startLatch.await();
                        restTemplate.postForObject("http://127.0.0.1:6081/zyzx/fetch/rescentData?channelType=wx&pageNum=1&ids=linankepu&startTime=2015/02/10 14:30:47&endTime=2017/02/10 14:30:47",null,String.class);
                        restTemplate.postForObject("http://127.0.0.1:6081/zyzx/fetch/weixinInfoById?weixinId=cicaf2012",null,String.class);
                        restTemplate.postForObject("http://127.0.0.1:6081/zyzx/search/rescentData?zbSourceSite=今日头条&pageNum=1&pageSize=10&channelType=wz&returnCols=DOCCHANNEL;BC;DOCPUBTIME;TXS;BM;IR_ABSTRACT;ZB_KEYWORDS5_CHAR",null,String.class);
                        restTemplate.postForObject("http://127.0.0.1:6081/zyzx/search/detailData?guid=973373589095272448&mlfDocid=6784353&dbName=mlf_product_formal&returnCols=DOCCHANNEL;BC;DOCPUBTIME;TXS;BM;IR_ABSTRACT;ZB_KEYWORDS5_CHAR",null,String.class);
                }
               /* if (i == 0){
                    try {
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
                }*/
            }catch (Exception e){
                log.error(String.format("thread error,msg:[%s]",e));
            } finally {
                latch.countDown();
            }

        }
    }
}
