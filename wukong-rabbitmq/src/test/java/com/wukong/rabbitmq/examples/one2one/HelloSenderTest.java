package com.wukong.rabbitmq.examples.one2one;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloSenderTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private HelloSender helloSender;

    //@Test
    public void hello() throws Exception {
        helloSender.send();
    }

}
