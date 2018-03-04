package com.wukong.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

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

    //生成Token
    public String generateToken(String userAccount,Integer userid) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(USERNAME, userAccount);
        claims.put(CREATED, new Date());
        claims.put(USERID,userid);

        return generateToken(claims);
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
     * @param lastPasswordReset  最后修改密码的时间
     * @return
     */
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    //判断token是否有限，有很多方法，我可以把生成的token放入到缓存进行判断
    public Boolean validateToken(String token, UserDetails userDetails) {
          return true;
//        SysUser user = (SysUser) userDetails;
//        final String username = getUsernameFromToken(token);
//        final Date created = getCreatedDateFromToken(token);
////        final Date expiration = getExpirationDateFromToken(token);
//        return (
//                username.equals(user.getUsername())
//                        && !isTokenExpired(token)
//                        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
    }


    public static void main(String[] args) {
        JwtTokenUtil jwtTokenUtil=new JwtTokenUtil();
        String token =  jwtTokenUtil.generateToken("username1",8);


    }


}
