package com.wukong.examples.controller;


import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;


@RestController  //等同于类上加@Controller 和 方法上加@RequestBody
@RequestMapping("/hello")  //映射地址：映射一级路径
public class HelloController  {

    /**
     * 默认映射，可不填二级
     * <p>
     * 地址：http://localhost:8080/hello
     * 显示：Hello Spring-Boot
     */
    @RequestMapping
    public String hello() {
        return "Hello World";
    }

    /**
     * 带参数
     * Json显示-返回Map格式
     * <p>
     * 地址：http://localhost:8080/hello/info?name=abc
     * 显示：{"name":"张三"}
     */
    @RequestMapping("/info")
    public Map<String, String> getInfo(@RequestParam String name) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        return map;
    }

    /**
     * Json显示-返回List格式
     * <p>
     * 地址：http://localhost:8080/hello/json
     * 显示：[{"name":"Shanhy-1"},{"name":"Shanhy-2"},{"name":"Shanhy-3"},{"name":"Shanhy-4"},{"name":"Shanhy-5"}]
     */
    @RequestMapping("/json")
    public List<Map<String, String>> getList() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map;
        for (int i = 1; i <= 5; i++) {
            map = new HashMap<String, String>();
            map.put("name", "Shanhy-" + i);
            list.add(map);
        }
        return list;
    }


    /**
     * web日志实例
     * <p>
     * 地址：http://localhost:8080/hello/logo
     */
    @RequestMapping("/logo")
    public String logo() {
        return "show logo";
    }


    /**
     * 得到用户的列表
     * 地址：https://localhost:8443/hello/getCityList
     */
    @RequestMapping("/getCityList")
    public List<City> getCityList() {

        City city1=new City(1,"city1","001");
        City city2=new City(2,"city2","002");
        City city3=new City(3,"city3","003");

        List<City> cityList=Arrays.asList(city1,city2,city3);

        return cityList;
    }

    /**
     * post 一个city信息，并返回一个数据
     * 地址：https://localhost:8443/hello/addCity
     * 利用postman进行测试
     */
    @RequestMapping("/addCity")
    public City addCity(@RequestBody City city){
        city.setCode(city.getCode()+"ok");
        return city;
    }



}