package com.wukong.security.service;

import com.wukong.security.dao.UserRoleMapper;
import com.wukong.security.model.User;
import com.wukong.security.dao.UserMapper;
import static com.wukong.security.dao.UserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    //利用SpingIOC注入DAO变量
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;



    @Transactional
    public int deleteByPrimaryKey(Long userId_) {
        return userMapper.deleteByPrimaryKey(userId_);
    }

    @Transactional
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Transactional
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Transactional
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly=true)
    public long count() {
        return userMapper.countByExample().build().execute();
    }

    @Transactional(readOnly=true)
    public User selectByPrimaryKey(Long userId_) {
        return userMapper.selectByPrimaryKey(userId_);
    }

    @Transactional(readOnly=true)
    public List<User> selectAll() {
        return userMapper.selectByExample().build().execute();
    }


    //重写loadUserByUsername 方法获得 userdetails 类型用户
    //https://github.com/527515025/springBoot/blob/master/springboot-SpringSecurity0/src/main/java/com/us/example/security/CustomUserService.java
    @Override
    public UserDetails loadUserByUsername(String username_) {


        List<User> userList=  userMapper.selectDistinctByExample()
                            .where(username,isEqualTo(username_))
                            .build()
                            .execute();


        if(userList==null || userList.size()>0){
            throw new UsernameNotFoundException("用户名不存在");
        }

        User user =userList.get(0);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。





        return null;
    }

    }