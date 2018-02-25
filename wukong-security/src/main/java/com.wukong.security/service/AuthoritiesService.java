package com.wukong.security.service;

import com.wukong.security.model.Authorities;
import com.wukong.security.dao.AuthoritiesMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthoritiesService {
    //利用SpingIOC注入DAO变量
    @Autowired
    private AuthoritiesMapper authoritiesMapper;

    @Transactional
    public int deleteByPrimaryKey(String username_,String authority_) {
        return authoritiesMapper.deleteByPrimaryKey(username_,authority_);
    }

    @Transactional
    public int insert(Authorities record) {
        return authoritiesMapper.insert(record);
    }

    @Transactional
    public int insertSelective(Authorities record) {
        return authoritiesMapper.insertSelective(record);
    }

    @Transactional(readOnly=true)
    public long count() {
        return authoritiesMapper.countByExample().build().execute();
    }

    @Transactional(readOnly=true)
    public List<Authorities> selectAll() {
        return authoritiesMapper.selectByExample().build().execute();
    }

}