package com.wukong.core.config;


import org.springframework.context.annotation.Import;




@Import({
        Http2HttpsConfig.class    //Http转https
})
/**
 * 封装了core中所有的config，便与其他module进行引用
 */
public class AllCoreConfig {
}
