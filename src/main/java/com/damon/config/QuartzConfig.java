package com.damon.config;

import com.damon.scheduler.StartThredsJob;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Value(value = "${cron.expression}")
    private String cron;

    @Value(value = "${target.method}")
    private String method;

    @Bean (name = "jobDetailFactoryBean")
    public MethodInvokingJobDetailFactoryBean jobDetailFactoryBean(StartThredsJob job){
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        //这里需要注意，下面的setTargetClass方法要求执行的方法一定是static的
//        factoryBean.setTargetClass(helloJob.getClass());
        //但是使用下面这个方法就不要求一定是static方法
        factoryBean.setTargetObject(job);
        factoryBean.setTargetMethod(method);
        factoryBean.setConcurrent(false);
        return factoryBean;
    }

    @Bean (name = "crontriggerBean")
    @Qualifier("jobDetailFactoryBean")
    public CronTriggerFactoryBean crontriggerBean(MethodInvokingJobDetailFactoryBean jobDetailFactoryBean){
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetailFactoryBean.getObject());
        trigger.setCronExpression(cron);
        return trigger;
    }

    @Bean (name = "schedulerBean")
    @Qualifier ("crontriggerBean")
    public SchedulerFactoryBean schedulerBean(CronTriggerFactoryBean crontriggerBean){
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(crontriggerBean.getObject());
        return scheduler;
    }

}
