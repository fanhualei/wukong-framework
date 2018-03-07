package com.wukong;


import com.wukong.security.dao.UserMapper;
import com.wukong.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication()
@EnableCaching
public class DemoApplication implements CommandLineRunner {

    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;


    @Override
    public void run(String... args) throws Exception {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}