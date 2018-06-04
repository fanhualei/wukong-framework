package com.wukong.donghai.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHello() {
        String url="/hello";

        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("\"Hello World 你好\"");
    }
}
