package com.wukong.examples.controller;


import com.wukong.core.result.ResponseResult;
import com.wukong.examples.entity.City;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/validator")
@Validated
public class ValidatorController {

    @RequestMapping("/para1")
    public Map para1(@RequestParam @Valid @Length(min = 6,max = 50)  String name
            ,@RequestParam @Valid @Email String email
            ,@RequestParam String cellPhone
    ) {

        return para(name,email,cellPhone);
    }

    @RequestMapping("/para2")
    @ResponseResult
    public Map para2(@RequestParam @Valid @Length(min = 6,max = 50) String name
            ,@RequestParam  @Valid @Email String email
            ,@RequestParam  String cellPhone
    ) {
        return para(name,email,cellPhone);
    }

    private Map para(String name,String email,String cellPhone){
        String str= "name:"+name+";"+"email:"+email+";"+"cellPhone:"+cellPhone+";";
        return new HashMap<String,String>(){{
            put("para", str);
        }};
    }


    @RequestMapping("/bean1")
    public City bean1(@RequestBody City city) {
        return bean(city);
    }

    @RequestMapping("/bean2")
    public City bean2(@RequestBody @Valid City city) {
        return bean(city);
    }

    @RequestMapping("/bean3")
    @ResponseResult
    public City bean3(@RequestBody @Valid City city) {
        return bean(city);
    }

    private City bean(City city){
        city.setCode(city.getCode()+"ok");
        return city;
    }




    @RequestMapping("/method1")
    public String method1(@RequestParam @Valid @Length(min = 6,max = 50) String name
            ,@RequestParam  @Valid @Email String email
            ,@RequestParam  String cellPhone
            ,BindingResult result
    ) {

        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getCode() + "---" + error.getArguments() + "---" + error.getDefaultMessage());
            }
        }
        return name+"ok";
    }

    @RequestMapping("/method2")
    public String method2(@RequestParam @Valid City city
            ,BindingResult result
    ) {

        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getCode() + "---" + error.getArguments() + "---" + error.getDefaultMessage());
            }
        }
        return city.getName()+"ok";
    }

}
