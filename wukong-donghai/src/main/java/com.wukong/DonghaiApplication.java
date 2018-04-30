package com.wukong;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@EnableCaching

// next is run debug

//public class DonghaiApplication implements CommandLineRunner {
//
//    @Override
//    public void run(String... args) throws Exception {
//
//    }
//
//
//    public static void main(String[] args) {
//        SpringApplication.run(DonghaiApplication.class, args);
//
//    }
//
//}


// next is run tomcat 下面的代码是为了部署到tomact下

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
