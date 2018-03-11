package com.wukong.examples.controller;


import com.wukong.core.result.ResponseResult;
import com.wukong.examples.entity.City;


import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;

@RestController
@RequestMapping("/result")
@Validated
public class ResultController {

    /**
     * 一个对象的自动包裹
     * http://localhost:8080/result/success
     */

    @RequestMapping("/success1")
    public City success1() {
        return success();
    }

    @RequestMapping("/success2")
    @ResponseResult
    public City success2() {
        return success();
    }

    private City success(){
        City city = new City(1,"city1","001");
        return city;
    }

    /**
     * 测试参数异常
     */
    @RequestMapping("/para1")
    public String para1(@RequestParam @Valid @Length(min = 6,max = 50)  String name
            ,@RequestParam @Valid @Email String email
            ,@RequestParam String cellPhone
    ) {
        return "name:"+name+";"+"email:"+email+";"+"cellPhone:"+cellPhone+";";
    }

    @RequestMapping("/para2")
    @ResponseResult
    public String para2(@RequestParam @Valid @Length(min = 6,max = 50) String name
            ,@RequestParam  @Valid @Email String email
            ,@RequestParam  String cellPhone
    ) {
        return "name:"+name+";"+"email:"+email+";"+"cellPhone:"+cellPhone+";";
    }




    /**
     * 异常的自动包裹
     * http://localhost:8080/result/fail?code=1
     */
    @RequestMapping("/exception1")
    public String exception1(Integer code) {
        return exception(code);
    }

    @RequestMapping("/exception2")
    @ResponseResult
    public String exception2(Integer code) {
        return exception(code);
    }
    //通过不同的code 得到错误信息
    private String exception(Integer code){
        if(code!=null & code!=0){
            throw new RuntimeException("my throws error");
        }
        return "ok";
    }





}
