# 返回错误码规范


* 参考：[API返回格式及异常处理](result.md)



## 错误返回例子


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

## 规范

> status 规范

* 600与601都是框架抛出的错误
* 601表示参数校验异常。600表示非参数校验异常。


> code 规范

* com.wukong.core.enums.ResultCode中定义了相关的规范

```java

/*
权限错误：10001-19999
前缀:SECU
*/
SECU_PERMISSION_NO_ACCESS(10001, "无访问权限"),
SECU_RESOURCE_EXISTED(10002, "资源已存在"),
SECU_RESOURCE_NOT_EXISTED(10003, "资源不存在"),

SECU_USER_NOT_LOGGED_IN(10004, "用户未登录"),
SECU_USER_LOGIN_ERROR(10005, "账号不存在或密码错误"),
SECU_USER_ACCOUNT_FORBIDDEN(10006, "账号已被禁用"),
SECU_USER_NOT_EXIST(10007, "用户不存在"),
SECU_USER_HAS_EXISTED(10008, "用户已存在"),
SECU_LOGIN_CREDENTIAL_EXISTED(10009, "凭证已存在"),


/*
接口错误：20001-29999
*/
INTERFACE_INNER_INVOKE_ERROR(20001, "内部系统接口调用异常"),
INTERFACE_OUTTER_INVOKE_ERROR(20002, "外部系统接口调用异常"),
INTERFACE_FORBID_VISIT(20003, "该接口禁止访问"),
INTERFACE_ADDRESS_INVALID(20004, "接口地址无效"),
INTERFACE_REQUEST_TIMEOUT(20005, "接口请求超时"),
INTERFACE_EXCEED_LOAD(20006, "接口负载过高"),


/*
常见的错误:30001-39999
参数相关的：30101-30199
数据相关的：30201-30299
其他     :30301-30399
 */

COMM_PARAM_IS_INVALID(30101, "参数无效"),
COMM_PARAM_IS_BLANK(30102, "参数为空"),
COMM_PARAM_TYPE_BIND_ERROR(30103, "参数类型错误"),
COMM_PARAM_NOT_COMPLETE(30104, "参数缺失"),

COMM_DATA_NONE(30201, "数据未找到"),
COMM_DATA_IS_WRONG(30202, "数据有误"),
COMM_DATA_ALREADY_EXISTED(30203, "数据已存在"),

COMM_SYSTEM_INNER_ERROR(30301, "系统繁忙，请稍后重试"),
```







