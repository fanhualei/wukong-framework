# API返回格式与全局异常捕获

> 目录

* [API返回格式](#API返回格式)
* [全局异常捕获](#全局异常捕获)

<br>

## API返回格式

> 添加注解就可以返回统一的格式

```java
@RequestMapping("/success1")
public City success1() {
    City city = new City(1,"city1","001");
    return city;
}

@RequestMapping("/success2")
@ResponseResult
public City success2() {
    City city = new City(1,"city1","001");
    return city;
}
```

[详细代码](../wukong-examples/src/main/java/com/wukong/examples/controller/ResultController.java) 

<br>

> 不加注解:https://localhost:8443/result/success1

    {"id":1,"name":"city1","code":"001"}

> 添加注解:https://localhost:8443/result/success2

    {"code":0,"msg":"success","data":{"id":1,"name":"city1","code":"001"}}


<br>


## 全局异常捕获

<br>









***

>参考资料

主要参考了:企业实战之spring项目《接口响应体格式统一封装》


* API统一返回格式
    * [企业实战之spring项目《接口响应体格式统一封装》](http://blog.csdn.net/aiyaya_/article/details/78976759)
    * [自定义统一api返回json格式--代码无法执行](https://www.cnblogs.com/minsons/p/7101788.html)
    
    
* Exception统一处理

* 不正确的解决方法
    * [Java 接口返回类](https://www.jianshu.com/p/d8f2939d6a36)
    * [SpringBoot进阶之统一接口返回信息格式](http://blog.csdn.net/tiegenz/article/details/78231993)



 


