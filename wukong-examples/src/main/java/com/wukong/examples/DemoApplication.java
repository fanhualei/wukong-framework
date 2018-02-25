package com.wukong.examples;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.wukong.core","com.wukong.security","com.wukong.examples"})
public class DemoApplication implements CommandLineRunner {



    @Override
    public void run(String... args) throws Exception {
        //this can print some testing code

    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);



    }
}