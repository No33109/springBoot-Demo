package com.example.demo.v1.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/test")
@Api(tags = "demo")
public class DemoController {


    @RequestMapping("/a")
    @ApiOperation(value = "test swagger",httpMethod = "GET")
    public String testDemo(){
        return "hello world";
    }



    public static void main(String[] args) {
        List<String> a = null;

        for(String s:a){
            if(s != null){
                System.out.println(a);
            }
        }
    }
}

