## 开发技巧


<br>

> 目录


* [无法自动装载](#无法自动装载) <br>
* [多module无法引用](#多module无法引用)  <br>
* [关闭多数据源](#关闭多数据源) <br>
* [关闭security认证](#关闭security认证)  <br>
* [升级到SpringBoot2注意事项](#升级到SpringBoot2注意事项)  <br>
* [多module引用关系](#多module引用关系)  <br>
* [module单独测试](#module单独测试)  <br>
* [yml文件编辑技巧](#yml文件编辑技巧)<bt>
    



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

>1 maven中注释掉druid依赖

```xml
<!--<dependency>-->
    <!--<groupId>com.alibaba</groupId>-->
    <!--<artifactId>druid-spring-boot-starter</artifactId>-->
    <!--<version>1.1.6</version>-->
<!--</dependency>-->
```
        
>2 修改application.properties，选择单一数据源

```properties
#启用单一数据源
spring.profiles.active=sdb

#启用多数据源
#spring.profiles.active=mdb
```

>3 将com.wukong.core.datasource.druid类注释掉

    除了DataSourceKey类
    其他类，打开，ctrl+A,ctrl+/  

<br>

### 关闭security认证

>1 注释掉security 依赖

```xml
<!--安全方面-->
<!--<dependency>-->
    <!--<groupId>io.jsonwebtoken</groupId>-->
    <!--<artifactId>jjwt</artifactId>-->
    <!--<version>0.7.0</version>-->
<!--</dependency>-->
<!--<dependency>-->
    <!--<groupId>org.springframework.boot</groupId>-->
    <!--<artifactId>spring-boot-starter-security</artifactId>-->
<!--</dependency>-->
```

>2 注释掉wukong security 依赖

```xml

<!--<dependency>-->
    <!--<groupId>com.runzhichina.wukong</groupId>-->
    <!--<artifactId>wukong-security</artifactId>-->
    <!--<version>1.1.RELEASE</version>-->
<!--</dependency>-->

```


>3 编译工程，看有没有报错

引用security依赖的地方会报错，理论讲应该是弱耦合
例如@PreAuthorize

```java
    @ApiOperation(value="得到名称", notes="")
    //    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')  ")
    @ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String")
    @RequestMapping("/info")
    public Map<String, String> getInfo(@RequestParam String name) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        return map;
    }

```

<br>

### 升级到SpringBoot2注意事项


>遇到问题

    1:原先的Http转Https功能不能用了。
      因为EmbeddedServletContainer被重新定义成WebServer


>参考网址

[Spring Boot 2.0 新特性和发展方向](http://blog.csdn.net/yalishadaa/article/details/79400916)


<br>


### 多module引用关系


* wukong-framework( parent=null modules=All dependencies=null)
    * wukong-parent 引用了项目使用中常用的包           
        * parent=spring-boot
        * modules=null
        * dependencies=工程用到的大部分功能
    * wukong-core   实现了数据库链接，https等功能
        * parent=wukong-parent
        * modules=null
        * dependencies=null
    * wukong-security 实现用户登录 jwt例子
        * parent=wukong-parent
        * modules=null
        * dependencies=wukong-core
    * wukong-examples 一个例子引用了core与security
        * parent=wukong-parent
        * modules=null
        * dependencies=wukong-core,wukong-security
    * wukong-generator 代码生成器
        * parent=spring-boot
        * modules=null
        * dependencies=null
    
    
<br>

### module单独测试

> 问题描述

    由于core与security是基础模块，没有自己的Spring主程序，
    以前只能放到examples中测试security的dao代码，这样代码耦合性不高
    
    
> 解决方法

    1:在test下建立一个TestApplication的主程序
    2:在test resources建立一个application.properties文件，并配置相关信息
    
    
<br>

### yml文件编辑技巧

当前不建议使用，因为添加注释不方便

> [在线properties转yml](https://www.bejson.com/devtools/properties2yaml/)


> 使用技巧 [更多](http://blog.csdn.net/zhengxiangwen/article/details/70042514)

    * 在yaml里，用#做注释
    * yaml不支持制表符tab缩进，请使用空格缩进
    * 如果参数是以空格开始或结束的字符串，应使用单引号把他包进来。
    * 如果一个字符串参数包含特殊字符，也要用单引号包起来。
    * 每个冒号后面一定要有一个空格
    * yaml中，空值可以用null或~表示
    * 数组用“[]”包括起来，hash用“{}”来包括
      
> 例子

```yaml
bat:    
  website: {baidu: http://www.baidu.com,qq: http://www.qq.com,ali: [http://www.taobao.com, http://www.tmall.com]}  
  ceo: { yanhongli:李彦宏,huatengma:麻花疼,yunma:马云}  
```
    
