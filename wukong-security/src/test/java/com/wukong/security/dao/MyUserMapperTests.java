package com.wukong.security.dao;

import com.wukong.security.dao.RoleMapper;
import com.wukong.security.dao.UserMapper;
import com.wukong.security.model.Role;
import com.wukong.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


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


    @Test
    public void testSelectUserByAccount(){
        User user =userMapper.selectUserByAccount("admin");
        assertThat(user.getUsername()).isEqualTo("admin");

    }

    @Autowired
    @SuppressWarnings("all")
    RoleMapper roleMapper;
    @Test
    public void testSelectRolesByUserid(){
        List<Role> list=roleMapper.selectRolesByUserid(1);
        list.forEach(System.out::println);
    }
    @Test
    public void testisUserExistByCellphone(){
        System.out.println(userMapper.isUserExistByCellphone("120"));
    }




}
