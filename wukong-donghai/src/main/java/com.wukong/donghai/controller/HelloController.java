package com.wukong.donghai.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 用来测试的类
* @author fanhl
*/
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping()
    public String hello() {
        return "Hello World 你好";
    }



}
