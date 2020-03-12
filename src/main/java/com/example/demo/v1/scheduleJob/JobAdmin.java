package com.example.demo.v1.scheduleJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobAdmin {

    private static Logger logger = LoggerFactory.getLogger(JobAdmin.class);

    @Autowired
   private  JobTimeConfig jobTimeConfig;

    @Scheduled(cron = "${admin.job.corn}")
    public void handleAllJob(){
        try {
            logger.warn("JobAdmin handleAllJob start");
            jobTimeConfig.findAllClassByInterface(BaseJob.class);
        }catch (Exception e){
            logger.error("JobAdmin handleAllJob error",e);
        }
    }
}
