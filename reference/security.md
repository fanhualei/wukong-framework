## security模式使用说明

>参考



>目录


* [不要身份验证](#不要身份验证)
    * [登录](#登录)
    * [发送验证码](#发送验证码)
    * [注册](#注册)
    * [通过手机短信登录](#通过手机短信登录)
    * [通过第三方登录](#通过第三方登录)
    * [判断用户名邮箱电话是否存在](#判断用户名邮箱电话是否存在)    

* [要身份验证](#要身份验证)
    * [注销](#注销)
    * [刷新Token](#刷新Token)
    * [得到防重prtoken](#得到防重prtoken)
    * [修改密码](#得到防重prtoken)

* [附加功能](#附加功能)
    * [防止重复提交](#防止重复提交)
    * [限制同一IP访问频率](#限制同一IP访问频率)
    * [防御CSRF攻击](#防御CSRF攻击)

    
<br>
    
### 不要身份验证
___

###### 不要身份验证接口，可加上[限制同一IP访问频率](#限制同一IP访问频率)功能

#### 登录

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


#### 发送验证码

>调用方法 `get` `post`  [https://localhost:8443/author/jwt/getVerifyCode](#)

参数  | 说明|
--------- | --------|
cellphone | 手机号码 |


```json
//success
{
    "code": 0,
    "msg": "success",
    "data":{
      "verifycode":"1234"
    }
}

```

```json
//error
{
    "code": 1,
    "msg": "错误信息"
}
```

>处理逻辑

* 1:在redis中`wukong:security:verifycode:{cellphone}:` 添加一个随机数，并发给用户手机上,设置5分钟过期
* 2:用户输入验证码登录时，与redis中的内容进行验证


<br>


#### 注册

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
    "msg": "success",
    "data":{
      "token":"服务器生成的token数值"
    }
}

```

```json
//error
{
    "code": 1,
    "msg": "错误信息"
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


#### 通过手机短信登录

>调用方法

`get` `post`

类型  | 说明|
--------- | --------|
地址 | /author/jwt/loginByPhoneMsg |
参数  | cellphone,verifycode|


```json
//success
{
    "code": 0,
    "msg": "success",
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

### 要身份验证

___


###### 要身份验证接口，可加上[防止重复提交](#防止重复提交)功能

<br>

### 附加功能
___


#### 防御CSRF攻击

待完善

>参考文献 <br>
* [如何通过JWT防御CSRF](https://segmentfault.com/a/1190000003716037)
* [讲真，别再使用JWT了！](https://www.jianshu.com/p/af8360b83a9f)



[找练API文档](https://www.kancloud.cn/zldev/apidoc/70857)