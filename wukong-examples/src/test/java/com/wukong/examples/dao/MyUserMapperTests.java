package com.wukong.examples.dao;

import com.wukong.security.dao.UserMapper;
import com.wukong.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyUserMapperTests extends AbstractTestNGSpringContextTests {


    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;


    //lamdba 介绍
    //http://www.jdon.com/idea/java/10-example-of-lambda-expressions-in-java8.html

    @Test
    public void testSelectByPrimaryKey() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);
    }

    @Test
    public void testCountByExample(){
        long count= userMapper.countByExample(null);
        System.out.println(count);
    }

    @Test
    public void testSelectAll(){
        List<User> list=userMapper.selectByExample(null);
        list.forEach(System.out::println);
//        list.forEach(n->assertThat(n.getUsername()).isEqualTo("admin"));
    }





}
