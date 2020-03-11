package com.example.demo.v1.scheduleJob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobAdmin {

    @Autowired
   private  JobTimeConfig jobTimeConfig;

    @Scheduled(cron = "0/30 * * * * ? ")
    public void handleAllJob(){
        try {
            jobTimeConfig.findAllClassByInterface(BaseJob.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
