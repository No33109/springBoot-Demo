package com.example.demo.v1.scheduleJob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedList;

@Component
public class JobTimeConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Environment environment;


    public void findAllClassByInterface(Class c){
        Collection<BaseJob> jobList=new LinkedList<>(applicationContext.getBeansOfType(c).values());
        if(!CollectionUtils.isEmpty(jobList)){
            jobList.forEach(j-> {
                String property = environment.getProperty(j.getClass().getName());
                if(!StringUtils.isEmpty(property)){
                    System.out.println("class:"+j.getClass().getName()+"  corn:"+property);
                }
            });
        }

    }

}
