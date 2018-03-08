## security模式使用说明

>参考

[找练API文档](https://www.kancloud.cn/zldev/apidoc/70857)

>目录



* [不要身份验证](#不要身份验证)
    * [登录](#登录)
    * [发送验证码](#发送验证码)
    * [注册](#注册)
    * [查询用户名是否重复](#查询用户名是否重复)
    * [查询邮箱是否重复](#查询邮箱是否重复)
    * [查询电话是否重复](#查询电话是否重复)
    * [通过手机短信登录](#通过手机短信登录)


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
    
### 不要身份验证<br>

不要身份验证接口，可加上[限制同一IP访问频率](#限制同一IP访问频率)功能

#### 登录

>调用方法

类型  | 说明|
--------- | --------|
地址 | /author/jwt/login |
参数  | username,password |
访问方式 | get,post |
成功  | json {token:dkdkdl} |
错误  |  |

<br>


#### 发送验证码

* 1:在redis中`wukong:security:verifycode:{cellphone}:` 添加一个随机数,设置5分钟过期
* 2:用户输入验证码登录时，与redis中的内容进行验证


>调用方法

类型  | 说明|
--------- | --------|
地址 | /author/jwt/getVerifyCode |
参数  | cellphone |
访问方式 | get,post |
成功  | json {token:dkdkdl} |
错误  |  |

<br>


### 要身份验证

要身份验证接口，可加上[防止重复提交](#防止重复提交)功能



<br>

### 附加功能

#### 防御CSRF攻击

待完善

>参考文献 <br>
* [如何通过JWT防御CSRF](https://segmentfault.com/a/1190000003716037)
* [讲真，别再使用JWT了！](https://www.jianshu.com/p/af8360b83a9f)
