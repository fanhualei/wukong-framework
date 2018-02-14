package com.wukong.examples.dao;

import com.wukong.examples.dao.auto.UserMapper;
import com.wukong.examples.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyUserMapper extends UserMapper {
    /**
     * 查询-实体
     *
     * @param id
     * @return 实体
     */
    public User getById(int id);
}
