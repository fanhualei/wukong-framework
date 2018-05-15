package com.wukong.core.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.config.JCacheConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

//
@Configuration
@EnableCaching
public class RedisConfig extends JCacheConfigurerSupport{
    /**
     *  设置 redis 数据默认过期时间
     *  设置@cacheable 序列化方式
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration=configuration.entryTtl(Duration.ofDays(365l));//开发设置为1年
        return configuration;
    }
}
