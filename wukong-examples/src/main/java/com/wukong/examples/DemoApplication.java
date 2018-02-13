package com.wukong.examples;


import com.wukong.core.config.AllCoreConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({AllCoreConfig.class})
@SpringBootApplication
public class DemoApplication{
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}