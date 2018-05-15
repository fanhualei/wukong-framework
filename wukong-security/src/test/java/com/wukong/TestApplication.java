package com.wukong;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication()
@EnableCaching
@RestController
public class TestApplication implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
    }
    @RequestMapping("/hello")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String hello()
    {
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}