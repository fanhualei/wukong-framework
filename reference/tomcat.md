# tomcat 使用


> 目录

* [安装tomcat](#安装tomcat)
* [安全配置](webSecurity.md#tomcat安全配置)
* [热加载](#热加载)
* [判断tomcat是否启动](#判断tomcat是否启动)
* [tomcat自启动](#tomcat自启动)
* 其他技巧
    * 批处理多个tomcat
    * 做成服务自启动
    * tomcat状态的监控
    
    
## 安装tomcat
 
 ### 注意事项
 
 * 推荐安装tomcat-9.0.2
 * [参考网址](https://www.linuxidc.com/Linux/2017-06/144809.htm)
 * 遇到权限问题,使用下面命令
    * sudo chmod 755 -R tomcat # 是修改tomcat的权限
    * sudo chmod fan tomcat # 这个是将某个目录的权限赋值给fan用户
 * Tomcat启动比较慢   
 
 ### 安装步骤
 
 * 下载Tomcat
 * 解压Tomcat
 * 修改Tomcat下的startup.sh文件
    * 主要是设置Java的环境
 * 修改Tomcat下的shutdown.sh文件,同上
 * 启动tomcat
 * 查看是否安装成功 http://127.0.0.1:8080 或 http://127.0.0.1:8080/examples  
 
 ### 常用命令
 
 * cd /home/fan/tomcat/apache-tomcat-9.0.2/bin/
 * 启动 sudo ./startup.sh
 * 关闭 sudo ./shutdown.sh
 
 
 ## 热加载    
 
 热加载：在开发的时候，修改了类文件，不需要重启tomcat。相对来说要高效一点。
 
 配置server.xml实现热加载：只要在server.xml文件中的host节点中，加入下面的节点。
 
 ```xml
<Context path="" docBase="../../webapp" debug="0" reloadable="true"/>

```
> 注意：在正式环境中，应该将reloadable设置成false，因为自动加载可能会出现错误


 
## 判断tomcat是否启动

> 执行下面的命令，会看到很多行，如果只有一行表示没有启动。

```youtrack
ps   -ef|grep  tomcat
```

> 也可以使用w3m http://127.0.0.1:20180/,但是在端口不向外开放时，不能使用


## tomcat自启动

```youtrack

cd /opt/wk/apache-tomcat-9.0.2/bin/

sudo cp catalina.sh  /etc/init.d/tomcat_20180

cd /etc/init.d/

sudo vim tomcat_20180
#在#!/bin/sh下添加两行内容后并保存退出
#CATALINA_HOME=/opt/wk/apache-tomcat-9.0.2
#JAVA_HOME=/opt/jdk1.8.0_161/

sudo update-rc.d -f tomcat_20180 defaults
#执行该命令会有两个警告提示，这个可以忽略，如果是报error,就一定不能忽略

sudo reboot 

```

> 参考网址： [ubuntu16.04设置tomcat自启动](https://www.cnblogs.com/youcong/p/8469488.html)

> 如果想删除启动项目，可以执行 sudo update-rc.d -f tomcat_20180 remove

> 查看服务器是否启动了

* http://127.0.0.1:20180/
* w3m http://127.0.0.1:20180/