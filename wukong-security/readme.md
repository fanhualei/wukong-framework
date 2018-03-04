


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


    说明
    1：整合jwt，那么就绕过了spring security自己的login函数.
    2: 那么就需要自己实现username与password的校验。
    3: 通过filter判断tonken正确，并将role添加到security context
    
    @TODO
    1:完善Token的判断：token和生成的不一样;token过期了;用户后来修改密码了;账户被停用了
    2:用户表中添加最后修改密码时间
    3:撰写测试文件 
    4:完善错误提示    
    

<br>

- [x] WebSecurityConfig 注册filter 并进行配置配置
- [x] JwtTokenUtil 用来生成token与解析token    
- [x] JwtController 中实现了login 与 刷新函数 
      /author/jwt/login?username=amdin&password=admin。
- [x] JwtAuthenticationTokenFilter，fiter类，判断token是否争取，并将权限写入系统
  

<br>

> WebSecurityConfig 实现

```Java
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean()  {
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/author/jwt/login").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 添加JWT filter
        httpSecurity.addFilterBefore(authenticationTokenFilterBean()
                        , UsernamePasswordAuthenticationFilter.class);

        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }

}

```

<br>

> JwtTokenUtil 实现

    1:得到token
    2:解析token
    3:判断token是否有效
    4:tonken中保存的有：username,userid,create_date,exp_date


<br>

> JwtAuthenController 实现


```Java
@RestController
@RequestMapping("/author/jwt")
public class JwtAuthenController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Object login(HttpServletResponse response,
                        @RequestParam String username,@RequestParam String password) throws IOException {
        User user=userService.selectUserByAccount(username);
        if(user!=null && MyEncoder.matches(password,user.getPassword())) {
            JwtTokenUtil jwtTokenUtil=new JwtTokenUtil();
            String jwt = jwtTokenUtil.generateToken(username,user.getUserId());
            response.setHeader("re_token",jwt);
            return new HashMap<String,String>(){{
                put("token", jwt);
            }};
        }else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }
}

```

<br>

> JwtAuthenticationTokenFilter 实现

```Java
ublic class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    @SuppressWarnings("all")
    private UserDetailsService userDetailsService;
    @Autowired
    @SuppressWarnings("all")
    private JwtTokenUtil jwtTokenUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException
    {

        String token = request.getHeader(JwtTokenUtil.HEADER_STRING);
        if (token != null && token.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {

            String username = jwtTokenUtil.getUsernameFromToken(token);
            logger.info("checking authentication " + username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                                userDetails, null,
                                                userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}

```


## 修改记录


### 2018-3-4

    
    实现了userDetail， CustomUserDetailsService去掉了 下面的代码，直接返回了user
    //        return new org.springframework.security.core.userdetails.User(user.getUsername(),
    //                user.getPassword(), authorities);



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
    
    
    