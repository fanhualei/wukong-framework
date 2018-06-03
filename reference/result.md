# API返回格式及异常处理


> 目录


* [API返回结果](#api返回结果)
    * [成功返回结果](#成功返回结果)
    * [异常返回结果](#异常返回结果)
    
* [悟空框架API返回规范](#悟空框架api返回规范)    
    * [controller规范](#controller规范)
    * [service命名规范](#service命名规范)
    
* [全局异常处理](#全局异常处理)
    * [为什么做全局异常处理](#为什么做全局异常处理)
    * [全局异常处理设计思路](#全局异常处理设计思路)
    * [关于400错误的四种异常抛出情形](#关于400错误的四种异常抛出情形)


* [自定义异常的使用](#自定义异常的使用)
    * [如何改变status数值](#如何改变status数值)
    * [如何定义code值](#如何定义code值)
    * [初始化BusinessException三个方法](初始化businessexception三个方法)
        * [通过code值](#通过code值)
        * [通过code值+异常变量列表](#通过code值+异常变量列表)
        * [通过code值+Message格式化参数](#通过code值+Message格式化参数)
        * todo BusinessException  实际用法


<br>




## API返回结果

* 通过status来判断是否成功
    * 成功：status=200
    * 失败：status!=200
    


### 成功返回结果


分类 | 具体类型 | 说明 |
--------- | --------| --------| 
基础类型   | int long Integer Long BigDecimal Date String | 直接输出|   
对象      | Map User Object | json字符串|  





####  基础类型输出结果

```youtrack
name:123456;email:123@189.cn;cellPhone:123;
```

##### 在基础类型上添加注解

```java
@RequestMapping("/num1")
@SingleResponseResult()
public int  num1() {
    return 666;
}
```

> 输出json结果

```json
{"result":666}
```



#### json输出结果

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


[HTTP状态码](http://www.runoob.com/http/http-status-codes.html)

<br>


## 悟空框架API返回规范

### controller规范

功能 | 返回值 | 说明 | 注解 |
--------- | --------| --------|  --------| 
insert添加   | long        | 负数:错误,0:没有添加成功，正数：主键(如果是联合主键返回1)     | 加注解返回json   |  
delete删除   | int         | >=0:删除的记录数，<0:表示错误                            | 加注解返回json   | 
update更新   | int         | >=0:更新的记录数，<0:表示错误                            | 加注解返回json  | 
count记录总数| long        | >=0:返回的记录数                                        | 加注解返回json | 
是否        | int         | 1:true,0:false,-1:错误                                 | 加注解返回json | 
返回String  | String      | 字符串                                                 | 加注解返回json | 
select     | List或对象   | json字符串                                             | 无注解返回json   | 

> 基础类型加 @SingleResponseResult()注解

为了方便读取，返回一个result的json字符串
```json
{"result":666}
```

### service命名规范

#### 所有更新数据库的操作，必须以insert,delete,update开头


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
#### 关于400错误的四种异常抛出情形
1. ConstraintViolationException  
违反约定异常，常见于对数据库的操作。
例如插入重复的主键，找不到对应的模型，实体属性的值和数据库实际类型不一致等

2. HttpMessageNotReadableException  
参数传送的请求无法正常处理，常见于字符串格式不正确，无法识别为对应的实体或Json对象。

3. BindException  
数据绑定时发生异常，因为发送的数据与接收时使用的变量格式无法匹配。  
例如接收时需要数据，但发送的数据是字符串。  

4. MethodArgumentNotValidException   
违反参数校验的异常，抛出此类型。

## 自定义异常的使用

### 如何改变status数值
在全局异常处理的GlobalExceptionHandler里，对异常的返回结果进行处理，通过设置ResponseEnitiy的status属性，可以改变status的数值。
```
//返回600状态码
ResponseEntity.status(600).body(defaultErrorResult);
```

### 如何定义code值
在ResultCode中，定义新的枚举类型，来定义Code值。  
最简单的定义方法为Code与Message的组合。
```
//添加一个名为GOODS_NOT_EXISTS的变量，其Code为66666，提示信息为商品不存在
GOODS_NOT_EXISTS(66665, "商品不存在");
```
Message中可以自定义参数，并在具体抛出异常时使用自定义的信息初始化， 详情参阅初始化BussinessException小节。
```
//添加一个名为USER_NOT_RIGHT的新异常，其Code为66666，提示信息为用户权限不足，但用户名处%s，可在后期进行格式化处理
USER_NOT_RIGHT(66666, "用户%s权限不足"),
//Message中的参数可以有多个，例如以下为带两个参数的Message实例
USER_NOT_RIGHT2(66667, "用户%s权限不足以使用%s功能");
```


### 初始化BusinessException三个方法
#### 通过code值
以Controller为例，演示通过Code值抛出600异常的方法
```
//抛出COMM_PARAM_IS_INVALID(30101, "参数无效"),异常。
 @RequestMapping("/exception")
    public String exception1(Integer code) {
        throw new BusinessException(ResultCode.COMM_PARAM_IS_INVALID);
    }
```
该Controller的返回结果如下
```
{
    "status": 600,
    "error": "Business Error",
    "message": "参数无效",
    "code": 30101,
    "path": "/result/exception",
    "exception": "com.wukong.core.exceptions.BusinessException",
    "errors": null,
    "timestamp": "2018-05-31T09:42:11.224+0000"
}
```
#### 通过code值+异常变量列表
通过带异常变量列表的方式来抛出601异常。
```
//抛出COMM_PARAM_IS_INVALID(30101, "参数无效"),异常。
 @RequestMapping("/exception")
 public String exception1(Integer code) {
        //构造一个HashMap用于存放违反规则的变量与提示信息
        Map p=new HashMap();
        //将变量名作为Key，提示信息作为Value存入Map中
        p.put("email","不是一个合法的电子邮件地址");
        p.put("name","长度需要在6和50之间");
        //将p作为参数抛出
        throw new BusinessException(ResultCode.COMM_PARAM_IS_INVALID,p);
    }
```
该Controller的返回结果如下(其中errors与标注格式还存在一定区别，有待进一步优化)。
```
{
    "status": 601,
    "error": "Bad Request",
    "message": "参数无效",
    "code": 30101,
    "path": "/result/exception",
    "exception": "com.wukong.core.exceptions.BusinessException",
    "errors": {
        "name": "长度需要在6和50之间",
        "email": "不是一个合法的电子邮件地址"
    },
    "timestamp": "2018-05-31T09:41:16.461+0000"
}
```
#### 通过code值+Message格式化参数
对于单参数的Message，在抛出时指定参数进行初始化
```
//抛出USER_NOT_RIGHT(66666, "用户%s权限不足"),异常,并把%s格式化为"admin"
 @RequestMapping("/exception")
    public String exception1(Integer code) {
        throw new BusinessException(ResultCode.USER_NOT_RIGHT,"admin");
    }
```
该Controller的返回结果如下
```
{
    "status": 600,
    "error": "Business Error",
    "message": "用户admin权限不足",
    "code": 66666,
    "path": "/result/exception",
    "exception": "com.wukong.core.exceptions.BusinessException",
    "errors": null,
    "timestamp": "2018-05-31T09:45:54.684+0000"
}
```
对于多参数的Message，在抛出时指定参数进行初始化
```
//抛出USER_NOT_RIGHT2(66667, "用户%s权限不足以使用%s功能")异常,并将两个参数格式化为"admin","manage"。
 @RequestMapping("/exception")
    public String exception1(Integer code) {
        throw new BusinessException(ResultCode.USER_NOT_RIGHT,"admin","manage");
    }
```
该Controller的返回结果如下
```
{
    "status": 600,
    "error": "Business Error",
    "message": "用户admin权限不足以使用manage功能",
    "code": 66667,
    "path": "/result/exception",
    "exception": "com.wukong.core.exceptions.BusinessException",
    "errors": null,
    "timestamp": "2018-05-31T09:46:55.712+0000"
}
```

<br><br>




>参考资料

主要参考了:企业实战之spring项目《接口响应体格式统一封装》


* API统一返回格式
    * [企业实战之spring项目《接口响应体格式统一封装》](http://blog.csdn.net/aiyaya_/article/details/78976759)
    * [自定义统一api返回json格式--代码无法执行](https://www.cnblogs.com/minsons/p/7101788.html)
    
    
* Exception统一处理
    * [企业实战之spring增强器实现《全局异常处理器》](http://blog.csdn.net/aiyaya_/article/details/78725755)






 


