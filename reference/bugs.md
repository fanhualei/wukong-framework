# 一些已知错误

> 目录

* Http转Https模块
    * [PostMan调用接口错误](#postman调用接口错误)
    
    
    

## Http转Https模块

### PostMan调用接口错误

    1:当wukong.web.http2https=true,启动http转https
    2:在postman中执行http出现错误
        http://localhost:8080/validator/bean1,出现错误
    3:在postman中执行https正确
        https://localhost:8443/validator/bean1,正确
    4:结论判断http跳转到https时候,系统抛出异常HttpMessageNotReadableException    
    

<br>

`错误提示`

```json
{
    "status": 400,
    "error": "Bad Request",
    "message": "参数无效",
    "code": 10001,
    "path": "/validator/bean1",
    "exception": "org.springframework.http.converter.HttpMessageNotReadableException",
    "errors": null,
    "timestamp": "2018-03-12T02:46:16.167+0000"
}
```    
    
             
