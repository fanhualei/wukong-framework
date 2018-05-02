# 服务器环境配置

> 目录


* [软件安装](#软件安装)
    * [安装系统](#安装系统)
    * [安装JDK](#安装jdk)
    * [安装Tomcat](#安装tomcat)
    * [设置Host文件](#设置host文件)
    * [安装Apache](#安装apache)
    * [安装第二个Tomcat](#安装第二个tomcat)
* [关键点](#关键点)
    * [Web环境配置](#Web环境配置)
    * [负载均衡配置](#负载均衡配置)
    * [模块二级域名分配](#模块二级域名分配)
    * [安全设置](#安全设置)
    * [日志与备份](#日志与备份)
    * [数据库负载](#数据库负载)




 <br>
 
 ## 软件安装
 
 `Apache` `方向代理`  `多域名配置` `多Tomcat配置`
 
  <br>
  
 ### 安装系统
 
 * 开发环境,推荐安装Ubuntu18. 
 * 服务器环境,推荐安装Ubuntu 16.04 
 [安装教程](https://jingyan.baidu.com/article/3c48dd348bc005e10be358eb.html)
 
 <br>
 
 ### 安装JDK
 
 > 注意事项
 
 * 推荐使用jdk1.8.0_161,不要用jdk9,因为SpringBoot支持不太好(2018.5.1)
 * 可以安装多个JDK,在Tomcat中指定使用那个JDK
 * [参考网址](http://www.linuxidc.com/Linux/2017-06/144732.htm)
 
 <br>
 
 > 安装步骤
 
 * 下载JDK 
 * 解压 
 * 移动到 /opt
 * 修改配置文件 vim ~/.bashrc 
 * 生效配置文件 source ~/.bashrc
 * 检查结果    java -version
 
 <br>
 
 ### 安装Tomcat
 
 > 注意事项
 * 推荐安装tomcat-9.0.2
 * [参考网址](https://www.linuxidc.com/Linux/2017-06/144809.htm)
 * 遇到权限问题,使用下面命令
    * sudo chmod 755 -R tomcat # 是修改tomcat的权限
    * sudo chmod fan tomcat # 这个是将某个目录的权限赋值给fan用户
 
 > 安装步骤
 
 * 下载Tomcat
 * 解压Tomcat
 * 修改Tomcat下的startup.sh文件
    * 主要是设置Java的环境
 * 修改Tomcat下的shutdown.sh文件,同上
 * 启动tomcat
 * 查看是否安装成功 http://127.0.0.1:8080   
 
 
 
 ## 部署程序
 
 
 
 <br>