package com.wukong.security.service;

import com.wukong.security.model.UserRole;
import com.wukong.security.model.UserRoleExample;
import com.wukong.security.dao.UserRoleMapper;

import org.apache.ibatis.annotations.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserRoleService {
    //利用SpingIOC注入DAO变量
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Transactional(readOnly=true)
    public long countByExample(UserRoleExample example) {
        return userRoleMapper.countByExample(example);
    }

    @Transactional
    public int deleteByExample(UserRoleExample example) {
        return userRoleMapper.deleteByExample(example);
    }

    @Transactional
    public int deleteByPrimaryKey(Integer userRoleId) {
        return userRoleMapper.deleteByPrimaryKey(userRoleId);
    }

    @Transactional
    public int insert(UserRole record) {
        return userRoleMapper.insert(record);
    }

    @Transactional
    public int insertSelective(UserRole record) {
        return userRoleMapper.insertSelective(record);
    }

    @Transactional(readOnly=true)
    public List<UserRole> selectByExample(UserRoleExample example) {
        return userRoleMapper.selectByExample(example);
    }

    @Transactional(readOnly=true)
    public UserRole selectByPrimaryKey(Integer userRoleId) {
        return userRoleMapper.selectByPrimaryKey(userRoleId);
    }

    @Transactional
    public int updateByExampleSelective(@Param("record") UserRole record,@Param("example") UserRoleExample example) {
        return userRoleMapper.updateByExampleSelective(record,example);
    }

    @Transactional
    public int updateByExample(@Param("record") UserRole record,@Param("example") UserRoleExample example) {
        return userRoleMapper.updateByExample(record,example);
    }

    @Transactional
    public int updateByPrimaryKeySelective(UserRole record) {
        return userRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(UserRole record) {
        return userRoleMapper.updateByPrimaryKey(record);
    }

}