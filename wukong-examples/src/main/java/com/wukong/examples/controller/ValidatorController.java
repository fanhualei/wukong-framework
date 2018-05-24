package com.wukong.examples.controller;



import com.wukong.examples.entity.City;
import com.wukong.examples.entity.Method;
import com.wukong.examples.entity.User;
import com.wukong.examples.validator.Phone;
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

    @RequestMapping("/bean0")
    public City bean0(City city) {
        return bean(city);
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
    public City bean3(@RequestBody @Valid City city) {
        return bean(city);
    }

    private City bean(City city){
        city.setCode(city.getCode()+"ok");
        return city;
    }



//bean类，使用到Bindingresult，需要使用@Validated注解类参数
// （注意到@Validated不能用在成员属性上，
// 所以使用bindingresult的时候，不能单单检测字段，而要用类包装字段）
// 所以method1的使用方法同method2
//    @RequestMapping("/method1")
//    public String method1(@RequestParam @Valid @Length(min = 6,max = 50) String name
//            ,@RequestParam  @Valid @Email String email
//            ,@RequestParam  String cellPhone
//            ,BindingResult result
//    ) {
//        if(result.hasErrors()) {
//            List<ObjectError> list = result.getAllErrors();
//            for (ObjectError error : list) {
//                System.out.println(error.getCode() + "---" + error.getArguments() + "---" + error.getDefaultMessage());
//            }
//        }
//        return name+"ok";
//    }

    @RequestMapping("/method1")
    public String method1(@Validated Method method
            ,BindingResult result
    ) {
        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getCode() + "---" + error.getArguments() + "---" + error.getDefaultMessage());
            }
        }
        return method.toString()+": ok";
    }

    @RequestMapping("/method2")
    public Object method2(@RequestBody @Validated  City city
            ,BindingResult result
    ) {

        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getCode() + "---" + error.getArguments() + "---" + error.getDefaultMessage());
            }
//            return result;
        }
        return city.getName()+"ok";
    }

    @RequestMapping("/method3")
    public Object method3(@RequestBody @Valid  City city
            ,BindingResult result
    ) {

        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getCode() + "---" + error.getArguments() + "---" + error.getDefaultMessage());
            }
//            return result;
        }
        return city.getName()+"ok";
    }

    @RequestMapping("/phone")
    public String phone1(@RequestBody @Valid User user) {
        return user.getPhone();
    }


    @RequestMapping("/phone2")
    public String phone2(@RequestParam @Valid @Phone String phone) {
        return phone;
    }
}
