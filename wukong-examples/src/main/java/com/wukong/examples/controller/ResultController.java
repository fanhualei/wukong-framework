package com.wukong.examples.controller;


import com.wukong.core.exceptions.UserNotLoginException;
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
import java.util.*;

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
     * 测试返回类型
     */

    @RequestMapping("/type1")
    public Object type1(@RequestParam Integer code)
    {
        return type(code);
    }

    @RequestMapping("/type2")
    @ResponseResult
    public Object type2(@RequestParam Integer code)
    {
        return type(code);
    }

    private Object type(Integer code){
        if(code==1){
            return new String("测试返回的函数");
        }else if(code==2) {
            return 1;
        }else if(code==3){
            return null;
        }else if(code==4){
            Map<String,String> ren=new HashMap<>();
            ren.put("key1","value1");
            ren.put("key2","value2");
            return ren;
        }else if(code==5){
            List<String> list = new ArrayList<>();
            list.add("user1");
            list.add("user2");
            return list;
        }else if(code==6){
            return new Date();
        }else if(code==7){
            return new Exception("dddd");
        }
        return null;
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
        if(code==1){
            throw new RuntimeException("my throws error");
        }else if(code==2){
            throw new NullPointerException();
        }else if(code==3){
            throw new NumberFormatException();
        }else if(code==4){
            throw new UserNotLoginException();
        }
        return "ok";
    }





}
