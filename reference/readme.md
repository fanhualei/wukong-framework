# 环境配置

> 目录

* [快速搭建开发环境](#快速搭建开发环境)
* [服务器环境配置](#服务器环境配置)
    * [Web环境配置](#Web环境配置)
    * [负载均衡配置](#负载均衡配置)
    * [模块二级域名分配](#模块二级域名分配)
    * [安全设置](#安全设置)
    * [日志与备份](#日志与备份)
    * [数据库负载](#数据库负载)
* [部署程序](#部署程序)
    * [Maven自动部署](#maven自动部署)
    * [数据库结构升级](#数据库结构升级)    
    * [系统回滚](#系统回滚)


<br>

## 快速搭建开发环境
    
> 关键步骤

* 1:idea 2017.3+
* 2:idea 安装 lombok插件
* 3:idea jdk1.8 (不能是1.9)
* 4:mysql 5.7.21+ 安装
* 5:redis 安装
* 6:idea 从github上下载代码(需要注册github)
* 7:idea 刷新pom.xml并build整个工程
* 8:mysql 中建立db，并执行schema.sql([reference/sql目录下](sql/security/schema.sql))
* 9:application-db.properties 修改数据库的名称与密码(wukong-examples/src/main/resources/目录下)
* 10:右键点击 DemoApplicaiton Run, 执行整个程序
* 11: 打开浏览器，输入下面地址,系统显示出一个字符串<br>
      https://localhost:8443/author/jwt/login?username=admin&password=admin
          

 <br>
 
 ## 服务器环境配置
 
 <br>
 
 ## 部署程序
 
 
 
 <br>