package com.wukong.security;

import com.wukong.security.model.Role;
import com.wukong.security.model.User;
import com.wukong.security.service.RoleService;
import com.wukong.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = "wukong:security:userdetails:")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;



    @Override
    @Cacheable(key="#p0")
    public UserDetails loadUserByUsername(String username) {
        User user = userService.selectUserByAccount(username);
        log.debug("日志输出");
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<SimpleGrantedAuthority> authorities = loadGrantedAuthorityByUserid(user.getUserId());
        //以前调用了org.springframework.security.core.userdetails.User
        return new CustomUserDetails(user,authorities);
    }

    //得到用户的权限 这个是我自己做的
    private List<SimpleGrantedAuthority> loadGrantedAuthorityByUserid(Integer userid){
        List<Role> roles=roleService.selectRolesByUserid(userid);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getRolename()));});
        return authorities;
    }
}
