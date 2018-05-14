# Mysql使用

>todo 

* 我用apt安装的是5.7.22先凑合用吧.
* 现在msyql新出的8,听说不错,半年后看看评论再用

>目录

* [安装](#安装)
* [安全配置](#安全配置)
* [mysql常用命令](#mysql常用命令)
* [本地数据库恢复到服务器](#本地数据库恢复到服务器)
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
    



## mysql常用命令


* mysql -u root -p
* show databases;    //检查是否有test这个数据库
* use test;
* show tables;      

[在ubuntu下mysql的简单操作教程](https://www.2cto.com/database/201710/692925.html)








    
## 本地数据库恢复到服务器

在本地开发环境的数据库,回复到测试服务器

> 参考资料

* [ubuntu mysql 数据库备份以及恢复命令行](https://www.cnblogs.com/zhaoxiwang/p/8023919.html)    
        

> 执行步骤

* 本地执行
    * mysqldump -u 用户名 -p 数据库名 > 导出的文件名
        * mysqldump -u root -p wukong_write > wukong_write.sql
    * 如果提示错误编号是1045,那可能是密码输入错误
   

* 服务器执行
    * 上传到服务器上
        * scp  -P 1422  -r ./wukong_write.sql  root@47.92.10.57:/opt/wk
    * 登录到服务上执行
        * 如果没有wukong_write，那么需要新建立数据
            * create database wukong_write;
        * @TODO 新建立的数据库，默认数据集与事物处理怎么弄的？
        * mysql -u root -p wukong_write < ./wukong_write.sql
    * 进入mysql命令行,查看数据
        * mysql -u root -p
        * show databases;
        * use  wukong_write;
        * show tables;
        * select * from wk_role;
           
    
    

    
    


          
    


