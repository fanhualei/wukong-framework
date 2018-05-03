# 服务器环境配置

> 目录


* [软件安装](#软件安装)
    * [安装系统](#安装系统)
    * [安装JDK](#安装jdk)
    * [安装Tomcat](#安装tomcat)
    * [设置Host文件](#设置host文件)
    * [安装Apache](#安装apache)
    * [Apache反向代理Tomcat](#apache反向代理tomcat)
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
 * Tomcat启动比较慢   
 
 > 安装步骤
 
 * 下载Tomcat
 * 解压Tomcat
 * 修改Tomcat下的startup.sh文件
    * 主要是设置Java的环境
 * 修改Tomcat下的shutdown.sh文件,同上
 * 启动tomcat
 * 查看是否安装成功 http://127.0.0.1:8080 或 http://127.0.0.1:8080/examples  
 
 > 常用命令
 
 * cd /home/fan/tomcat/apache-tomcat-9.0.2/bin/
 * 启动 sudo ./startup.sh
 * 关闭 sudo ./shutdown.sh
 
 
 
  ### 设置Host文件
  
  > 注意事项
  * [参考网址](https://jingyan.baidu.com/article/19192ad8e0333be53f570758.html)
 
 > 配置步骤
 
 * 打开文件 sudo vim /etc/hosts
 * 编辑文件
 * 重启网络 sudo /etc/init.d/networking restart
 * 测试结果 :ping ###.###.###
 
 > hosts实例 www.wk.com 与 wx.wk.com是新追加的内容
 
 ````properties
127.0.0.1       localhost
127.0.1.1       fan-uut

127.0.0.1       www.wk.com
127.0.0.1       wx.wk.com

# The following lines are desirable for IPv6 capable hosts
::1     ip6-localhost ip6-loopback
fe00::0 ip6-localnet
ff00::0 ip6-mcastprefix
ff02::1 ip6-allnodes
ff02::2 ip6-allrouters


````
 <br>
 
 
 ### 安装Apache
 
 >注意事项
 
 * 使用apt进行安装,不推荐从源码安装,因为还要安装好多依赖包
 * 推荐安装Apache2.4
 * 默认安装在 /etc/apache2
 * [Apache2.4帮助文档目录](http://httpd.apache.org/docs/2.4/)
 * [Apache2.4安装帮助文档](http://httpd.apache.org/docs/2.4/install.html)
 
  > 安装步骤
  
  * 安装 sudo apt install apache2
  * 启动 sudo service apache2 start
  * 测试 http://127.0.0.1 
  
  > 基本操作命令
  
  >>如果当前用户的apache已经安装为linux的服务的话
  1. 启动apache
  service httpd2 start 
  2. 停止服务apache
  service httpd2 stop 
  3. 重新启动apache
  service httpd2 restart
  
  >>不是linux的服务的话(下面的没实验过)
  1. 启动apahce的命令：
  /usr/local/apache2/bin/apachectl start apache
  2.  停止apache的命令：
  /usr/local/apache2/bin/apachectl stop  
  3.  重启apache的命令：
  /usr/local/apache2/bin/apachectl restart 
  要在重启 Apache 服务器时不中断当前的连接，则应运行：
  /usr/local/sbin/apachectl graceful

 
 ### Apache反向代理Tomcat
 
 有时候80端口被Apache占用,服务器上还有很多其他服务,例如tomcat node等.<br>
 用户输入80端口时候,可以自动转到tomcat的8080端口
 
 >注意事项
 
 * [Apache官方参考网站](http://httpd.apache.org/docs/2.4/howto/reverse_proxy.html)
 * [网友总结的方法](http://www.yyearth.com/article/17-08/156.html)
 * 更多信息,可以百度:ubuntu apache2 tomcat mods-available
 
 
 >配置步骤
 
 * 启动apache的proxy功能
    * cd /etc/apache2/mods-available
    * ln -s ./proxy.conf ../mods-enabled/
    * ln -s ./proxy_http.load ../mods-enabled/
    * ln -s ./proxy.load ../mods-enabled/
 * 在sites-enabled建立新配置文件
    * 如果输入wx.wk.com转到tomcat的127.0.0.1:8080端口
 ```xml
        <VirtualHost *:80>
                ServerName wx.wk.com
                ProxyPass / http://127.0.0.1:8080/
                ProxyPassReverse / http://127.0.0.1:8080/
        
                ServerAdmin webmaster@localhost
                DocumentRoot /var/www/html
        
                ErrorLog ${APACHE_LOG_DIR}/wx_error.log
                CustomLog ${APACHE_LOG_DIR}/wx_access.log combined
        
        </VirtualHost>
``` 
  * 重启 apache2 service httpd2 restart
  * 可以通过命令 netstat -ant  查看步骤5中运行时系统监听的端口号状况
 
 
 
 ## 部署程序
 
 
 
 <br>