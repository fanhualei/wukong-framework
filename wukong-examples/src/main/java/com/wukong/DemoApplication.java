package com.wukong;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication()
@EnableCaching
public class DemoApplication implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}