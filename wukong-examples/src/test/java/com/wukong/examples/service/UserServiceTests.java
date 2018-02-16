package com.wukong.examples.service;

import com.wukong.examples.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTests extends AbstractTestNGSpringContextTests {
    @Autowired
    private UserService userService;



    @Test
    public void  testDeleteByPrimaryKey(){
        //delNum 删除的记录数量
        int  delNum=userService.deleteByPrimaryKey(16);
        System.out.println("===========:"+delNum);
    }

    //指定一个ID，进行更新
    @Test
    public void  testInsert_setID(){
        User record=new User();
        int id=10;
        record.setUserId(id);
        record.setAge(10000+id);
        record.setCreateDate(new Date());
        record.setGroupsId(20000+id);
        record.setUserName("name"+id);
        int  insertNum=userService.insert(record);
        assertThat(insertNum).isEqualTo(1);
    }

    //系统系统自动获得ID
    //@TODO 还不能自动返回id
    @Test
    public void  testInsert_autoID(){
        User record=new User();
        int id=11;
        record.setAge(10000+id);
        record.setCreateDate(new Date());
        record.setGroupsId(20000+id);
        record.setUserName("name"+id);
        int  insertNum=userService.insert(record);
        assertThat(insertNum).isEqualTo(1);
        System.out.println("newID===========:"+record.getUserId());
    }

    /**
     * age date 这两个字段为空的情况下
     */
    @Test
    public void  testInsertSelective_autoID(){
        User record=new User();
        int id=12;
        record.setGroupsId(20000+id);
        record.setUserName("name"+id);
        int  insertNum=userService.insertSelective(record);
        assertThat(insertNum).isEqualTo(1);
        System.out.println("newID===========:"+record.getUserId());
    }


    /**
     * 更新所有字段
     */
    @Test
    void testUpdateByPrimaryKey(){
        User record=new User();
        int id=12;
        record.setUserId(12);
        record.setUserName("name"+id);
        int  updateNum=userService.updateByPrimaryKey(record);
    }

    /**
     * 部分字段更新,如果不输入，那么就取数据库的默认值
     */
    @Test
    void testUpdateByPrimaryKeySelective(){
        User record=new User();
        int id=12;
        record.setUserId(12);
        record.setAge(10000+id);

        int  updateNum=userService.updateByPrimaryKeySelective(record);
    }



    @Test
    public void testCount(){
        long count=userService.count();
        System.out.println("count===========:"+count);
    }


    //@TODO 这个测试是错误的，没有从正确的数据库上读取数据
    @Test
    public void countOfWrite(){
        long count=userService.countOfWrite();
        System.out.println("count===========:"+count);


    }

    /**
     *  自己添加的xml来写的sql语句
     *  报错:MySQLSyntaxErrorException: Table 'wukong_read.User' doesn't exist
     *  @TODO 这个有错误  需要看 mysql 的官方的例子
     */
    @Test
    public  void testSelectById(){
       User user=  userService.selectByPrimaryKey(1);
        System.out.println("count===========:"+user.getUserName());
    }


}
