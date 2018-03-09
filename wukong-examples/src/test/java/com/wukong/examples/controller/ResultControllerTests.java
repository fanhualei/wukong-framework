package com.wukong.examples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResultControllerTests extends AbstractTestNGSpringContextTests {


    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeMethod
    public void getToken(){
        String url="/author/jwt/login?username=admin&password=admin";
        ResponseEntity<Map> entity = this.restTemplate.getForEntity(url, Map.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Map<String, String> returnMap=entity.getBody();
        String token=returnMap.get("token");
        BasicJwtInterceptor basicJwtInterceptor= new BasicJwtInterceptor(token);
        restTemplate.getRestTemplate().getInterceptors().add(basicJwtInterceptor);
    }


    @Test
    public void testFindAPIResultSuccess() {
        String url="/result/success";


        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(entity.getBody()).isEqualTo("Hello World");

        System.out.printf(entity.getBody().toString());
    }

}
