package com.example.demo.v1.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/test")
@Api(tags = "demo")
public class DemoController {


    @GetMapping("/a")
    @ApiOperation(value = "test swagger")
    public String testDemo(){
        return "hello world";
    }
}
