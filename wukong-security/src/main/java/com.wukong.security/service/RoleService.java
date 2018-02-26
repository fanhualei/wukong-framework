package com.wukong.security.service;

import com.wukong.security.model.Role;
import com.wukong.security.dao.RoleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleService {
    //利用SpingIOC注入DAO变量
    @Autowired
    private RoleMapper roleMapper;

    @Transactional
    public int deleteByPrimaryKey(Long roleId_) {
        return roleMapper.deleteByPrimaryKey(roleId_);
    }

    @Transactional
    public int insert(Role record) {
        return roleMapper.insert(record);
    }

    @Transactional
    public int insertSelective(Role record) {
        return roleMapper.insertSelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(Role record) {
        return roleMapper.updateByPrimaryKey(record);
    }

    @Transactional
    public int updateByPrimaryKeySelective(Role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly=true)
    public long count() {
        return roleMapper.countByExample().build().execute();
    }

    @Transactional(readOnly=true)
    public Role selectByPrimaryKey(Long roleId_) {
        return roleMapper.selectByPrimaryKey(roleId_);
    }

    @Transactional(readOnly=true)
    public List<Role> selectAll() {
        return roleMapper.selectByExample().build().execute();
    }

}