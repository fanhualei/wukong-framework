
package com.wukong.examples.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.*;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class JwtAuthenControllerTests extends AbstractTestNGSpringContextTests {

//    @Autowired
//    private TestRestTemplate restAuthTemplate; //用于测需要验证的
    @Autowired
    private TestRestTemplate restTemplate;

    private String token="1212";
    private BasicJwtInterceptor basicJwtInterceptor;
    private String verifyCode;
    private String baseUrl="/author/jwt/";

    //    public void getHeader() throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("token","xxxxxx");
//        HttpEntity formEntity = new HttpEntity(headers);
//        String[] urlVariables = new String[]{"admin"};
//        ResponseEntity<ActResult> result = testRestTemplate.exchange("/test/getHeader?username={username}", HttpMethod.GET,formEntity,ActResult.class,urlVariables);
//        Assert.assertEquals(result.getBody().getCode(),0);
//    }

    private HttpEntity getAuthHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",token);
        return new HttpEntity<String>(headers);
    }

    //@Test(priority = 0)
    public void testPhoneExist(){
        String url=baseUrl+"/public/phoneExist?cellphone=1234567890";

        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    //@Test(priority = 1)
    public void testEmailExist(){
        String url=baseUrl+"/public/emailExist?email=xxxx@xxxx.com";

        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    //@Test(priority = 2)
    public void testUsernameExist(){
        String url=baseUrl+"/public/usernameExist?username=admin";

        Map<String, Boolean> map = this.restTemplate.getForObject(url,Map.class);
        Boolean value=map.get("usernameExist");
        assertThat(value).isEqualTo(true);

    }
    //@Test(priority = 3)
    public void testLoginOk(){
        String url=baseUrl+"/public/login?username=admin&password=admin";
        Map<String, String> map = this.restTemplate.getForObject(url,Map.class);
        String token=map.get("token");
        assertThat(token).isNotNull();
    }
    //@Test(priority = 4)
    public void testGetVerifyCode(){
        String url=baseUrl+"/public/getVerifyCode?cellphone=1234567890";
        Map<String, String> map = this.restTemplate.getForObject(url,Map.class);
        verifyCode=map.get("verifycode");
        assertThat(verifyCode.length()).isEqualTo(6);
    }
    //@Test(priority = 5)
    public void testRegist(){
        String url=baseUrl+"/public/regist?cellphone=1234567890&password=123456&verifycode="+verifyCode;
        Map<String, String> map = this.restTemplate.getForObject(url,Map.class);
        String token=map.get("token");
        assertThat(token).isNotNull();
        this.token=token;
    }
    //@Test(priority = 6)
    public void testLoginByPhonemessage(){
        testGetVerifyCode();
        String url=baseUrl+"/public/loginByPhonemessage?cellphone=1234567890&verifycode="+verifyCode;
        Map<String, String> map = this.restTemplate.getForObject(url,Map.class);
        String token=map.get("token");
        assertThat(token).isNotNull();
        System.out.println("set "+token);
        this.token=token;
//        basicJwtInterceptor.setToken(token);
    }
    //@Test(priority = 7)
    public void testRefreshToken(){
        String url=baseUrl+"/refreshToken";
        ResponseEntity<Map> result=this.restTemplate.exchange(url,HttpMethod.GET,getAuthHeader(),Map.class);
        //Map<String, String> map = this.restTemplate.getForObject(url,Map.class);
        String token=((Map<String,String>)result.getBody()).get("token");
        assertThat(token).isNotNull();
        this.token=token;
        //basicJwtInterceptor.setToken(token);
    }
    //@Test(priority = 8)
    public void testchangePassword(){
        testGetVerifyCode();
        String url=baseUrl+"/changePassword?cellphone=1234567890&password=123&verifycode="+verifyCode;
        ResponseEntity<Map> result=this.restTemplate.exchange(url,HttpMethod.GET,getAuthHeader(),Map.class);
        //Map<String, String> map = this.restTemplate.getForObject(url,Map.class);
        String token=((Map<String,String>)result.getBody()).get("token");
        assertThat(token).isNotNull();
        this.token=token;
    }

//    @Test

//    @Test
//    public void testInfoByGet() {
//        String url="/hello/info?name=张三";
//        Map<String, String> map = this.restTemplate.getForObject(url,Map.class);
//        String value=map.get("name");
//        assertThat(value).isEqualTo("张三");
//    }








}
