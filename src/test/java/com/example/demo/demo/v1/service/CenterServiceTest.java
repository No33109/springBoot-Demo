package com.example.demo.demo.v1.service;

import com.example.demo.DemoApplicationTests;
import com.example.demo.v1.service.CenterService;
import org.junit.Test;

import javax.annotation.Resource;

public class CenterServiceTest extends DemoApplicationTests {

    @Resource
    private CenterService centerService;

    @Test
    public void save(){
        for (int i = 0; i <10 ; i++) {
            centerService.save();
        }
    }

    @Test
    public void findAll(){
        System.out.println(centerService.findAll());
    }
}
