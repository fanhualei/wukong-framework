package com.wukong.examples.controller;


import com.wukong.examples.entity.City;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/result")
public class ResultController {


    /**
     * 一个对象的自动包裹
     */

    @RequestMapping("/success")
    public City success() {
        City city = new City(1,"city1","001");
        return city;
    }

    /**
     * 异常的自动包裹
     */
    @RequestMapping("/fail")
    public String fail(Integer code) {

        if(code!=0){
            throw new RuntimeException("故意抛出的错误");
        }
        return "ok";
    }

}
