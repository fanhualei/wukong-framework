package com.wukong.examples.controller;


import com.wukong.core.result.ResponseResult;
import com.wukong.examples.entity.City;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/result")
public class ResultController {




    /**
     * 一个对象的自动包裹
     * http://localhost:8080/result/success
     */

    @RequestMapping("/success1")
    public City success1() {
        City city = new City(1,"city1","001");
        return city;
    }


    @RequestMapping("/success2")
    @ResponseResult
    public City success2() {
        City city = new City(1,"city1","001");
        return city;
    }


    /**
     * 异常的自动包裹
     * http://localhost:8080/result/fail?code=1
     */
    @RequestMapping("/fail1")
    public String fail1(Integer code) {

        if(code!=null & code!=0){
            throw new RuntimeException("my throws error");
        }
        return "ok";
    }

    @RequestMapping("/fail2")
    @ResponseResult
    public String fail2(Integer code) {

        if(code!=null & code!=0){
            throw new RuntimeException("my throws error");
        }
        return "ok";
    }

}
