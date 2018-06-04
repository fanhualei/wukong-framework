package com.wukong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;


/**
 *
 * @author fanhl
 */
@SpringBootApplication()
@EnableCaching
public class DonghaiApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 设置启动类,用于独立tomcat运行的入口
        return builder.sources(DonghaiApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DonghaiApplication.class, args);

    }

}
