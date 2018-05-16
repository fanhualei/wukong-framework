package com.wukong.security.controller;

import com.wukong.security.model.User;
import com.wukong.security.service.UserService;
import com.wukong.security.util.JwtTokenUtil;
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
public class JwtAuthenController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @RequestMapping("/login")
    public Object login(HttpServletResponse response,
                        @RequestParam String username,@RequestParam String password) throws IOException {
        User user=userService.selectUserByAccount(username);
        if(user!=null && MyEncoder.matches(password,user.getPassword())) {
            String jwt = jwtTokenUtil.generateToken(username,user.getUserId());
            response.setHeader("re_token",jwt);
            return new HashMap<String,String>(){{
                put("token", jwt);
            }};
        }else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }
    //TODO 以下API待通过注解完成一个手机号码的有效性验证
    @RequestMapping("/getVerifyCode")
    public Object getVerifyCode(HttpServletResponse response,@RequestParam String cellphone)throws IOException{
        if(cellphone!=null&&!cellphone.equals("")){//cellphone的验证按照设想应该有注解完成，这里仅仅验证是否为空
            String verifyCode=userService.getVerifyCode(cellphone);
            if(verifyCode!=null)
                return new HashMap<String,String>(){{
                    put("verifycode", verifyCode);
                }};
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
    //此处有个问题 密码是否为明文传输，若是，我这需要进行加密，否则加密归前端
    @RequestMapping("/regist")
    public Object regist(HttpServletResponse response,@RequestParam String cellphone,@RequestParam String password,@RequestParam String verifycode )throws IOException{
        if(!cellphone.equals("")&& !password.equals("")&&!verifycode.equals("")){
            User user=userService.regist(cellphone,password,verifycode);
            if(user!=null){
                String jwt = jwtTokenUtil.generateToken(user.getUsername(),user.getUserId());
                response.setHeader("re_token",jwt);
                return new HashMap<String,String>(){{
                    put("token", jwt);
                }};
            }
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
//    public Object loginByPhoneMessage(){
//
//    }
}
