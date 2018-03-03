package com.wukong.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 每次密码都不一样，但是只要用 mathches就可以得到结果
 * 今后可以在这个类里面，修改自己的加密方法
 */
public class MyEncoder {

    private static BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();

    public static void main(String[] args) {
        //这里用来测试
        String[] strs={"admin","user1","123abcdefg@13456","12345678901234567890"};
        for (String s:strs) {
            String encodeStr = encoder.encode(s);
            System.out.println(s+"= [" + encodeStr + "]" + encodeStr.length());
        }
        String adminStr="$2a$10$iWIebpXWvbLyu4jYaDthdOfGcuQ99IgQBTkizHvVn6YwO94qjN9vq";
        System.out.println("args = [" + encoder.matches("admin",adminStr) + "]");
    }


    /**
     *
     * @param rawPassword  明文
     * @return 得到加密的密码
     */
    public static String encoder(String rawPassword){
        //进行加密测试
        return encoder.encode(rawPassword);
    }

    /**
     *
     * @param rawPassword   明文
     * @param encodedPassword  已经加密好密码
     * @return  返回 true与密码相同， false不同
     */
    public static boolean matches(String rawPassword, String encodedPassword){
        //进行加密测试
        return encoder.matches(rawPassword,encodedPassword);
    }


}
