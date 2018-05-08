# 消息队列的使用

> 目录

* [待选产品](#待选产品)
* [RocketMQ使用](#rocketmq使用)


## 待选产品


> 参考文档


* [MQ选型对比RabbitMQ RocketMQ ActiveMQ Kafka](https://blog.csdn.net/oMaverick1/article/details/51331004)






## RocketMQ使用

> 参考文档

* 安装
    * [ubuntu安装rabbitMQ方法](https://jingyan.baidu.com/article/d3b74d64151d351f77e609e8.html)
    * 推荐:[Ubuntu 16.04 通过 apt 安装 RabbitMQ ](http://blog.51cto.com/walkerqt/2065470)
* mqtt
    * [官网的安装方法](http://www.rabbitmq.com/web-mqtt.html)
        * sudo rabbitmq-plugins enable rabbitmq_mqtt 官网上多了一个web
    
* Java安装    
    * [springboot(八)：RabbitMQ详解](#https://www.cnblogs.com/ityouknow/p/6120544.html)
    * [springboot2.0 连接rabbitmq处理消息](https://www.2cto.com/kf/201804/738015.html)
    * [使用rabbitmq做为mqtt服务器，整合spring做推送后台](https://my.oschina.net/u/1047640/blog/819418)

* 安全配置
    * 修改默认密码:guest -> fanhualei
    * 添加https
        * [参考网址](https://www.2cto.com/kf/201804/739624.html)
    * [rabbitmq用户管理、角色管理与权限管理](https://www.cnblogs.com/java-zhao/p/5670476.html)


* 常见命令
    * 访问路径:http://localhost:15672/
    * 启动 RabbitMQ 服务
        * sudo systemctl enable rabbitmq-server
        * sudo systemctl start rabbitmq-server
    * 查看状态
        * sudo systemctl status rabbitmq-server
    * 重新启动
        * sudo systemctl restart rabbitmq-server
        
    * 查看 RabbitMQ 版本
        * sudo rabbitmqctl status | grep rabbit
    * 查看可用插件及已安装插件
        * sudo rabbitmq-plugins list
    * 查看用户
        * sudo rabbitmqctl list_users
    * 添加管理用户
        * sudo rabbitmqctl add_user admin yourpassword
        * sudo rabbitmqctl set_user_tags admin administrator        
                      