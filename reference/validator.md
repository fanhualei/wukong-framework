# Validator数据校验的使用

>目录
* [基本用法](#基本用法)
    * [在controller类上校验参数](#在controller类上校验参数)
    * [在model类上检验属性](#在model类上检验属性)
* [自定义Validator](#自定义validator)
* [多语言提示](#多语言提示)

<br>

##基本用法

### 在controller类上校验参数

>使用方法
* Class添加@Validated
* 在参数上添加注解
    * @Length(min = 6,max = 50) name长度要在6到50之间
    * @Email 需要一个合法的email地址


```java
@RestController
@RequestMapping("/result")
@Validated
public class ResultController {
    @RequestMapping("/fail")
    public String fail(@RequestParam @Length(min = 6,max = 50) String name
                ,@RequestParam @Email  String email
                ,@RequestParam  String cellPhone) {
        return name+"ok";
    }
}
```

<br>

>测试 访问 /result/fail?name=11&email=email&cellPhone=3333 

`由于添加了安全模块,需要用TestNg进行测试,见ResultControllerTests代码`

>测试结果

```youtrack
2018-03-10 12:26:37.656 ERROR 6524 --- [o-auto-1-exec-7] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is javax.validation.ConstraintViolationException: fail.name: 长度需要在6和50之间, fail.email: 不是一个合法的电子邮件地址] with root cause

javax.validation.ConstraintViolationException: fail.name: 长度需要在6和50之间, fail.email: 不是一个合法的电子邮件地址
```



<br>

##自定义Validator

<br>

##多语言提示

<br>


***
>参考文献
* [使用validator-api来验证spring-boot的参数](https://www.cnblogs.com/mawang/p/6767906.html?utm_source=itdadao&utm_medium=referral)
