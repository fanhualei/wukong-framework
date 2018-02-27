


基础概念
---

### autor2概念

    grant_type=password&username=johndoe&password=A3ddj3w
    grant_type=client_credentials
    grant_type=refresh_token
    
### 访问路径

    /oauth/2.0/authorize
    /oauth/2.0/token
    /oauth/2.0/refresh_token
    
    /oauth/jwt/login       


### 参考文件

    http://blog.csdn.net/u012373815/article/details/54633046
    http://wiki.jikexueyuan.com/project/spring-security/first-experience.html
    
### 撰写思路

    jwt:见另外一个code的例子
    spring认证，只用到user和role。需要添加UserDetailsService类，并在config中做注解
    
    
    