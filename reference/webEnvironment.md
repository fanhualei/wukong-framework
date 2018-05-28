# 服务器环境配置

> 前言

未来的服务器环境，都通过docker镜像来安装


> 目录


* [软件安装](#软件安装)
    * [安装Ubuntu系统](#安装ubuntu系统)
    * [安装JDK](#安装jdk)
    * [安装Tomcat](tomcat.md#安装tomcat)
    * [设置Host文件](#设置host文件)
    * [安装Apache](#安装apache)
    * [Apache反向代理Tomcat](#apache反向代理tomcat)
    * [安装第二个Tomcat](#安装第二个tomcat)
    * [安装Mysql](mysql.md)
    * [安装redis](redis.md)
    * [安装gitlab](gitlab.md)
    * [gitlab中nginx方向代理](gitlab_apache.md)
* [关键点](#关键点)
    * [Https配置](https.md)
    * [负载均衡配置](#负载均衡配置)
    * [模块二级域名分配](#模块二级域名分配)
    * [安全设置](#安全设置)
    * [日志与备份](#日志与备份)
    * [数据库负载](#数据库负载)




 <br>
 
 ## 软件安装
 
 `Apache` `方向代理`  `多域名配置` `多Tomcat配置`
 
  <br>
  
 ### 安装Ubuntu系统
 
 * 开发环境,推荐安装Ubuntu18. 
 * 服务器环境,推荐安装Ubuntu 16.04 
 [安装教程](https://jingyan.baidu.com/article/3c48dd348bc005e10be358eb.html)
 * 安全很重要
    * root的弱口令,很快被攻破,成为挖矿机
    * 需要有人去研究,如何加固Ubuntu.(王老师)
 
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

 
 <br>
 
 ### Apache反向代理Tomcat
 
 有时候80端口被Apache占用,服务器上还有很多其他服务,例如tomcat node等.<br>
 用户输入80端口时候,可以自动转到tomcat的8080端口
 
 >注意事项
 
 * [Apache官方参考网站](http://httpd.apache.org/docs/2.4/howto/reverse_proxy.html)
 * [网友总结的方法](http://www.yyearth.com/article/17-08/156.html)
 * 更多信息,可以百度:ubuntu apache2 tomcat mods-available
 
 
 >配置步骤
 
 * 启动apache的proxy功能(必须用shudo 如果ln文件出现红色,那么就不能启动apache)
    * cd /etc/apache2/mods-available
    * sudo ln -s ./proxy.conf ../mods-enabled/
    * sudo ln -s ./proxy_http.load ../mods-enabled/
    * sudo ln -s ./proxy.load ../mods-enabled/
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
 
 > Apache的启动脚本
 
 * 做一些脚本放到/opt好操作
 
 * 启动脚本,友好的提示代理的tomcat
 ```youtrack
    #! /bin/bash
    echo 'start apache! '
    /etc/init.d/apache2 start
    echo 'http://www.wk.com proxy http://127.0.0.1:20180'
    echo 'http://wx.wk.com  proxy http://127.0.0.1:8080'
    echo 'http://127.0.0.1'
```
 
 * 检测apache的状态脚本

```youtrack
    #!/bin/bash
     
    # 判断apache是否启动
    # Author:James 2016-10-14
     
    # apache所在机器的IP(默认apache端口为80)
    ipport=127.0.0.1
     
    # 获取apache是否启动状态(通过-w全量匹配tcp的80端口)
    isopen=$(nmap $ipport| grep -w 80/tcp | grep http | awk '{print $2}')
     
    if [ "$isopen" == "open" ]
       then
            echo "Apache is runing..... $(date)   " 
       else
            echo "Apache is stop        $(date)   " 
    fi
``` 
 
 
 
 <br>
 
 
 ### 安装第二个Tomcat
 
 > 目的
 * 让Apache反向代理两个tomcat
 * 输入wx.wk.com被apache代理到第一个tomcat
 * 输入www.wk.com被apache代理到第二个tomcat
 
 > 目录规划
 * /opt下建立wk目录
 * wk目录与文件信息
   * wk.sql  数据库脚本
   * tomcat  悟空的web服务器
   * webapp  悟空的应用程序
   * wk.conf 悟空的配置文件,在apache下做ln
 * 这样做的目的是,今后好维护
 
 > 操作步骤
 * /opt下建立wk目录
    * sudo mkdir wk
 * 将tomcat 复制到wk目录下
    * sudo cp -r apache-tomcat-9.0.2/ /opt/wk/
 * 配置新的tomcat
    * 删除这个tomcat下无用webapp
    * 修改tomcat的默认端口
        * 端口记录在conf/server.xml
        * tomcat默认的端口是8080，还会占用8005，8009和8443端口
        * 修改/conf/server.xml
        * 端口修改成 20180 20105 20109 20143 
        * 今后以开头201,202,202增加(不能超过65536) 
        * TODO自动写一个变量,今后不用修改server.xml文件就好了.
    * 将工程指向webapp
        * <Context path="" docBase="../../webapp" debug="0" reloadable="true"/>    
        * 在webapp建立一个index.html,用来做测试.
    * 启动Tomcat,测试是否成功
        * http://127.0.0.1:20180/ 是否显示出相关页面
 * 配置Apache的反向代理
    * 在/opt/wk下,建立Apache的配置文件wk.conf
    ```xml
    <VirtualHost *:80>
    	ServerName www.wk.com
    	ProxyPass / http://127.0.0.1:20180/
    	ProxyPassReverse / http://127.0.0.1:20180/
    
    	ServerAdmin webmaster@localhost
    	DocumentRoot /var/www/html
    
    	ErrorLog ${APACHE_LOG_DIR}/wk_error.log
    	CustomLog ${APACHE_LOG_DIR}/wk_access.log combined
    </VirtualHost>
    
    ```
    * 在Apache中做ln链接,并启动Apache           
        * cd /etc/apache2/sites-enabled
        * sudo ln -s /opt/wk/wk.conf ./
        * sudo service apache2 restart
        * http://www.wk.com/ is result
 
 * 配置Tomcat的Shell脚本
    * 在/opt目录下建立,这样操作方便
    * /opt/tomcat-startup.sh 与 /opt/tomcat-shutdown.sh
```youtrack
    #! /bin/bash
    echo 'start 2  tomcat! '
    '/opt/wk/apache-tomcat-9.0.2/bin/startup.sh'
    '/home/fan/tomcat/apache-tomcat-9.0.2/bin/startup.sh'
    echo 'http://127.0.0.1:20180'
    echo 'http://127.0.0.1:8080'
```
 
 
 
 
 
 
 
 
 ## 部署程序
 
 * 使用ssh登录:ssh [-l login_name] [-p port] [user@]hostname
    * 例如:ssh -l root -p 22  47.95.33.158
 
 
 
 ## 关键点
 
 ### Https配置
 
 > 注意事项
 
 * [参照网址-包含如何申请](https://blog.csdn.net/gary_yan/article/details/77981303)
 
 * https://blog.csdn.net/tiercel2008/article/details/7726302
 
 * https://www.cnblogs.com/xuliangwei/p/8626139.html
 
 ```xml

```
 
 
 
 
 <br>