package com.wukong.security.service;

import com.wukong.security.model.Resource;
import com.wukong.security.dao.ResourceMapper;

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

    @Transactional
    public int deleteByPrimaryKey(Long resourceId_) {
        return resourceMapper.deleteByPrimaryKey(resourceId_);
    }

    @Transactional
    public int insert(Resource record) {
        return resourceMapper.insert(record);
    }

    @Transactional
    public int insertSelective(Resource record) {
        return resourceMapper.insertSelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(Resource record) {
        return resourceMapper.updateByPrimaryKey(record);
    }

    @Transactional
    public int updateByPrimaryKeySelective(Resource record) {
        return resourceMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly=true)
    public long count() {
        return resourceMapper.countByExample().build().execute();
    }

    @Transactional(readOnly=true)
    public Resource selectByPrimaryKey(Long resourceId_) {
        return resourceMapper.selectByPrimaryKey(resourceId_);
    }

    @Transactional(readOnly=true)
    public List<Resource> selectAll() {
        return resourceMapper.selectByExample().build().execute();
    }

}