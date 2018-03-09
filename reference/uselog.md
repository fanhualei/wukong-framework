# 如何使用log

> 目录

* [代码中加入log](#代码中加入log)
* [配置log输出](#配置log输出)

<br>


## 代码中加入log

>使用lombok，一定要用@Slf4j

```java
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class UserService {
    public void loadUserByUsername(String username) {
        log.debug("得到用户名:"+username);
    }
}

```

<br>

## 配置log输出

```properties
#日志
#输出dao
logging.level.com.wukong.security.dao=DEBUG

#springframework.web下所有的输出
logging.level.org.springframework.web=DEBUG 
```