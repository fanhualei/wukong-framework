package com.wukong.examples.dao;

import com.wukong.security.model.User;
import com.wukong.security.dao.UserMapper;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;
import static com.wukong.security.dao.UserDynamicSqlSupport.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyUserMapperTests extends AbstractTestNGSpringContextTests {
    @Autowired
    private UserMapper userMapper;


    @Test
    public void testSelectDistinctByExample() {
        List<User> list = userMapper.selectDistinctByExample()
                .where(username, isLike("%" + "admin" + "%"))
                .or(user.userId,isEqualTo(1L))
                .build()
                .execute();


//        list.forEach(System.out::println);

        list.forEach(n->assertThat(n.getUsername()).isEqualTo("admin"));
        
    }


    @Test
    public void testCount(){
        long count= userMapper.countByExample()
                .build()
                .execute();
        System.out.println(count);
    }


}
