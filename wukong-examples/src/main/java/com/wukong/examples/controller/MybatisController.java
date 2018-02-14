package com.wukong.examples.controller;


import com.wukong.examples.entity.User;
import com.wukong.examples.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mybatis")
public class MybatisController {
    @Autowired
    private UserService userService;

    /**
     * 查询
     * @param name
     * @return
     * https://localhost:8443/mybatis/searchList?name=123
     */
    @RequestMapping("/searchList")
    public List<User> searchList(String name) {
        return userService.selectByName(name);
    }



}
