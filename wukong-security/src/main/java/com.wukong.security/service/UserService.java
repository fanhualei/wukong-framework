package com.wukong.security.service;

import com.wukong.security.dao.UserRoleMapper;
import com.wukong.security.model.User;
import com.wukong.security.model.UserExample;
import com.wukong.security.dao.UserMapper;

import com.wukong.security.model.UserRole;
import com.wukong.security.util.JwtTokenUtil;
import com.wukong.security.util.MyEncoder;
import org.apache.ibatis.annotations.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class UserService {
    //利用SpingIOC注入DAO变量
    @Autowired
    private UserMapper userMapper;

    @Transactional(readOnly=true)
    public long countByExample(UserExample example) {
        return userMapper.countByExample(example);
    }

    @Transactional
    public int deleteByExample(UserExample example) {
        return userMapper.deleteByExample(example);
    }

    @Transactional
    public int deleteByPrimaryKey(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Transactional
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Transactional
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Transactional(readOnly=true)
    public List<User> selectByExample(UserExample example) {
        return userMapper.selectByExample(example);
    }

    @Transactional(readOnly=true)
    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Transactional
    public int updateByExampleSelective(@Param("record") User record,@Param("example") UserExample example) {
        return userMapper.updateByExampleSelective(record,example);
    }

    @Transactional
    public int updateByExample(@Param("record") User record,@Param("example") UserExample example) {
        return userMapper.updateByExample(record,example);
    }

    @Transactional
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }


    public User selectUserByAccount( String account){
        if(StringUtils.isEmpty(account)){
            return null;
        }
        return userMapper.selectUserByAccount(account);
    }

    //以下为安全模块需要的service
    //部分字符串硬编码进去，比较dirty
    @Autowired
    private StringRedisTemplate template;
    //这个hashMap应该由一次查表得出放入内存中
    private HashMap<String,Integer>roleMap=new HashMap<String, Integer>() {
        {
            put("ADMIN_ROLE",1);
            put("USER_ROLE",2);
        }
    };

    public String getVerifyCode(String cellphone)throws IOException{
        //TODO 此处先通过phone字段查询用户是否存在
        //if(userMapper.isUserExistByCellphone(cellphone)==1) return null;
        String verifyCode=String.valueOf((int)((Math.random()*9+1)*100000));//随机生成6位验证码
        ValueOperations<String, String> ops = template.opsForValue();
        String key="wukong:security:verifycode:"+cellphone;
        ops.set(key,verifyCode);
        template.expire(key,5,TimeUnit.MINUTES);//实际应用中应该用发送短信的业务逻辑来取代返回验证码
        return verifyCode;

    }

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Transactional
    public User regist(String cellphone,String password,String verifycode)throws IOException {
        ValueOperations<String, String> ops = template.opsForValue();
        String key="wukong:security:verifycode:"+cellphone;
        if(template.hasKey(key)){
            if(verifycode.equals(ops.get(key))){ //verifycode之前已经验证非空故不会出错
                template.delete(key);
                if(userMapper.isUserExistByCellphone(cellphone)==1) throw new IOException("用户已注册");//return null;
                User user=new User();
                user.setUsername("(phone)"+cellphone);
                user.setPhone(cellphone);
                user.setPassword(MyEncoder.encoder(password));
                user.setEnabled(true);
                user.setPwresetdate(new Date());
//                user.setRoleId(roleMap.get("USER_ROLE"));
                userMapper.insert(user);
                user=userMapper.selectByCellphone(cellphone);
                UserRole userRole=new UserRole();
                userRole.setUserId(user.getUserId());
                userRole.setRoleId(roleMap.get("USER_ROLE"));
                userRoleMapper.insert(userRole);


                return user;

            }
        }
        throw new IOException("验证码错误");
        //log.info("return null");
//        return null;
    }

    @Transactional
    public User loginByPhoneMessage(String cellphone,String verifycode)throws IOException{
        ValueOperations<String, String> ops = template.opsForValue();
        String key="wukong:security:verifycode:"+cellphone;
        if(template.hasKey(key)){
            template.delete(key);
            return userMapper.selectByCellphone(cellphone);
        }
        throw new IOException("验证码错误");
    }

    @Transactional
    public boolean phoneExist(String cellphone)throws IOException{
        return userMapper.isUserExistByCellphone(cellphone) > 0;
    }

    @Transactional
    public boolean usernameExist(String username)throws IOException{
        return userMapper.isUserExistByUsername(username) > 0;
    }

    @Transactional
    public boolean emailExist(String email)throws IOException{
        return userMapper.isUserExistByEmail(email) > 0;
    }
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    public User refreshToken(String username,String token)throws IOException{
        jwtTokenUtil.delTokenFromredisByToken(token);
        return userMapper.selectUserByAccount(username);
    }
    @Transactional
    public User changePassword(String cellphone,String password,String verifycode)throws IOException {
        ValueOperations<String, String> ops = template.opsForValue();
        String key = "wukong:security:verifycode:" + cellphone;
        if (template.hasKey(key)) {
            if (verifycode.equals(ops.get(key))) { //verifycode之前已经验证非空故不会出错
                template.delete(key);
                User user = userMapper.selectByCellphone(cellphone);
                if (user == null) return null;
                userMapper.updatePasswordbyUserid(user.getUserId(), MyEncoder.encoder(password));
                return user;

            }throw new IOException("验证码错误");

        }
        throw new IOException("验证码不存在");
        //return null;
    }
}