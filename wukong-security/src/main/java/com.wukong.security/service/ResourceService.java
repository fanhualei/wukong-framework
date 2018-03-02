package com.wukong.security.service;

import com.wukong.security.model.Resource;
import com.wukong.security.model.ResourceExample;
import com.wukong.security.dao.ResourceMapper;

import org.apache.ibatis.annotations.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResourceService {
    //利用SpingIOC注入DAO变量
    @Autowired
    private ResourceMapper resourceMapper;

    @Transactional(readOnly=true)
    public long countByExample(ResourceExample example) {
        return resourceMapper.countByExample(example);
    }

    @Transactional
    public int deleteByExample(ResourceExample example) {
        return resourceMapper.deleteByExample(example);
    }

    @Transactional
    public int deleteByPrimaryKey(Integer resourceId) {
        return resourceMapper.deleteByPrimaryKey(resourceId);
    }

    @Transactional
    public int insert(Resource record) {
        return resourceMapper.insert(record);
    }

    @Transactional
    public int insertSelective(Resource record) {
        return resourceMapper.insertSelective(record);
    }

    @Transactional(readOnly=true)
    public List<Resource> selectByExample(ResourceExample example) {
        return resourceMapper.selectByExample(example);
    }

    @Transactional(readOnly=true)
    public Resource selectByPrimaryKey(Integer resourceId) {
        return resourceMapper.selectByPrimaryKey(resourceId);
    }

    @Transactional
    public int updateByExampleSelective(@Param("record") Resource record,@Param("example") ResourceExample example) {
        return resourceMapper.updateByExampleSelective(record,example);
    }

    @Transactional
    public int updateByExample(@Param("record") Resource record,@Param("example") ResourceExample example) {
        return resourceMapper.updateByExample(record,example);
    }

    @Transactional
    public int updateByPrimaryKeySelective(Resource record) {
        return resourceMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(Resource record) {
        return resourceMapper.updateByPrimaryKey(record);
    }

}