package com.wukong.security.service;

import com.wukong.security.model.UserEx;
import com.wukong.security.dao.UserExMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserExService {
    //利用SpingIOC注入DAO变量
    @Autowired
    private UserExMapper userExMapper;

    @Transactional
    public int deleteByPrimaryKey(Long userId_) {
        return userExMapper.deleteByPrimaryKey(userId_);
    }

    @Transactional
    public int insert(UserEx record) {
        return userExMapper.insert(record);
    }

    @Transactional
    public int insertSelective(UserEx record) {
        return userExMapper.insertSelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(UserEx record) {
        return userExMapper.updateByPrimaryKey(record);
    }

    @Transactional
    public int updateByPrimaryKeySelective(UserEx record) {
        return userExMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly=true)
    public long count() {
        return userExMapper.countByExample().build().execute();
    }

    @Transactional(readOnly=true)
    public UserEx selectByPrimaryKey(Long userId_) {
        return userExMapper.selectByPrimaryKey(userId_);
    }

    @Transactional(readOnly=true)
    public List<UserEx> selectAll() {
        return userExMapper.selectByExample().build().execute();
    }

}