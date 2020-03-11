package com.example.demo.v1.scheduleJob;

import com.example.demo.v1.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestJob implements BaseJob {

    @Autowired
    private CenterService centerService;

    @Override
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void process() {
        System.out.println("定时----");
    }
}
