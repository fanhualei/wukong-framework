package com.wukong;


import com.wukong.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication()
public class DemoApplication implements CommandLineRunner {

//    @SuppressWarnings("all")
//    @Autowired
//    private UserService userService;


    @Override
    public void run(String... args) throws Exception {
//        System.out.println("user = [" + userService.selectByPrimaryKey(1) + "]");
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}