package com.wukong.security.controller;

import com.wukong.security.model.Account;
import com.wukong.security.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/author/jwt")
public class JwtController {


    @RequestMapping("/login")
    public Object login(HttpServletResponse response,
                        @RequestBody Account account) throws IOException {
        if(isValidPassword(account)) {
            String jwt = JwtUtil.generateToken(account.username);
            response.setHeader("dddd",jwt);
            return new HashMap<String,String>(){{
                put("token", jwt);
            }};
        }else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }


    private boolean isValidPassword(Account ac) {
        //we just have 2 hardcoded user
        if ("admin".equals(ac.username) && "admin".equals(ac.password)
                || "user".equals(ac.username) && "user".equals(ac.password)) {
            return true;
        }
        return false;
    }


}
