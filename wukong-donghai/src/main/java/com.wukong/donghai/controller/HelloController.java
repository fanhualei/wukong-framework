package com.wukong.donghai.controller;


import com.wukong.core.result.SingleResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping()
    public String hello() {
        return "Hello World ";
    }



}
