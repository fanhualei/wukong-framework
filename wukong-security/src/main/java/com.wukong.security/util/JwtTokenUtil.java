package com.wukong.security.util;

import com.wukong.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Component
public class JwtTokenUtil {


    public static final String SECRET = "ThisIsASecret";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String USERID = "userid";
    public static final String USERNAME = "username";
    public static final String CREATED = "created";

    //最小单位为秒 1L*10=10秒  1L*60=1分钟  1L*60*10=10分钟 1L*60*60=1小时
    private Long expiration=1L*60*60*24*300; //为了测试现在设置成1年的时间

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = (String) claims.get(USERNAME);
        } catch (Exception e) {
            username = null;
        }
        return username;
    }


    public Integer getUseridFromToken(String token) {
        Integer userid;
        try {
            final Claims claims = getClaimsFromToken(token);
            userid = (Integer) claims.get(USERID);
        } catch (Exception e) {
            userid = null;
        }
        return userid;
    }


    //得到创建的时间
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    //得到过期时间
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    //得到一个数据
    public  Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    //得到过期时间
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    //判断 是否过期
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //判断token创建时间，是否在修改密码以后
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }


    @Autowired
    private StringRedisTemplate template;
    private final static String REDIS_TOKEN="wukong:security:token:";
    //将数据设置到缓存中
    private void setTokenToReddis(Integer userid,String token,Date createDate){
        ValueOperations<String, String> ops = template.opsForValue();
        String key=REDIS_TOKEN+userid+":"+getDateStr(createDate);
        ops.set(key,token);
        template.expire(key,expiration,TimeUnit.SECONDS);
    }

    private void delTokenFromRedisByUid(Integer userid){
        String key=REDIS_TOKEN+userid+":*";
        System.out.println(key);
        Set<String> keySet=template.keys(key);
        template.delete(keySet);
    }


    //从缓存中得到token
    private String getTokenFromRedis(Integer userid,Date createDate){
        //判断如果有缓存，就返回缓存
        ValueOperations<String, String> ops = template.opsForValue();
        String key=REDIS_TOKEN+userid+":"+getDateStr(createDate);
        if(template.hasKey(key)){
            return ops.get(key);
        }
        return "";
    }

    //得到秒一级的字符串
    private String getDateStr(Date createDate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String str=sdf.format(createDate);
        return str;
    }

    //生成Token
    public String generateToken(String userAccount,Integer userid) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(USERNAME, userAccount);
        Date createDate=new Date();
        claims.put(CREATED, createDate);
        claims.put(USERID,userid);
        String token =generateToken(claims);

        //这个是登录函数，只要登录，就设置缓存
        setTokenToReddis(userid,token,createDate);

        return token;
    }



    private String generateToken(Map<String, Object> claims){
        String jwtStr =Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        return TOKEN_PREFIX+ " " + jwtStr;
    }


    /**
     * 是否可以刷新token
     * @param token
     * @return
     */
    public Boolean canTokenBeRefreshed(String token) {
//        final Date created = getCreatedDateFromToken(token);
//        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
//                && !isTokenExpired(token);
        String key=REDIS_TOKEN+getUseridFromToken(token)+":"+getDateStr(getCreatedDateFromToken(token));
        return template.hasKey(key);
    }

    //@todo 要重新设置缓存
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            if (!canTokenBeRefreshed(token)) throw new  Exception(); //TODO :应该写为单独的异常类
            final Claims claims = getClaimsFromToken(token);
            claims.put(CREATED, new Date());
            refreshedToken = generateToken(claims);

            //这个是登录函数，只要登录，就设置缓存
            Integer userid =(Integer)claims.get(USERID);
            Date createDate=(Date)claims.get(CREATED);
            setTokenToReddis(userid,refreshedToken,createDate);

        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }
    // @TODO 不能访问一次就查询数据库一次，不能只凭借用户名进行验证，应该把token放入到redis中
    //判断token是否有限，有很多方法，我可以把生成的token放入到缓存进行判断
    //token 的时效由redis控制，管理
    public Boolean validateToken(String token) {
        final Date created = getCreatedDateFromToken(token);
        final Integer userid=getUseridFromToken(token);
        if(userid==null||created==null){
            return false;
        }
        String tokenFromRedis=getTokenFromRedis(userid,created);
        if(!token.equals(tokenFromRedis)){
            return false;//与服务中存储的不一致,token被修改
        }
        return true;

//        CustomUserDetails user = (CustomUserDetails) userDetails;
//
//
//        final Date created = getCreatedDateFromToken(token);
//
//        String tokenFromRedis=getTokenFromRedis(user.getUserId(),created);
//        if(!token.equals(tokenFromRedis)){
//            return false;//与服务中存储的不一致,token被修改
//        }
//
//        if(isTokenExpired(token)){
//            return false; //token 过期
//        }
//
//
//        if(isCreatedBeforeLastPasswordReset(created, user.getPwresetdate())){
//            return false; //用户修改过密码，需要重新登录
//        }
//
//        if(!user.isEnabled()){
//            return false; //用户的账户被停用
//        }
//
//        return true;
    }


}
