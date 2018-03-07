## 如何使用log

> 关键点

    1: 代码中加入log
    2: 配置log输出

<br>


### 1: 代码中加入log

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

### 2: 配置log输出