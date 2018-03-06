## SpringBoot使用小技巧


<br>

> 关键点

    1: @Autowired 无法装载
    2: 多module无法引用
    

<br>    
    
###  @Autowired 无法装载


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
        