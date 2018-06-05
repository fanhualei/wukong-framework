# Spirng Boot中文乱码问题


# 请不要看这篇文章


> 目录

* [常见的乱码问题](#常见的乱码问题)
* [解决方案](#解决方案)
* [参考文档](#参考文档)



## 常见的乱码问题


问题编号 | 描述 | 解决方案  | 
--------- | --------|  --------|
1   | controller输出输入乱码             | [解决方案](#controller输出输入乱码)  |
2   | 数据库保存与读取乱码                |  需要做实验  |
3   | 多语言文件读取乱码                  |  未发现乱码 |




## 解决方案

### controller输出输入乱码

> 测试方法

    http://127.0.0.1:8080/changeLanguage?lang=中文
    http://127.0.0.1:8080/welcome
    以上两个链接，只要返回的是中文，就表示成功



#### SpringBoot中配置


##### 下面的代码作废了，因为使用utf-8的json转换机制
在core工程中，追加了EncodingConfig.java配置类

```java
@Configuration
public class EncodingConfig extends WebMvcConfigurationSupport {
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }
}
```


##### 新的转换机制

```java
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
```



#### tomcat中设置

    Tomcat的配置文件server.xml
    <Connector port="8080" protocol="HTTP/1.1"    
               connectionTimeout="20000"    
               redirectPort="8443"    
               URIEncoding="UTF-8"/>  


## 参考文档

* [Spring Boot 中文乱码问题解决方案汇总](https://www.jianshu.com/p/718826aee249)

