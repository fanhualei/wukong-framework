package com.wukong.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
* 设置编码规则，由于使用json，并且这个json是用utf8来处理的，所以中文是ok的，
 * 另外副作用是，所以的，包含string都转了字符串
* @author fanhl
*/
@Configuration
public class EncodingConfig extends WebMvcConfigurationSupport {

    /**
     * 这个函数的目的是将json放到最前边
     * 因为controller返回String类型的时候，是StringHttpMessageConverter
     * 所以在统一返回接口中出现错误，
     * @see com.wukong.core.handler.ResponseResultHandler
     * @param converters
     */

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(0,new MappingJackson2HttpMessageConverter());
    }



}
