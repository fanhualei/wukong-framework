package com.wukong.security.util;

import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtTokenUtilTests {

    //@todo 这个地方可能出现自动装载的错误
    private JwtTokenUtil jwtTokenUtil=new JwtTokenUtil();

    private String username="fanhl";
    private Integer userid=888;
    private String token;

    private  SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

//    @BeforeMethod
    public void getToken(){
        token=jwtTokenUtil.generateToken(username,userid);
        System.out.println("token = " + token);
    }


//    @Test
    //测试是否可以提取出数据
    public void testAnalysisToken(){
        Date createDate_=jwtTokenUtil.getCreatedDateFromToken(token);
        System.out.println("createDate_     = " + myFmt.format(createDate_));

        Date expirationDate_=jwtTokenUtil.getExpirationDateFromToken(token);
        System.out.println("expirationDate_ = " + myFmt.format(expirationDate_) );

        String username_=jwtTokenUtil.getUsernameFromToken(token);
        Claims claims_= jwtTokenUtil.getClaimsFromToken(token);
        System.out.println("claims_ = " + claims_);

        Integer userid_=jwtTokenUtil.getUseridFromToken(token);

        assertThat(username).isEqualTo(username_);
        assertThat(userid).isEqualTo(userid_);

//        jwtTokenUtil.refreshToken();
//        jwtTokenUtil.canTokenBeRefreshed()
    }


    @Test
    public void testDate(){
        Date date=new Date();
        System.out.println(date.toString());

        long time =date.getTime();

        System.out.println(time);


        Date date1=new Date(time);

        System.out.println(date1);


    }





}
