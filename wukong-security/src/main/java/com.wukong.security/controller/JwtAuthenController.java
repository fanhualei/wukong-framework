package com.wukong.security.controller;

import com.wukong.security.model.User;
import com.wukong.security.service.UserService;
import com.wukong.security.util.JwtTokenUtil;
import com.wukong.security.util.MyEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@RestController
@RequestMapping("/author/jwt")
public class JwtAuthenController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @RequestMapping("/public/login")
    public Object login(HttpServletResponse response,
                        @RequestParam String username,@RequestParam String password) throws IOException { //为什么要throw IOException？
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
    @RequestMapping("/public/getVerifyCode")
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
    @RequestMapping("/public/regist")
    public Object regist(HttpServletResponse response,@RequestParam String cellphone,@RequestParam String password,@RequestParam String verifycode )throws IOException{
        if(!cellphone.equals("")&& !password.equals("")&&!verifycode.equals("")){ //目前用这句话来代替有效性验证
            User user=userService.regist(cellphone,password,verifycode);
            if(user!=null)
                return generateTokenResponse(response, user);

        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping("/public/loginByPhonemessage")
    public Object loginByPhoneMessage(HttpServletResponse response,@RequestParam String cellphone,@RequestParam String verifycode)throws IOException{
        if(!cellphone.equals("")&&!verifycode.equals("")){
            User user=userService.loginByPhoneMessage(cellphone,verifycode);
            if(user!=null)
                return generateTokenResponse(response, user);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    private Object generateTokenResponse(HttpServletResponse response, User user) throws IOException{
        if(user!=null){
//            System.out.println("---------------------------------"+user.toString());
            String jwt = jwtTokenUtil.generateToken(user.getUsername(),user.getUserId());
            response.setHeader("re_token",jwt);
            return new HashMap<String,String>(){{
                put("token", jwt);
            }};
        }
        throw new IOException("用户不存在");
        //return null;
    }

    @RequestMapping("/public/phoneExist")
    public Object phoneExist(HttpServletResponse response,@RequestParam String cellphone)throws IOException{
        if(!cellphone.equals("")){
            return new HashMap<String,Boolean>(){{
                put("phoneExist", userService.phoneExist(cellphone));
            }};
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping("/public/emailExist")
    public Object emailExist(HttpServletResponse response,@RequestParam String email)throws IOException{
        if(!email.equals("")){
            return new HashMap<String,Boolean>(){{
                put("emailExist", userService.emailExist(email));
            }};
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping("/public/usernameExist")
    public Object usernameExist(HttpServletResponse response,@RequestParam String username)throws IOException{
        if(!username.equals("")){
            return new HashMap<String,Boolean>(){{
                put("usernameExist", userService.usernameExist(username));
            }};
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
    @RequestMapping("/refreshToken")
    public Object refreshToken(HttpServletResponse response, HttpServletRequest request)throws IOException{
        //System.out.println("Hello World");
//        throw new IOException("SB");
        UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = request.getHeader(JwtTokenUtil.HEADER_STRING);
        return generateTokenResponse(response,userService.refreshToken(userDetails.getUsername(),token));
    }

    @RequestMapping("/changePassword")
    public Object changePassword(HttpServletResponse response,@RequestParam String cellphone,@RequestParam String password,@RequestParam String verifycode )throws IOException {
        if (!cellphone.equals("") && !password.equals("") && !verifycode.equals("")) { //目前用这句话来代替有效性验证
            User user = userService.changePassword(cellphone, password, verifycode);
            if (user != null) {
                jwtTokenUtil.delTokenFromRedisByUid(user.getUserId());
                return generateTokenResponse(response, user);
            }
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
