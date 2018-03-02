package com.wukong.security.service;

import com.wukong.security.model.User;
import com.wukong.security.dao.UserMapper;

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

    @Transactional
    public int deleteByPrimaryKey(Integer userId_) {
        return userMapper.deleteByPrimaryKey(userId_);
    }

    @Transactional
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Transactional
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Transactional
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly=true)
    public long count() {
        return userMapper.countByExample().build().execute();
    }

    @Transactional(readOnly=true)
    public User selectByPrimaryKey(Integer userId_) {
        return userMapper.selectByPrimaryKey(userId_);
    }

    @Transactional(readOnly=true)
    public List<User> selectAll() {
        return userMapper.selectByExample().build().execute();
    }

}