## 开发技巧技巧


<br>

> 目录


* [无法自动装载](#无法自动装载) <br>
* [多module无法引用](#多module无法引用)  <br>
* [关闭多数据源](#关闭多数据源) <br>
* [关闭security认证](#关闭security认证)  <br>
    

<br>    
    
###  无法自动装载

`@Autowired`

>原因1(使用new来生成对象)

    自己new的对象，里面包含@Autowired 就无法装载
    例如：JwtTokenUtil jwtTokenUtil=new JwtTokenUtil();
    那么JwtTokenUtil 里面包含@Autowired 就无法装载
    
>原因2(没有找到包，或添加标签)

    如果是 @Component @Service 等标签，就能自动装载
        
<br>    
    
### 多module无法引用


> 解决方案

    1:将 Application.Java的包路径提高到最高
      这样springBoot会自动检索目录下所有子文件夹，进行加载。
    2:手工指定要引入的数据包
    @SpringBootApplication(scanBasePackages={"com.wukong.core","com.wukong.examples","com.wukong.security"})
   

<br>
   
### 关闭多数据源

>3.1 maven中<font color="red">注释</font>掉druid依赖

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.6</version>
</dependency>
```
        
>3.2 修改application.properties，选择单一数据源

```properties
#启用单一数据源
spring.profiles.active=sdb

#启用多数据源
#spring.profiles.active=mdb
```

>3.3 将com.wukong.core.datasource.druid类注释掉

    除了DataSourceKey类
    其他类，打开，ctrl+A,ctrl+/  


### 关闭security认证
