package com.example.demo.v1.scheduleJob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.LinkedList;

@Component
public class JobTimeConfig {

    @Autowired
    private ApplicationContext applicationContext;

    public void findAllClassByInterface(Class c){
        Collection<BaseJob> jobList=new LinkedList<>(applicationContext.getBeansOfType(BaseJob.class).values());
        if(!CollectionUtils.isEmpty(jobList)){
            jobList.forEach(j-> System.out.println(j.getClass().getName()));
        }

    }

}
