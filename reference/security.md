# security模式使用说明

>参考

重点
* 微信小程序登录
* springBoot参数验证
* springBoot统一的错误返回机制

>目录


* [可以直接访问的](#可以直接访问的)
    * [登录](#登录)
    * [发送验证码](#发送验证码)
    * [注册](#注册)
    * [通过手机短信登录](#通过手机短信登录)
    * [通过第三方登录](#通过第三方登录)
    * [判断用户名邮箱电话是否存在](#判断用户名邮箱电话是否存在)    

* [登录后才可访问的](#登录后才可访问的)
    * [注销](#注销)
    * [刷新token](#刷新token)
    * [得到防重prtoken](#得到防重prtoken)
    * [修改密码](#修改密码)

* [附加功能](#附加功能)
    * [防止重复提交](#防止重复提交)
    * [限制同一ip访问频率](#限制同一ip访问频率)
    * [防御csrf攻击](#防御csrf攻击)
    * [黑名单策略](#黑名单策略)

    
<br>
    
## 可以直接访问的

###### 不需要登录就可以访问的API接口，可加上[限制同一ip访问频率](#限制同一ip访问频率)功能

### 登录

>调用方法 `get` `post`  [https://localhost:8443/author/jwt/login](#)


参数  | 说明|
--------- | --------|
username | 用户名|
password | 密码 |


```json
//success
{"token":"服务器生成的token"}
```

<br>


### 发送验证码

>调用方法 `get` `post`  [https://localhost:8443/author/jwt/getVerifyCode](#)

参数  | 说明|
--------- | --------|
cellphone | 手机号码 |


```json
//success
{
    "code": 0,
    "message": "success",
    "data":{
      "verifycode":"1234"
    }
}
```

```json
//error
{
    "code": 1,
    "message": "错误信息"
}
```

>处理逻辑

* 1:在redis中`wukong:security:verifycode:{cellphone}:` 添加一个随机数，并发给用户手机上,设置5分钟过期
* 2:用户输入验证码登录时，与redis中的内容进行验证


<br>


### 注册

`当前只实现用手机号码注册`


>调用方法 `get` `post`  [https://localhost:8443/author/jwt/regist](#)


参数  | 说明|
--------- | --------|
  cellphone|手机号码  |
  password| 密码 |
  verifycode| 验证码 |


```json
//success
{
    "code": 0,
    "message": "success",
    "data":{
      "token":"服务器生成的token数值"
    }
}
```

```json
//error
{
    "code": 1,
    "message": "错误信息"
}
```

>处理逻辑

`成功:` 返回token

* 1:客户端输入：手机号 验证码 密码
* 2:服务器端判断
    * 2.1: 校验格式：手机号 验证码 密码
    * 2.2: 判断验证码是否有效
    * 2.3: 判断手机号是否已经注册
        * 2.3.1: 手机号存在   : 调用[通过手机短信登录](#通过手机短信登录)
        * 2.3.2: 手机号码不存在:
            * 新建立用户,用户名:(phone){cellphone};
            * 并添加USER_ROLE;
            * 根据新建的user,得到token,返回给客户端

`(phone){cellphone}=(phone)13800138000 今后用户名中有(phone)开头的,都是手机注册的`

<br>


### 通过手机短信登录

>调用方法 `get` `post`  [https://localhost:8443/author/jwt/loginByPhonemessage](#)

参数  | 说明|
--------- | --------|
cellphone| 电话号码|
verifycode|验证码|


```json
//success
{
    "code": 0,
    "message": "success",
    "data":{
      "token":"服务器生成的token数值"
    }
}
```


>处理逻辑

`成功:` 返回token

* 1:客户端输入：手机号 验证码(服务器发送到用户手机上)
* 2:服务器端判断
    * 2.1: 校验格式：手机号 验证码 
    * 2.2: 判断验证码是否有效
    * 2.3: 判断手机号是否已经注册
        * 2.3.1: 手机号存在 ,查询出用户,并生成token
        * 2.3.1: 手机用户不存在,返回错误信息

<br>


### 通过第三方登录

'这个函数还需要仔细想想'

> 调用方法 `post`  [https://localhost:8443/author/jwt/thirdPartyLogin](#) <br> 

参数  | 说明|
--------- | --------|
type| 用户是否先用微博或微信登陆，（2：代表微博登陆，1：代表微信登陆）|
openId|微博或微信的openId|
timeStamp|时间戳|
summary|加密字段,加密方法（Key+type+openId+timeStamp 顺序进行MD5加密，Key的值为wech_app2015_en）|

```json
//success
{
    "code": 0,
    "message": "success",
    "data": {
        "userid": 123,
        "nickName": "",
        "token": "",
        "age": 0,
        "sex": "",
        "credential": "",
        "cellphone": "",
        "headImageUrl":"路径"
    }
}
```



<br>

### 判断用户名邮箱电话是否存在

> 这是三个函数
>> 电话号码 `get` `post`  [https://localhost:8443/author/jwt/phoneExist](#) <br>
>> 邮箱 `get` `post`  [https://localhost:8443/author/jwt/emailExist](#) <br>
>> 用户名 `get` `post`  [https://localhost:8443/author/jwt/usernameExist](#)

参数  | 说明|
--------- | --------|
cellphone| 电话号码|
email|邮箱|
username|用户名|

```json
//success
{
    "code": 0,
    "message": "success",
    "data":{
      "phoneExist":true,  
      "emailExist":true,
      "usernameExist":true
    }
}
```
`true:表示存在`<br>
`上面的代码是实例文件,实际上只会返回一个`



<br>

## 登录后才可访问的

###### 需要登录成功后才可访问的API接口，可加上[防止重复提交](#防止重复提交)功能


### 注销

>调用方法 `get` `post`  [https://localhost:8443/author/jwt/logout](#)

<br>

### 刷新Token

>调用方法 `get` `post`  [https://localhost:8443/author/jwt/refreshToken](#)

```json
//success
{
    "code": 0,
    "message": "success",
    "data":{
      "token":"服务器生成的token数值"
    }
}
```
>处理逻辑

* 1:服务器判断老的token是否有效,用户是否被禁用等.
* 2:只更新过期时间,然后将token返回给客户端.

<br>

### 得到防重prtoken

>调用方法 `get` `post`  [https://localhost:8443/author/jwt/prtoken](#)

```json
//success
{
    "code": 0,
    "message": "success",
    "data":{
      "prtoken":"prtoken"
    }
}
```

>处理逻辑 见:[防止重复提交](#preventrepeat.md)

<br>

### 修改密码

>调用方法  `post`  [https://localhost:8443/author/jwt/changePassword](#)


参数  | 说明|
--------- | --------|
  cellphone|手机号码  |
  password| 密码 |
  verifycode| 验证码 |


```json
//success
{
    "code": 0,
    "message": "success",
    "data":{
      "token":"服务器生成的token数值"
    }
}
```

`上面这个函数与注册的函数一样,所以写代码的时候,可以重用`


<br>

## 附加功能


### 防止重复提交

> 详细内容见 [防止重复提交](preventrepeat.md)

<br>


### 限制同一ip访问频率

> 示例:@RequestLimit(count=10,time=5000)   

`其中count指的是规定时间内的访问次数，time指的就是规定时间，单位为毫秒`

```java
@Controller  
public class URLController {  
    @RequestLimit(count=10,time=5000)  
    @RequestMapping("/urltest")  
    @ResponseBody  
    public String test(HttpServletRequest request, ModelMap modelMap) {  
        return "aaa";  
    }  
}  
```

`参考` [实现方法](http://blog.csdn.net/gebitan505/article/details/55517574)

<br>

### 防御csrf攻击

待完善

>参考文献 <br>
* [如何通过JWT防御CSRF](https://segmentfault.com/a/1190000003716037)
* [讲真，别再使用JWT了！](https://www.jianshu.com/p/af8360b83a9f)

<br>

### 黑名单策略

>黑名单,不仅限于ip地址,还应该包含用户浏览器的cookie <br>
>黑名单,应该是一个智能的,系统自动判断异常,进行防御操作
>用户访问的ip地址(或地域)经常变更,那么可能存在异常
>应该记录下用户的登录日志



###### API参考 [找练API文档](https://www.kancloud.cn/zldev/apidoc/70857)