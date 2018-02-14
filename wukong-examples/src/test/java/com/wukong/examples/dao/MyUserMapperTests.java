package com.wukong.examples.dao;

import com.wukong.examples.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.List;

import static com.wukong.examples.dao.auto.UserDynamicSqlSupport.userName;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//事务回滚
//@Transactional  //开启全局事务
//@Rollback       //数据数据回滚
public class MyUserMapperTests extends AbstractTestNGSpringContextTests {
    @Autowired
    private MyUserMapper userMapper;


    @Test
    public void testSelectDistinctByExample() {
        List<User> list = userMapper.selectDistinctByExample()
                .where(userName, isLike("%" + "aaa" + "%"))
                .build()
                .execute();
        int i=list.size();
    }


    @Test
    public void testCount(){
        long count= userMapper.countByExample()
                .build()
                .execute();
        System.out.println(count);
    }


//    long count(SelectStatementProvider selectStatement);
//    int delete(DeleteStatementProvider deleteStatement);
//    int insert(InsertStatementProvider<User> insertStatement);
//    User selectOne(SelectStatementProvider selectStatement);
//
//
//    List<User> selectMany(SelectStatementProvider selectStatement);
//
//
//    int update(UpdateStatementProvider updateStatement);






}
