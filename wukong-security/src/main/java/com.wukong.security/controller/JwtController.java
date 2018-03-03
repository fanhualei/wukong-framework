package com.wukong.security.controller;

import com.wukong.security.model.User;
import com.wukong.security.service.UserService;
import com.wukong.security.util.JwtUtil;
import com.wukong.security.util.MyEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/author/jwt")
public class JwtController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Object login(HttpServletResponse response,
                        @RequestParam String username,@RequestParam String password) throws IOException {

        User user=userService.selectUserByAccount(username);

        if(user!=null && MyEncoder.matches(password,user.getPassword())) {

            String jwt = JwtUtil.generateToken(user.getUserId());

            response.setHeader("re_token",jwt);
            return new HashMap<String,String>(){{
                put("token", jwt);
//                put("userid",user.getUserId().toString());
            }};
        }else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }
}
