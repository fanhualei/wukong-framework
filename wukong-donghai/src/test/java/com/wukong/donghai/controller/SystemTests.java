package com.wukong.donghai.controller;


import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class SystemTests {

    // 不要使用spring 的StringUtils :org.springframework.util.StringUtils
    @Test
    public void testPath(){
        String a="/*,/hello/**,/wechat/**";
        String[] urlArray= StringUtils.split(a,",");
        for (String url:urlArray) {
            System.out.println(url);
        }
    }

    @Test
    public void testStringUtils(){
        String renStr="{\"$1\":\"$2\"}";

        renStr=renStr.replace("$1","return");
        renStr=renStr.replace("$2","你好中国");

        System.out.println(renStr);


    }


}
