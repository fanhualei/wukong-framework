package com.wukong.security.service;

import com.wukong.security.model.UserRole;
import com.wukong.security.dao.UserRoleMapper;

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

    @Transactional
    public int deleteByPrimaryKey(Long userRoleId_) {
        return userRoleMapper.deleteByPrimaryKey(userRoleId_);
    }

    @Transactional
    public int insert(UserRole record) {
        return userRoleMapper.insert(record);
    }

    @Transactional
    public int insertSelective(UserRole record) {
        return userRoleMapper.insertSelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(UserRole record) {
        return userRoleMapper.updateByPrimaryKey(record);
    }

    @Transactional
    public int updateByPrimaryKeySelective(UserRole record) {
        return userRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly=true)
    public long count() {
        return userRoleMapper.countByExample().build().execute();
    }

    @Transactional(readOnly=true)
    public UserRole selectByPrimaryKey(Long userRoleId_) {
        return userRoleMapper.selectByPrimaryKey(userRoleId_);
    }

    @Transactional(readOnly=true)
    public List<UserRole> selectAll() {
        return userRoleMapper.selectByExample().build().execute();
    }

}