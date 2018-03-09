# 修改日志

<br>


## 完成情况
- [x] 主从数据库
- [x] Rest Api规范
- [x] Testing 测试框架
- [x] PostMan 测试框架
- [ ] 前端框架
- [ ] 安全框架
- [ ] 使用文档
- [ ] 部署文档


## 03-3 添加了两个Dao检索函数

> UserMapper->selectUserByAccount

```Java
    @Select({
            "select",
            "*",
            "from wk_user",
            "where username = #{account} or phone=#{account} or email=#{account}"
    })
    User selectUserByAccount( String account);
```

> RoleMapper->selectRolesByUserid

```Java
    @Select({
            "select",
            "wk_role.*",
            "from wk_role",
            "join wk_user_role on wk_user_role.role_id=wk_role.role_id",
            "where wk_user_role.user_id = #{userid,jdbcType=INTEGER}",
            "order by sort"
    })
    @Results({
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="rolename", property="rolename", jdbcType=JdbcType.VARCHAR),
            @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
            @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<Role> selectRolesByUserid(Integer userid);
```


## 03-2  为了少写代码，我将SpringBoot的application提到com.wukong包下了

> 不用自动检索
    
    SpringBoot有好处，也有坏处，坏处是你必须要按照他的规则把文件放在特定目录下，不然就出现错误
    //@SpringBootApplication(scanBasePackages={"com.wukong.core","com.wukong.examples","com.wukong.security"})
    @SpringBootApplication()


> 使用了注解的样式，删除了动态sql

    <!--<dependency>-->
        <!--<groupId>org.mybatis.dynamic-sql</groupId>-->
        <!--<artifactId>mybatis-dynamic-sql</artifactId>-->
        <!--<version>1.0.0</version>-->
    <!--</dependency>-->


> TODO 看看怎么启用Mybatis的一级缓存，同时集成分页插件

## 02-25 追加了security与redis部分
    
    1、把核心模块追加了
    2、TODO
       2.1、在testNG中加入Token，不然运行不了
       2.2、将权限信息保存到数据库中，便于调整
       2.3、将Token保存到redis中,这样可以提高效率
            最好做一个开关，可以保存到内存也可以保存到redis中
 
    
>今天有重大收获,通过拦截器自动给testNg追加tonken，!!!

    1、追加了BasicJwtInterceptor，每个request追加token
    2、HelloControllerTests 中追加@BeforeMethod，初始化上面的拦截器           
            
>HelloControllerTests 相关代码
        
```Java
@BeforeMethod
public void getToken(){
    String url="/author/jwt/login?username=admin&password=admin";
    ResponseEntity<Map> entity = this.restTemplate.getForEntity(url, Map.class);
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    Map<String, String> returnMap=entity.getBody();
    String token=returnMap.get("token");
    BasicJwtInterceptor basicJwtInterceptor= new BasicJwtInterceptor(token);
    restTemplate.getRestTemplate().getInterceptors().add(basicJwtInterceptor);
}
```    

>BasicJwtInterceptor 相关代码

```Java
public BasicJwtInterceptor(String token) {
    Assert.hasLength(token, "token must not be empty");
    this.token = token;
}


@Override
public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                    ClientHttpRequestExecution execution) throws IOException {
    request.getHeaders().add("Authorization", token);
    return execution.execute(request, body);
}
```
      
## 02-19 完成了service代码生成的主要工作
     
     1、熟悉了javaparse的工作原理
     2、书写了Mybatis代码生成的原理，可以自动的添加table，并且可以追加内容进行相关代码的生成。
     3、原先想将配置数据用springboot来配置，后来发现不用了。先读取xml文件，然后调用数据库相关文件。
 
 

 

## 02-16 做了拦截器，可以拦截service上指定的数据库

      1、 @DatasourceAnno(name=master) 或 @DatasourceAnno(name=slave) 后不再根据函数名来判断获取的数据库
      2、maven 引用了 spring-boot-starter-aop ，就不用引用aspectjweaver 
      3、wukong-generator 用来生成相关的代码
      
      todo
      1、代码生成器
      2、安全框架
      3、主键不会返回
      4、生成class类的时候，要把前缀给去掉



## 02-15 做了主从数据库，实现了多年的心愿。 

   主要工作
   
      1、实现了 1个master库， n个slave库。1个master库， 3个slave库，出现select时，从slave库上轮训选择要查询的库
      2、集成了阿里的druid数据源，spring 推荐的是hikariCP ，速度快稳定。 druid太复杂，但是容易监控sql语句
      3、输入https://localhost:8443/druid/， 能看到druid的监控页面
      4、解决了springBoot跨包的问题
        @SpringBootApplication(scanBasePackages={"com.wukong.core","com.wukong.examples"})
      
  
      
   todo 
   
      ~~1、@todo 撰写一个标签  可以制定从主数据库上读取数据~~
      2、@todo 今后可以动态的配置slave数量
      3、@todo 撰写unitTest保证这部分数据没有问题。
      4、@todo 拦截器可以配置到配置文件中
      ~~5、@todo 将代码移到core目录中~~
      6、@todo 升级druid到1.8.1版本，看看好用不用。 估计要同时升级到springboot2.0
      ~~7、@todo 使用spring的aop，而不是老的apache的aop~~
      ~~8、@todo service方法中只要是:insert update delete add  remove 就表示非查询语句~~
      
   思考      
      
      到底拦截器做在DAO(sql)上好 还是在service上好
      service的好处
      1、数据源这部分代码简单。
      2、今后可以重用Dao的这部分代码？ 但是想想，这条不成立。
      坏处：
      1、如果有人从controler这部分调用dao，就拦截不住。
 
 
## 02-14 集成了mybatis，今天最大的收获是，别把没看懂的代码提交到工程中，会影响其他程序的。

      1、集成了mybatis,按照官网的，越简单越好
      2、testNG中进行了测试，分别测试了Dao与Service，今后只用测试Service
      3、引入了这个aspectj包太老了，看看有没有其他更好的
      ~~4、@todo mybatis多数据源与动态切换，官方应该有特别简单例子，这样符合springboot的特点
      ，别上网看那些代码，写的太复杂  ~~     
 
 
 
## 02-13 做了http rest的代码 

      1、添加了文件上传 下载 
      2、post json代码  
      3、http2https重新定向  
      4、testng参数测试
      5、@todo testng 传递对象测试 excel表测试 随机数测试    
    
     
## 02-12 将原先代码都删除了，实现了下面的功能
 
       1、多pom
       2、https
       3、testNG测试
       4、rest接口    