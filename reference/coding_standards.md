# 编码规范


> 目录 

* [RestController函数返回方式](#restController函数返回方式)
* [阿里java编码规范](#阿里java编码规范)




## RestController函数返回方式


当前有两种方案，大家认为那种方案比较好？

* login 函数，如果用户名与密码正确，那么返回一个json字符串{tonken:"ok"}，如果错误返回错误状态
* 两个方案的区别，是方案2抛出了一个自定义的异常,并且方案2的函数返回值不是object


> 方案1

* 思路


```java
@RequestMapping("/public/login")
public Object login(@RequestParam String username,@RequestParam String password) throws IOException { //为什么要throw IOException？

    if(username=="admin"&& password="admin") {
        return new HashMap<String,String>(){{
            put("token", "ok");
        }};
    }else {
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}

```

> 方案2

```java
@RequestMapping("/public/login")
public HashMap<String,String> login(@RequestParam String username,@RequestParam String password) throws IOException { //为什么要throw IOException？

    if(username=="admin"&& password="admin") {
        return new HashMap<String,String>(){{
            put("token", "ok");
        }};
    }else {
        return new MySecurityException(60001,"登录错误");
    }
}

```


