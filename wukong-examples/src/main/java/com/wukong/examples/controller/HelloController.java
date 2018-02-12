package com.wukong.examples.controller;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
//        logger.debug("日志输出测试 Debug");
//        logger.trace("日志输出测试 Trace");
//        logger.info("日志输出测试 Info");
//        logger.warn("日志输出测试 warn");
//        logger.error("日志输出测试 error");

        return "show logo";
    }

}