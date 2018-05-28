# API返回格式及异常处理


> 目录


* [API返回结果](#api返回结果)
    * [成功返回结果](#成功返回结果)
    * [异常返回结果](#异常返回结果)
    
* [全局异常处理](#全局异常处理)
    * [为什么做全局异常处理](#为什么做全局异常处理)
    * [全局异常处理设计思路](#全局异常处理设计思路)


* [自定义异常的使用](#自定义异常的使用)
    * [如何改变status数值](#如何改变status数值)
    * [如何定义code值](#如何定义code值)
    * [初始化BusinessException三个方法](初始化businessexception三个方法)
        * [通过code值](#通过code值)
        * [通过code值+变量](#通过code值+变量)
        * todo BusinessException  实际用法


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
# 自定义异常的使用

## 如何改变status数值
通过设置ResponseEnitiy的status属性，可以自定义任何status代码进行返回。
```
//返回600状态码
ResponseEntity.status(600).body(defaultErrorResult);
```
## 如何定义code值
修改ResultCode,添加Enum类型变量。
```
//添加一个名为GOODS_NOT_EXISTS的变量，其Code为66666，提示信息为商品不存在
GOODS_NOT_EXISTS(66666, "商品不存在");
```
## 初始化BusinessException三个方法
### 通过code值
```
throw new BusinessException(ResultCode.GOODS_NOT_EXISTS);
```


























>参考资料

主要参考了:企业实战之spring项目《接口响应体格式统一封装》


* API统一返回格式
    * [企业实战之spring项目《接口响应体格式统一封装》](http://blog.csdn.net/aiyaya_/article/details/78976759)
    * [自定义统一api返回json格式--代码无法执行](https://www.cnblogs.com/minsons/p/7101788.html)
    
    
* Exception统一处理
    * [企业实战之spring增强器实现《全局异常处理器》](http://blog.csdn.net/aiyaya_/article/details/78725755)






 


