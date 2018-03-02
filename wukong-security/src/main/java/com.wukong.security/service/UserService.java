package com.wukong.security.service;

import com.wukong.security.model.User;
import com.wukong.security.model.UserExample;
import com.wukong.security.dao.UserMapper;

import org.apache.ibatis.annotations.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    //利用SpingIOC注入DAO变量
    @Autowired
    private UserMapper userMapper;

    @Transactional(readOnly=true)
    public long countByExample(UserExample example) {
        return userMapper.countByExample(example);
    }

    @Transactional
    public int deleteByExample(UserExample example) {
        return userMapper.deleteByExample(example);
    }

    @Transactional
    public int deleteByPrimaryKey(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Transactional
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Transactional
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Transactional(readOnly=true)
    public List<User> selectByExample(UserExample example) {
        return userMapper.selectByExample(example);
    }

    @Transactional(readOnly=true)
    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Transactional
    public int updateByExampleSelective(@Param("record") User record,@Param("example") UserExample example) {
        return userMapper.updateByExampleSelective(record,example);
    }

    @Transactional
    public int updateByExample(@Param("record") User record,@Param("example") UserExample example) {
        return userMapper.updateByExample(record,example);
    }

    @Transactional
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

}