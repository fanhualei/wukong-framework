# 微信小程序

> 目录

* [初始化](#初始化)
* [注意事项](#注意事项)
* [工程应用](#工程应用)


## 初始化

[引用了一个开源的微信包](https://codegit.net/jlee/weixin-java-tools/src/master)


> 参数配置

```properties

#微信小程序
wechat.miniapp.appid=111
wechat.miniapp.secret=111
wechat.miniapp.token=111
wechat.miniapp.aesKey=111
wechat.miniapp.msgDataFormat=JSON
```

* token可由开发者可以任意填写
* aesKey由开发者手动填写或随机生成
* appid=wxf01a5b261ceac1364c
* secret=19828e97ea827842e64c9bd8cadad77c1

c1+91

> 在微信服务器上进行配置

* 192.168.3.102


[微信服务器教程](https://developers.weixin.qq.com/miniprogram/dev/api/custommsg/callback_help.html)



## 注意事项

> 引用的时候,会出现NoClassDefFoundError 错误

* 原因:程序中使用了httpclient 与微信组件包jar冲突.
* 解决方法: 在pom.xml中注释掉httpclient

[参考网址](https://github.com/Wechat-Group/weixin-java-tools/wiki/NoClassDefFoundError%E3%80%81NoSuchMethdError%E6%88%96ClassNotFoundException%E7%9A%84%E8%A7%A3%E5%86%B3%E5%8A%9E%E6%B3%95)


> WxErrorController类在springBoot2.0 下出现错误

* 注释掉这个类,因为这个类是全局错误处理,今后框架有自己的全局错误处理.



## 工程应用