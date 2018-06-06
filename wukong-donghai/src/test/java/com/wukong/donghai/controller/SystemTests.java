package com.wukong.donghai.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class SystemTests  {

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

        assertThat(renStr).isEqualTo("{\"return\":\"你好中国\"}");


        assertThat(renStr).as("检查字符串：%s",renStr).isEqualTo("{\"return\":\"你好中国\"}");





    }


}
