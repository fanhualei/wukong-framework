# Validator数据校验的使用

>目录
* 基本用法
    * [controller上加校验](#controller上加校验)
    * [bean上加校验](#bean上加校验)
    * [在函数内部进行校验](#在函数内部进行校验)

* [自定义Validator](#自定义validator)
* 其他参考
    * [常用的validator](#常用的validator)    
    * [提示国际化](#提示国际化)

<br>

##基本用法

### controller上加校验

>使用方法
* Class添加@Validated
* 在参数上添加注解
    * @Length(min = 6,max = 50) name长度要在6到50之间
    * @Email 需要一个合法的email地址


```java
@RestController
@RequestMapping("/validator")
@Validated
public class ValidatorController {

    @RequestMapping("/para1")
    public Map para1(@RequestParam @Valid @Length(min = 6,max = 50)  String name
            ,@RequestParam @Valid @Email String email
            ,@RequestParam String cellPhone
    ) {

        return para(name,email,cellPhone);
    }

    @RequestMapping("/para2")
    @ResponseResult
    public Map para2(@RequestParam @Valid @Length(min = 6,max = 50) String name
            ,@RequestParam  @Valid @Email String email
            ,@RequestParam  String cellPhone
    ) {
        return para(name,email,cellPhone);
    }

    private Map para(String name,String email,String cellPhone){
        String str= "name:"+name+";"+"email:"+email+";"+"cellPhone:"+cellPhone+";";
        return new HashMap<String,String>(){{
            put("para", str);
        }};
    }
}    
```

<br>

>测试 访问 /validator/para1?name=11&email=email&cellPhone=3333 



>测试结果(没有加全局错误处理)

```youtrack
2018-03-10 12:26:37.656 ERROR 6524 --- [o-auto-1-exec-7] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is javax.validation.ConstraintViolationException: fail.name: 长度需要在6和50之间, fail.email: 不是一个合法的电子邮件地址] with root cause

javax.validation.ConstraintViolationException: fail.name: 长度需要在6和50之间, fail.email: 不是一个合法的电子邮件地址
```




<br>

### bean上加校验

> bean添加校验代码

```java
@Data
public class City {
    @Range(min = 1, max = 20, message = "id只能从1-20")
    private int id;
    @Length(min = 6,max = 50)
    private String name;
    private String code;
}
```






<br>

### 在函数内部进行校验


<br>





<br>

## 自定义Validator

<br>


## 其他参考


### 常用的validator

>常用validator

名称|说明|
--------- | --------|
@null | 验证对象是否为空 |
@notnull |  验证对象是否为非空 | 
@asserttrue | 验证 boolean 对象是否为 true |
@assertfalse   |  验证 boolean 对象是否为 false | 
@min  |  验证 number 和 string 对象是否大等于指定的值 | 
@max |  验证 number 和 string 对象是否小等于指定的值 | 
@decimalmin | 验证 number 和 string 对象是否大等于指定的值，小数存在精度 | 
@decimalmax  | 验证 number 和 string 对象是否小等于指定的值，小数存在精度 | 
@size |  验证对象（array,collection,map,string）长度是否在给定的范围之内 | 
@digits  |  验证 number 和 string 的构成是否合法 | 
@past  |  验证 date 和 calendar 对象是否在当前时间之前 | 
@future  |  验证 date 和 calendar 对象是否在当前时间之后 | 
@pattern |  验证 string 对象是否符合正则表达式的规则 | 
@Email  | 验证邮箱 | 


>例子说明(regexp表达式比较好用)

* @size (min=3, max=20, message="用户名长度只能在3-20之间")
* @size (min=6, max=20, message="密码长度只能在6-20之间")
* @pattern (regexp="[a-za-z0-9._%+-]+@[a-za-z0-9.-]+\\.[a-za-z]{2,4}", message="邮件格式错误")
* @Length(min = 5, max = 20, message = "用户名长度必须位于5到20之间")  
* @Email(message = "比如输入正确的邮箱")  
* @NotNull(message = "用户名称不能为空") 
* @Max(value = 100, message = "年龄不能大于100岁") 
* @Min(value= 18 ,message= "必须年满18岁！" )  
* @AssertTrue(message = "bln4 must is true")
* @AssertFalse(message = "blnf must is falase")
* @DecimalMax(value="100",message="decim最大值是100")
* @DecimalMin(value="100",message="decim最小值是100")
* @NotNull(message = "身份证不能为空") 
* @Pattern(regexp="^(\\d{18,18}|\\d{15,15}|(\\d{17,17}[x|X]))$", message="身份证格式错误"




### 提示国际化

#### class目录添加两个文件

> 必须用ValidationMessages开头

* ValidationMessages.properties
    * demo.name = 名字不能为空
* ValidationMessages_en.properties
    * demo.name = `name` is not empty.   

> 注解中加入{demo.name}

* @NotEmpty(message="{demo.name}")


<br>


***
>参考文献
* [使用validator-api来验证spring-boot的参数](https://www.cnblogs.com/mawang/p/6767906.html?utm_source=itdadao&utm_medium=referral)
* [Spring Boot Validator校验](http://412887952-qq-com.iteye.com/blog/2312356)
* [Spring Boot 入门 - 基础篇（12）- 数据校验](http://rensanning.iteye.com/blog/2357373)