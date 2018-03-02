package com.wukong.generator.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceConfig {

    //ignores = ignores tables ,example: user,group,order
    //runWukongPlug =true  generate service and get table from auto rule

    String prefix;
    String ignores;
    String targetPackage;
    String targetProject;

    boolean runWukongRule;

    //context的属性，通过这个来判断生成service的属性if
    String targetRuntime;


    public boolean isDynamicService(){
        if(targetRuntime!=null
                &&targetRuntime.equalsIgnoreCase("MyBatis3DynamicSql")){
            return true;
        }
        return false;
    }



}
