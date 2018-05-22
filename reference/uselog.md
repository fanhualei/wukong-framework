# 如何使用log

> 目录

* [代码中加入log](#代码中加入log)
* [配置log输出](#配置log输出)
* [日志级别的使用](#日志级别的使用)

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



## 日志级别的使用

> DEBUG、INFO、WARN、ERROR和FATAL

* 最常见的是 debug info error
* 在服务器上，一般会将error单独输出的到一个文件


级别 | 使用场景|
--------- | --------|
DEBUG  | 当你想用system.out.print时，可以用这个 |
INFO  | 业务相关的记录，例如我想在每个订单生成时有日志，以便今后通过日志分析 |
ERROR | 被try catch及自己判断的错误，都应该输出错误。不严重错误，可以使用warn    | 


