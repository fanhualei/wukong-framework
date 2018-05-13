# Mysql使用

>todo 

* 我用apt安装的是5.7.22先凑合用吧.
* 现在msyql新出的8,听说不错,半年后看看评论再用

>目录

* [安装](#安装)
* [安全配置](#安全配置)
* 建立数据库
* 备份与恢复
* 负载均衡


## 安装

> 参考文档

* [Linux(Ubuntu)下MySQL的安装与配置](https://www.cnblogs.com/boshen-hzb/p/5889633.html)
* [](http://www.linuxidc.com/Linux/2017-06/144805.htm)


> 安装的步骤

* apt update
    * 更新安装列表
* netstat -tap|grep mysql
    * 查看是否安装过mysql ,当然换成apache2与nginx也可以
* apt install mysql-server mysql-client
    * 进行安装,安装过程要输入密码
* netstat -tap|grep mysql
    * 查看安装是否成功
    
> 常用命令

* 登录
    * mysql -u root -p
* 启动与停止mysql (新版的使用mysql替代mysqld)
    * service mysql start
    * service mysql stop
    * service mysql restart
    

## 安全配置

基本上不用什么安全配置,因为默认只能本机访问.


> 基本知识
* mysql的默认端口是 3306
* [MySQL目录说明](https://www.cnblogs.com/dpf-learn/p/7513025.html)
* 查看mysql版本
    * mysql -V
* 默认只能本机访问
    * bind-address = 127.0.0.1
        
    
    


          
    


