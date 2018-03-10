package com.wukong.examples.controller;


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
@RequestMapping("/validator")
@Validated
public class ValidatorController {
    @RequestMapping("/fail")
    public String fail(@RequestParam @Valid @Length(min = 6,max = 50) String name
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

}
