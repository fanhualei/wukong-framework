# 微信小程序

> 目录

* [初始化](#初始化)
* [注意事项](#注意事项)
* [工程应用](#工程应用)


## 初始化

[引用了一个开源的微信包](https://codegit.net/jlee/weixin-java-tools/src/master)



## 注意事项

> 引用的时候,会出现NoClassDefFoundError 错误

* 原因:程序中使用了httpclient 与微信组件包jar冲突.
* 解决方法: 在pom.xml中注释掉httpclient

[参考网址](https://github.com/Wechat-Group/weixin-java-tools/wiki/NoClassDefFoundError%E3%80%81NoSuchMethdError%E6%88%96ClassNotFoundException%E7%9A%84%E8%A7%A3%E5%86%B3%E5%8A%9E%E6%B3%95)



## 工程应用