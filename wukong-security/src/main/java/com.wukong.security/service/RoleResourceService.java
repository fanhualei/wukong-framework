package com.wukong.security.service;

import com.wukong.security.model.RoleResource;
import com.wukong.security.model.RoleResourceExample;
import com.wukong.security.dao.RoleResourceMapper;

import org.apache.ibatis.annotations.Param;

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

    @Transactional(readOnly=true)
    public long countByExample(RoleResourceExample example) {
        return roleResourceMapper.countByExample(example);
    }

    @Transactional
    public int deleteByExample(RoleResourceExample example) {
        return roleResourceMapper.deleteByExample(example);
    }

    @Transactional
    public int deleteByPrimaryKey(Integer roleResourceId) {
        return roleResourceMapper.deleteByPrimaryKey(roleResourceId);
    }

    @Transactional
    public int insert(RoleResource record) {
        return roleResourceMapper.insert(record);
    }

    @Transactional
    public int insertSelective(RoleResource record) {
        return roleResourceMapper.insertSelective(record);
    }

    @Transactional(readOnly=true)
    public List<RoleResource> selectByExample(RoleResourceExample example) {
        return roleResourceMapper.selectByExample(example);
    }

    @Transactional(readOnly=true)
    public RoleResource selectByPrimaryKey(Integer roleResourceId) {
        return roleResourceMapper.selectByPrimaryKey(roleResourceId);
    }

    @Transactional
    public int updateByExampleSelective(@Param("record") RoleResource record,@Param("example") RoleResourceExample example) {
        return roleResourceMapper.updateByExampleSelective(record,example);
    }

    @Transactional
    public int updateByExample(@Param("record") RoleResource record,@Param("example") RoleResourceExample example) {
        return roleResourceMapper.updateByExample(record,example);
    }

    @Transactional
    public int updateByPrimaryKeySelective(RoleResource record) {
        return roleResourceMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(RoleResource record) {
        return roleResourceMapper.updateByPrimaryKey(record);
    }

}