package com.wukong.donghai.controller;

import com.wukong.core.result.SingleResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* 这是一个测试的类，今后要移动到test包中
* @author fanhl
*/
@RestController
@RequestMapping("/test/single")
public class SingleResponseController {


    @RequestMapping("/string")
    public String string() {
        return "Hello World 你好世界";
    }


    @RequestMapping("/string1")
    @SingleResponseResult()
    public String string1() {
        return "Hello World 你好世界";
    }


    @RequestMapping("/num")
    public int  num() {
        return 666;
    }

    @RequestMapping("/num1")
    @SingleResponseResult()
    public int  num1() {
        return 666;
    }

    @RequestMapping("/long")
    public long  longm() {
        return 666;
    }

    @RequestMapping("/long1")
    @SingleResponseResult()
    public long  longm1() {
        return 666;
    }


    @RequestMapping("/bigdecimal")
    public BigDecimal bigdecimal() {
        return new BigDecimal(666);
    }

    @RequestMapping("/bigdecimal1")
    @SingleResponseResult()
    public BigDecimal  bigdecimal1() {
        return new BigDecimal(666);
    }

    @RequestMapping("/date")
    public Date date() {
        Calendar cal= Calendar.getInstance();
        cal.set(2018,6,1,23,15,16);
        return cal.getTime();
    }

    @RequestMapping("/date1")
    @SingleResponseResult()
    public Date  date1() {

        Calendar cal= Calendar.getInstance();
        cal.set(2018,6,1,23,15,16);
        return cal.getTime();
    }


    @RequestMapping("/e")
    @SingleResponseResult
    public Integer  e() {

        if(1==1)
            throw new RuntimeException("小明");

        return 999;
    }


    @RequestMapping("/map")
    public Map<String,String> map() {

        Map<String,String> map=new HashMap<>();
        map.put("name","小明");
        return map;
    }


    @RequestMapping("/map1")
    @SingleResponseResult
    public Map<String,String> map1() {

        Map<String,String> map=new HashMap<>();
        map.put("name","小明");
        return map;
    }



    @RequestMapping("/maps")
    public Map<String,Object> maps() {

        Calendar cal= Calendar.getInstance();
        cal.set(2018,6,1,23,15,16);


        Map<String,Object> map=new HashMap<>();
        map.put("name","小明");
        map.put("time",cal.getTime());
        map.put("long",12345678L);
        return map;
    }
}
