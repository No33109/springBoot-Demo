package com.example.demo.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1")
@RestController
public class DefaultController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello SpringSecurity!";
    }
}
