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



}
