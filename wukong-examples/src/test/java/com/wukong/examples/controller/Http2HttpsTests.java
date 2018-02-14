package com.wukong.examples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Http2HttpsTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestRestTemplate restTemplate;


    @Value("${wukong.web.http2https:true}") //true:不重定向  false:重定向
    private boolean http2https;

    /**
     * 测试 http重新定向是否可以  使用的是8080端口，如果是80与443，需要修改一下下面的代码
     * 如果要用这个，请将enabled=true  false
     */
    @Test(enabled = false)
    public void testHttp2Https(){
        if(http2https){ //不重新定向 8080 与 8443都可以
            String url="http://localhost:8080/hello";
            ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
            assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(entity.getBody()).isEqualTo("Hello World");

            url="https://localhost:8443/hello";
            entity = this.restTemplate.getForEntity(url, String.class);
            assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(entity.getBody()).isEqualTo("Hello World");



        }else{ //重新定向
            String url="http://localhost:8080/hello";
            ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);

            //测试重新定向成功
            assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.FOUND);

            //使用新的https进行测试
            url=entity.getHeaders().getLocation().toString();
            entity = this.restTemplate.getForEntity(url, String.class);
            assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(entity.getBody()).isEqualTo("Hello World");
        }
    }

}
