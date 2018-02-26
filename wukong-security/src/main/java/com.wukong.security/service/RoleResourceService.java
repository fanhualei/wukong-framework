package com.wukong.security.service;

import com.wukong.security.model.RoleResource;
import com.wukong.security.dao.RoleResourceMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleResourceService {
    //利用SpingIOC注入DAO变量
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Transactional
    public int deleteByPrimaryKey(Long roleResourceId_) {
        return roleResourceMapper.deleteByPrimaryKey(roleResourceId_);
    }

    @Transactional
    public int insert(RoleResource record) {
        return roleResourceMapper.insert(record);
    }

    @Transactional
    public int insertSelective(RoleResource record) {
        return roleResourceMapper.insertSelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(RoleResource record) {
        return roleResourceMapper.updateByPrimaryKey(record);
    }

    @Transactional
    public int updateByPrimaryKeySelective(RoleResource record) {
        return roleResourceMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly=true)
    public long count() {
        return roleResourceMapper.countByExample().build().execute();
    }

    @Transactional(readOnly=true)
    public RoleResource selectByPrimaryKey(Long roleResourceId_) {
        return roleResourceMapper.selectByPrimaryKey(roleResourceId_);
    }

    @Transactional(readOnly=true)
    public List<RoleResource> selectAll() {
        return roleResourceMapper.selectByExample().build().execute();
    }

}