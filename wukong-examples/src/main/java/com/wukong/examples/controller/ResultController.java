package com.wukong.examples.controller;


import com.wukong.examples.entity.City;
import org.hibernate.validator.constraints.Length;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;

@RestController
@RequestMapping("/result")
@Validated
public class ResultController {



    @RequestMapping("/success")
    public City success() {
        City city = new City(1,"city1","001");
        return city;
    }


    @RequestMapping("/fail")
    public String fail(@RequestParam @Length(min = 6,max = 50) String name
                ,@RequestParam @Email  String email
                ,@RequestParam  String cellPhone) {
        return name+"ok";
    }

}
