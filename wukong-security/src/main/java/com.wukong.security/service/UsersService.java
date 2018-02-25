package com.wukong.security.service;

import com.wukong.security.model.Users;
import com.wukong.security.dao.UsersMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsersService {
    //利用SpingIOC注入DAO变量
    @Autowired
    private UsersMapper usersMapper;

    @Transactional
    public int deleteByPrimaryKey(String username_) {
        return usersMapper.deleteByPrimaryKey(username_);
    }

    @Transactional
    public int insert(Users record) {
        return usersMapper.insert(record);
    }

    @Transactional
    public int insertSelective(Users record) {
        return usersMapper.insertSelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(Users record) {
        return usersMapper.updateByPrimaryKey(record);
    }

    @Transactional
    public int updateByPrimaryKeySelective(Users record) {
        return usersMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly=true)
    public long count() {
        return usersMapper.countByExample().build().execute();
    }

    @Transactional(readOnly=true)
    public Users selectByPrimaryKey(String username_) {
        return usersMapper.selectByPrimaryKey(username_);
    }

    @Transactional(readOnly=true)
    public List<Users> selectAll() {
        return usersMapper.selectByExample().build().execute();
    }



}