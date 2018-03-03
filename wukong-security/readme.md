


#安全框架基础概念
---

<br>

## SpringBoot 安全实现
`撰写两个类就行了`

- [x] 实现UserDetailsService，读取自己的表结构
- [x] 实现WebSecurityConfigurerAdapter，进行配置
- [ ] 多种加密方法，springboot默认就是，今后谁去Todo
- [x] 不同ROLE的控制方法，注解或程序中写。 TODO外部配置文件

<br>

>说明

    *spring 自己实现了login界面
    *spring 推荐使用BCrypt加密，BCrypt每次加密的都不一样
    *spring 兼容多种加密方法，可以使用"MD5"来区分不同的加密方法
            http://wiselyman.iteye.com/blog/2410052
    *为了初始化脚本中的数据，我写了一个MyEncoder类得到BCrypt密码
            
<br>

> CustomUserDetailsService 实现

```Java
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.selectUserByAccount(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<SimpleGrantedAuthority> authorities = loadGrantedAuthorityByUserid(user.getUserId());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorities);
    }

    //得到用户的权限 这个是我自己做的
    private List<SimpleGrantedAuthority> loadGrantedAuthorityByUserid(Integer userid){
        List<Role> roles=roleService.selectRolesByUserid(userid);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getRolename()));});
        return authorities;
    }
}

```
<br>

> WebSecurityConfig 实现

```Java
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    //返回自己的 UserDetailsService
    UserDetailsService customUserService(){ return new CustomUserDetailsService(); }

    @Override
    // 设置自己的UserDetailsService ，以及使用BCrypt进行密码的hash
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    //装载BCrypt密码编码器
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin()
                //设置默认登录成功跳转页面
                .defaultSuccessUrl("/hello").permitAll();
    }

}
```

<br>

> ROLE权限控制

>> controller 注解

```Java

    @RequestMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')  ")
    public String hello() {
        return "Hello World";
    }
    
```

>> 代码中实现 WebSecurityConfig类中

`antMatchers 一定要紧跟在authorizeRequests()后边`
`hasAnyRole 可以使用多个`
`("ADMIN") 与controller不同，不能写成ROLE_ADMIN`


```Java
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello/json").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().defaultSuccessUrl("/hello").permitAll()//设置默认登录成功跳转页面
                ;
    }

```

<br>

## JWT 整合

<br>

## 其他

<br>

### 参考网页

http://blog.csdn.net/linzhiqiang0316/article/details/78358907

纯的security的讲解
http://blog.csdn.net/u012702547/article/details/54319508

<br>

### 要做的事情

- [ ] 修改dao，完成selectUserByName,selectRolesByUserId查询
- [ ] 生成UserDetail来进行测试
- [ ] 在拦截器中，拦截相应的URL
- [ ] 部署文档


<br>


### autor2概念

    grant_type=password&username=johndoe&password=A3ddj3w
    grant_type=client_credentials
    grant_type=refresh_token

<br>
    
### 访问路径

    /oauth/2.0/authorize
    /oauth/2.0/token
    /oauth/2.0/refresh_token
    
    /oauth/jwt/login       

<br>

### 参考文件

    http://blog.csdn.net/u012373815/article/details/54633046
    http://wiki.jikexueyuan.com/project/spring-security/first-experience.html

<br>
    
### 撰写思路

    jwt:见另外一个code的例子
    spring认证，只用到user和role。需要添加UserDetailsService类，并在config中做注解
    
    
    