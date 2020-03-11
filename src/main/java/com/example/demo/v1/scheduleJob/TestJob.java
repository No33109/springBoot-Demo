package com.example.demo.v1.scheduleJob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestJob implements BaseJob {

    @Autowired
    private JobTimeConfig jobTimeConfig;


    @Override
    @Scheduled(cron = "${com.example.demo.v1.scheduleJob.TestJob}")
    public void process() {
        System.out.println("定时----");
        jobTimeConfig.findAllClassByInterface(BaseJob.class);
    }
}
