# redis的使用

> 关键点

    1: redis使用规范
    2: 在代码中使用redis
    3：通过注解使用redis
    4:redis安装
    
<br>   
    
## 1: redis key 命名规范

> 非DB key命名规范

    key = wukong:模块名称:缓存名字:主键
        例如tonken缓存的命名 key = wukong:security:token:{userid}
        
> DB层查询key命名规范

    1:保存对象
        key= 表名:主键值  value=对象
        例如：user:1=userObject     
    2:保存表字段的明细
        key=表名:主键字段名:主键数:字段名  value=字段值
        例如：user:userid:1:username="amdin"
             user:userid:1:email="fanhl@189.cn"
        检索：
             keys user:userid:1* 可以查出有几个属性
      
       
<br>             
                  
## 2: 在代码中使用redis

>例如：JwtTokenUtil.java 直接使用spring RedisTemplate进行操作

```java
    @Autowired
    private StringRedisTemplate template;
    private final static String REDIS_TOKEN="wukong:security:token:";
    //将数据设置到缓存中
    private void setTokenToReddis(Integer userid,String token,Date createDate){
        ValueOperations<String, String> ops = template.opsForValue();
        String key=REDIS_TOKEN+userid+":"+getDateStr(createDate);
        ops.set(key,token);
    }

    //从缓存中得到token
    private String getTokenFromRedis(Integer userid,Date createDate){
        //判断如果有缓存，就返回缓存
        ValueOperations<String, String> ops = template.opsForValue();
        String key=REDIS_TOKEN+userid+":"+getDateStr(createDate);
        if(template.hasKey(key)){
            return ops.get(key);
        }
        return "";
    }

```
[token设计说明](token.md)<br>

<br>

##  3: spring注解使用redis



>3.1:工程追加注解 @EnableCaching

```java
@SpringBootApplication()
@EnableCaching
public class DemoApplication implements CommandLineRunner {
    
}
```
<br>

>3.2:类 追加 @CacheConfig

```java
@Service
@Slf4j
@CacheConfig(cacheNames="wukong:role")
public class RoleService {
    
}
```
<br>

>3.3:方法上 追加 @Cacheable

```java
// @Cacheable 会先查询缓存，如果缓存中存在，则不执行方法
@Cacheable(key="#p0")
public List<Role> selectRolesByUserid(Integer userid){
    return roleMapper.selectRolesByUserid(userid);
}
```
<br>

>3.4:其他

        Mybatis中使用redis        
        http://blog.csdn.net/dream_broken/article/details/72602218 


<br>

## 4: redis安装

    安装
        sudo apt-get install redis-server
    检查状态
        sudo /etc/init.d/redis-server status
    登录redis即获得帮助
        redis-cli
        help    
    基本使用命令
        查看所有的key列表  keys *
        增加一条记录key1  set key1 "hello"
        得到数据         get key1
        增加一条数字记录  set key2 1
        让数字自增       INCR key2
        删除一个        del key1   
        删除所有数据     flushall
        
[redis使用说明](https://www.cnblogs.com/zongfa/p/7808807.html  "打开网页")        