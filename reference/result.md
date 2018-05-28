# API返回格式及异常处理


> 目录


* [API返回结果](#api返回结果)
    * [成功返回结果](#成功返回结果)
    * [异常返回结果](#异常返回结果)
    
* [全局异常处理](#全局异常处理)
    * [为什么做全局异常处理](#为什么做全局异常处理)
    * [全局异常处理设计思路](#全局异常处理设计思路)


* [不同类型异常处理方法](#不同类型异常处理方法)
    * [参数校验异常](#参数校验异常)
    * [其他系统错误](#其他系统错误)
    * [自定义异常](#自定义异常)


<br>




## API返回结果

* 通过status来判断是否成功
    * 成功：status=200
    * 失败：status!=200
    


### 成功返回结果

> 成功返回结果的类型
    
* 基础类型->字符串
* 对象   ->json字符串


>> 字符串结果

```youtrack
name:123456;email:123@189.cn;cellPhone:123;
```

>> json 结果

````json
{"id":1,"name":"city1","code":"001"}
````



### 异常返回结果

> Sringboot默认的返回结果

* 使用参数校验框架的返回结果

```json
{
    "timestamp": "2018-05-24T06:31:51.423+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "para1.name: 长度需要在6和50之间, para1.email: 不是一个合法的电子邮件地址",
    "path": "/result/para"
}
```

* 异常的抛出结果

```json
{
    "timestamp": "2018-05-24T06:36:09.686+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "No message available",
    "path": "/result/exception"
}
```




## 全局异常处理


### 为什么做全局异常处理

* spring 的异常处理太简单

```json
{
    "timestamp": "2018-05-24T06:31:51.423+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "para1.name: 长度需要在6和50之间, para1.email: 不是一个合法的电子邮件地址",
    "path": "/result/para"
}
```

* 希望能添加更多的信息，例如下面

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


### 全局异常处理设计思路


#### GlobalExceptionHandler 来捕获错误



> 实例代码

````java
@ControllerAdvice  // 这个注解是用来捕获异常
@ResponseBody     //  如果不添加这个注解，那么不能将DefaultErrorResult 返回
public class GlobalExceptionHandler  {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class) //需要拦截的异常
    public DefaultErrorResult handleConstraintViolationException(ConstraintViolationException e
      , HttpServletRequest request) {
        
        //自定义defaultErrorResult ，来返回数据
        return new DefaultErrorResult(ResultCode.PARAM_IS_INVALID, e);    
    }

}
````























































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
    * [企业实战之spring增强器实现《全局异常处理器》](http://blog.csdn.net/aiyaya_/article/details/78725755)

* 不正确的解决方法
    * [Java 接口返回类](https://www.jianshu.com/p/d8f2939d6a36)
    * [SpringBoot进阶之统一接口返回信息格式](http://blog.csdn.net/tiegenz/article/details/78231993)



 


