package com.wukong.security.service;

import com.wukong.security.model.UserEx;
import com.wukong.security.model.UserExExample;
import com.wukong.security.dao.UserExMapper;

import org.apache.ibatis.annotations.Param;

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

    @Transactional(readOnly=true)
    public long countByExample(UserExExample example) {
        return userExMapper.countByExample(example);
    }

    @Transactional
    public int deleteByExample(UserExExample example) {
        return userExMapper.deleteByExample(example);
    }

    @Transactional
    public int deleteByPrimaryKey(Integer userId) {
        return userExMapper.deleteByPrimaryKey(userId);
    }

    @Transactional
    public int insert(UserEx record) {
        return userExMapper.insert(record);
    }

    @Transactional
    public int insertSelective(UserEx record) {
        return userExMapper.insertSelective(record);
    }

    @Transactional(readOnly=true)
    public List<UserEx> selectByExample(UserExExample example) {
        return userExMapper.selectByExample(example);
    }

    @Transactional(readOnly=true)
    public UserEx selectByPrimaryKey(Integer userId) {
        return userExMapper.selectByPrimaryKey(userId);
    }

    @Transactional
    public int updateByExampleSelective(@Param("record") UserEx record,@Param("example") UserExExample example) {
        return userExMapper.updateByExampleSelective(record,example);
    }

    @Transactional
    public int updateByExample(@Param("record") UserEx record,@Param("example") UserExExample example) {
        return userExMapper.updateByExample(record,example);
    }

    @Transactional
    public int updateByPrimaryKeySelective(UserEx record) {
        return userExMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(UserEx record) {
        return userExMapper.updateByPrimaryKey(record);
    }

}