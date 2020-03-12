package com.example.demo.v1.scheduleJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestJob implements BaseJob {

    private static Logger logger = LoggerFactory.getLogger(TestJob.class);

    @Autowired
    private JobTimeConfig jobTimeConfig;


    @Override
    @Scheduled(cron = "${com.example.demo.v1.scheduleJob.TestJob}")
    public void process() {
        logger.warn("TestJob process start");
//        System.out.println("定时----");
//        jobTimeConfig.findAllClassByInterface(BaseJob.class);
    }
}
