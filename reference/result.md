# API返回格式与全局异常捕获





> 目录

* [注意事项](#注意事项)
* [API返回格式](#api返回格式)
* [全局异常捕获](#全局异常捕获)
    * [参数校验异常](#参数校验异常)
    * [其他系统错误](#其他系统错误)
    * [自定义异常](#自定义异常)

<br>

## 注意事项

以下信息十分重要

> @ResponseResult 的适用范围
* 不建议添加到Class上
* 添加到函数上,不能返回String类型

> 不完善的地方
* 当参数不完整的情况下,返回成功状态 code=1





## API返回格式

> 添加`@ResponseResult`注解,统一返回格式

```java
@RequestMapping("/success1")
public City success1() {
    City city = new City(1,"city1","001");
    return city;
}

@RequestMapping("/success2")
@ResponseResult
public City success2() {
    City city = new City(1,"city1","001");
    return city;
}
```

[详细代码](../wukong-examples/src/main/java/com/wukong/examples/controller/ResultController.java) 

<br>

> 添加注解:https://localhost:8443/result/success2

    {"code":0,"msg":"success","data":{"id":1,"name":"city1","code":"001"}}


> 不加注解:https://localhost:8443/result/success1

    {"id":1,"name":"city1","code":"001"}




<br>


## 全局异常捕获

<br>

### 参数校验异常

通过下面地址访问:http://localhost:8080/result/para1?name=12&email=189.cn&cellPhone=123

> 例子代码

```java
/**
 * 测试参数异常
 */
@RequestMapping("/para1")
public String para1(@RequestParam @Valid @Length(min = 6,max = 50)  String name
        ,@RequestParam @Valid @Email String email
        ,@RequestParam String cellPhone
) {
    return "name:"+name+";"+"email:"+email+";"+"cellPhone:"+cellPhone+";";
}

@RequestMapping("/para2")
@ResponseResult
public String para2(@RequestParam @Valid @Length(min = 6,max = 50) String name
        ,@RequestParam  @Valid @Email String email
        ,@RequestParam  String cellPhone
) {
    return "name:"+name+";"+"email:"+email+";"+"cellPhone:"+cellPhone+";";
}
```



> 没有添加@ResponseResult的返回结果

```json
{
    "status": 400,
    "error": "Bad Request",
    "message": "参数无效",
    "code": 10001,
    "path": "/result/para1",
    "exception": "javax.validation.ConstraintViolationException",
    "errors": [
        {
            "fieldName": "email",
            "message": "不是一个合法的电子邮件地址"
        },
        {
            "fieldName": "name",
            "message": "长度需要在6和50之间"
        }
    ],
    "timestamp": "2018-03-11T13:34:30.611+0000"
}
```


> 添加@ResponseResult的返回结果

```json
{
    "code": 10001,
    "message": "参数无效",
    "data": [
        {
            "fieldName": "email",
            "message": "不是一个合法的电子邮件地址"
        },
        {
            "fieldName": "name",
            "message": "长度需要在6和50之间"
        }
    ]
}
```

<br>

###  其他系统错误

> 没有添加@ResponseResult的返回结果

```json
{
    "status": 500,
    "error": "Internal Server Error",
    "message": "系统繁忙，请稍后重试",
    "code": 40001,
    "path": "/result/exception1",
    "exception": "java.lang.RuntimeException",
    "errors": null,
    "timestamp": "2018-03-11T13:46:48.246+0000"
}
```


> 添加@ResponseResult的返回结果

```json
{
    "code": 40001,
    "message": "系统繁忙，请稍后重试",
    "data": null
}
```

<br>

### 自定义异常



> 没有添加@ResponseResult的返回结果

```json
{
    "status": 401,
    "error": "Unauthorized",
    "message": "用户未登录",
    "code": 20001,
    "path": "/result/exception1",
    "exception": "com.wukong.core.exceptions.UserNotLoginException",
    "errors": null,
    "timestamp": "2018-03-11T13:51:11.305+0000"
}
```


> 添加@ResponseResult的返回结果

```json
{
    "code": 20001,
    "message": "用户未登录",
    "data": null
}
```

<br>



***

>参考资料

主要参考了:企业实战之spring项目《接口响应体格式统一封装》


* API统一返回格式
    * [企业实战之spring项目《接口响应体格式统一封装》](http://blog.csdn.net/aiyaya_/article/details/78976759)
    * [自定义统一api返回json格式--代码无法执行](https://www.cnblogs.com/minsons/p/7101788.html)
    
    
* Exception统一处理

* 不正确的解决方法
    * [Java 接口返回类](https://www.jianshu.com/p/d8f2939d6a36)
    * [SpringBoot进阶之统一接口返回信息格式](http://blog.csdn.net/tiegenz/article/details/78231993)



 


