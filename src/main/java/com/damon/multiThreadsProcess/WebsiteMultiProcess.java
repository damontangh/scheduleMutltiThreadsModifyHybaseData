package com.damon.multiThreadsProcess;

import com.trs.hybase.client.TRSRecord;
import com.damon.threadJob.WebsiteAddHybaseid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class WebsiteMultiProcess {

    @Autowired
    WebsiteAddHybaseid websiteAddHybaseid;

    //分片基数，获取的数量/size=分片数量
    @Value(value = "${sharding.each.size}")
    private Integer shardingSize;

    //这是分片数量
    private Integer shardingAmount;

    //这是获取到list的size
    private Integer listTotalSize;

    public void MultiProcess(){
        List<TRSRecord> records = websiteAddHybaseid.fetchData();
        if (records != null && shardingSize != null){
            listTotalSize = records.size();
            //如果list的size大于分片基数，才执行下面步骤
            //例如分片基数100，而list.size才90，那么就只要1片就行了
            if (listTotalSize == 0){
                return;
            }
            if (listTotalSize > shardingSize){
                double remainder = listTotalSize % shardingSize;
                int quotient = listTotalSize / shardingSize;
                shardingAmount = (remainder > 0 ? quotient + 1:quotient);
            } else {
                shardingAmount = 1;
            }

            /**
             * 如果分片数量大于1，那就使用多线程，
             * 如果只有1片，那就不用多线程
             */
            if (shardingAmount == 1){
                websiteAddHybaseid.updateWebsiteTrsData(websiteAddHybaseid.processData(records));
            } else {
                CountDownLatch startCount = new CountDownLatch(1);
                CountDownLatch endCount = new CountDownLatch(shardingAmount);
                int end;
                for (int i = 1; i <= shardingAmount; i++) {
                    int start = shardingSize*(i-1);
                    //最后一片要特殊处理,例如一共503，下标是0-502，最后一片的下标是500-503,因为subList的endIndex获取不到的
                    //第1片：0-100，实际获取0-99下标，一共100条数据
                    if (i == shardingAmount){
                        end = listTotalSize;
                    } else {
                        end = shardingSize*i;
                    }
                    List<TRSRecord> partList = records.subList(start,end);
                    Thread thread = new Thread(new MultiThread(partList,startCount,endCount));
                    Executors.newCachedThreadPool().execute(thread);
                }
                //这里发出指令，线程可以执行
                startCount.countDown();
                //这里等待所有线程执行结束
                try {
                    endCount.await();
                } catch (InterruptedException e) {
                    log.error(String.format("endCount.await error,msg:[%s]",e));
                }
            }

        }


    }


    private class MultiThread implements Runnable{
        private List<TRSRecord> recordsInner;
        private CountDownLatch startCountInner;
        private CountDownLatch endCountInner;
        //每一个线程负责总list中的一部分，因此传给它的list，就已经是部分list
        public MultiThread(List<TRSRecord> records,CountDownLatch startCount,CountDownLatch endCount){
            this.recordsInner = records;
            this.startCountInner = startCount;
            this.endCountInner = endCount;
        }
        @Override
        public void run() {
            try {
                startCountInner.await();
                websiteAddHybaseid.updateWebsiteTrsData(websiteAddHybaseid.processData(recordsInner));
            }catch (Exception e){
                log.error(String.format("multiProcess error",e));
            }finally {
                endCountInner.countDown();
            }
        }
    }

}
