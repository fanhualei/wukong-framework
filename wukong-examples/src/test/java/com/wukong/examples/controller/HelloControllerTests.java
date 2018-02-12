
package com.wukong.examples.controller;

import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHello() {
        String url="/hello";
        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("Hello World");
    }


    @Test
    public void testJson() {
        String url="/hello/json";
        List<Map<String, String>> list = this.restTemplate.getForObject(url,List.class);

        for(int i=0;i<list.size();i++){
            Map<String, String> map =list.get(i);
            String value=map.get("name");
            assertThat(value).isEqualTo("Shanhy-"+(i+1));
        }
    }



    @Test
    public void testInfo() {
        String url="/hello/info?name=abc";
        Map<String, String> map = this.restTemplate.getForObject(url,Map.class);
        String value=map.get("name");
        assertThat(value).isEqualTo("abc");
    }


    @Test
    public void testLogo() {
        String url="/hello/logo";
        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("show logo");
    }

}
