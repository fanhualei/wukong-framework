package com.wukong.examples.service;



import com.wukong.core.datasource.DatasourceAnno;
import com.wukong.examples.dao.MyUserMapper;
import com.wukong.examples.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.wukong.core.datasource.DatasourceAnno.master;
import static com.wukong.examples.dao.auto.UserDynamicSqlSupport.userName;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

@Service

public class UserService  {


    @Autowired
    private MyUserMapper userMapper;


    public int deleteByPrimaryKey(Integer userId_){
        return  userMapper.deleteByPrimaryKey(userId_);
    }

    public int insert(User record){
        return userMapper.insert(record);
    }



    public int insertSelective(User record){
        return userMapper.insertSelective(record);
    }

    public int updateByPrimaryKey(User record){
        return userMapper.updateByPrimaryKey(record);
    }

    public int updateByPrimaryKeySelective(User record){
        return userMapper.updateByPrimaryKeySelective(record);
    }

    public long count(){
        return userMapper.countByExample().build().execute();
    }

    public long countOfWrite(){
        return userMapper.countByExample().build().execute();
    }

    @DatasourceAnno(name=master)
    public User selectByPrimaryKey(Integer userId_){
        return userMapper.selectByPrimaryKey(userId_);
    }

    public List<User> selectAll(){
        return userMapper.selectByExample().build().execute();
    }

    public List<User> selectByName(String name) {
        List<User> list = userMapper.selectDistinctByExample()
                .where(userName, isLike("%" + name + "%"))
                .build()
                .execute();
        return list;
    }

    /**
     *  自己添加的xml来写的sql语句
     */

    public User selectById(int id) {
        return userMapper.selectById(id);
    }


}
