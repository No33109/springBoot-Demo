package com.example.demo.demo.v1.dao;

import com.example.demo.DemoApplicationTests;
import com.example.demo.v1.dao.CenterDao;
import org.junit.Test;

import javax.annotation.Resource;

public class CenterDaoTest extends DemoApplicationTests {

    @Resource
    private CenterDao centerDao;

    @Test
    public void find(){
        System.out.println(centerDao.findAll());
    }
}
