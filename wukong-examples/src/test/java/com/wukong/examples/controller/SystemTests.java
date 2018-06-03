package com.wukong.examples.controller;

import org.testng.annotations.Test;

import java.math.BigDecimal;

public class SystemTests {

    @Test
    public void testPath(){

        //类所在位置
        System.out.println( HelloControllerTests.class.getResource(""));
        //classPath的路径
        System.out.println( HelloControllerTests.class.getResource("/").getPath());
        System.out.println( HelloControllerTests.class.getClassLoader().getResource(""));
        Byte tinyintf;
        tinyintf=1;


        BigDecimal a=new BigDecimal(111);
        BigDecimal b=new BigDecimal(222);
        a=a.add(b);


    }
}
