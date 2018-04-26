package com.wukong;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@EnableCaching
public class DonghaiApplication implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }


    public static void main(String[] args) {
        SpringApplication.run(DonghaiApplication.class, args);

    }

}
