wukong-framework
===

gitlab


`license:LGPL` `springboot2.0` `springsecurity` `testng`  `mybatis` `jwt` `https` `redis`





## 快速掌握

* 环境配置
    * [开发环境配置](reference/readme.md "开打环境配置文档")
    * [服务器环境配置](reference/webEnvironment.md "开打环境配置文档")
    * [Https配置](reference/https.md)
    * [服务器安全配置](reference/webSecurity.md)
    * [部署到服务器上](reference/ci.md "部署到服务器上")

* API接口开发
    * [基本API用法](reference/controller.md)
    * [返回格式及异常处理](reference/result.md)
    * [数据校验](reference/validator.md)
    * [配置访问权限](reference/controller.md#配置访问权限)
    * [一个完整的代码例子](reference/myfirst_controller.md)
    * [i18n多语言](reference/i18n.md)
    * [中文乱码的问题](reference/encoding.md)
    * [HTTP状态码对照表](http://tools.jb51.net/table/http_status_code)

    
* 单元测试
    * [如何使用TestNG测试](reference/testng.md)
    * [如何使用postMan测试](reference/postman.md)
    * [如何使用swagger进行测试](reference/swagger2.md)
        
    
* 数据库开发    
    * [Mysql使用](reference/mysql.md)
    * [如何撰写数据库操作](reference/database.md)
 

* 安全
    * [security功能说明](reference/security.md)
    * [如何配置权限](reference/security-config.md)
    * [防止重复提交](reference/preventrepeat.md)
    * [关闭与开启security认证](reference/tip.md) `见·关闭security认证·小节`

* 规范
    * 开发人员的规范
        * [java开发规范](reference/specification_java.md)
        * [记录log规范](reference/uselog.md)
        * [git提交代码规范](reference/specification_git.md)
        * [属性参数规范](reference/specification_properties.md)
        * [redis-key命名规范](reference/redis.md#redis-key命名规范)  
        * [controller与service命名规范](reference/result.md#悟空框架api返回规范)
        * [Java与Mysql数据对应关系](reference/mysql_java.md)
        * [alibaba Idea代码检查插件安装](https://github.com/alibaba/p3c/blob/master/idea-plugin/README_cn.md)
        * [所有String操作不能自己写，用现成的](https://blog.csdn.net/tzhai27/article/details/75351861)
        * [DateUtils](https://blog.csdn.net/softwave/article/details/75808421)
        * [其他](reference/coding_standards.md)
    * 服务器部署人员的规范
        * [代码分支规范](reference/gitlab_branch.md)
        * [服务器端口规范](reference/specification_server.md#端口规范)
        * [docker命名规范](reference/specification_server.md#docker命名规范)
        * [tomcat工程目录规范](reference/specification_server.md#tomcat工程目录规范])



* 持续集成
    * [使用gitLab提交代码](reference/gitlab.md)
    * [gitlab niginx方向代理实例](reference/gitlab_apache.md)
    * [gitlab常用命令及运维](reference/gitlab_opt.md)

   
* docker使用
    * [docker基本用法](reference/docker.md)
    * [dockerQ&A](reference/docker_qa.md)
    
* linux常用技巧
    * [linux常用命令](reference/cmd.md)
    * [ssh免密码登录](reference/cmd.md#ssh免密码登录)
    
    
* tomcat使用技巧
    * [判断tomcat是否启动](reference/tomcat.md#判断tomcat是否启动)
    * [tomcat添加成自启动](reference/tomcat.md#tomcat自启动)
    * [热加载](reference/tomcat.md#热加载)   
    * [其他](reference/tomcat.md)    
    
    



* 其他
    * [如何使用Redis缓存](reference/redis.md)
    * [RocketMQ使用](reference/mq.md)
    * [如何使用elasticsearch](reference/elasticsearch.md)
    * [SpringBoot使用小技巧](reference/tip.md)
    * [bugs一些错误](reference/bugs.md)
    * [maven 使用技巧](reference/maven.md)
    * [shell脚本相关技巧](reference/sh.md)
    * [地图相关信息](reference/map.md)
    * [Springboot idea调试热部署](https://blog.csdn.net/u013042707/article/details/78648259)
    * [一张图看懂开源许可协议,开源许可证GPL、BSD、MIT、Mozilla、Apache和LGPL的区别](https://blog.csdn.net/testcs_dn/article/details/38496107)
    


<br><br>


## 设计参考

* [变更记录](reference/log.md "开打变更记录文档")
* [core库说明](wukong-core/readme.md )
* [security库说明](wukong-security/readme.md )
* [examples项目说明](wukong-examples/readme.md )
* [代码生成器说明](wukong-generator/readme.md )



## next todo list


* 持续化集成需要有人做一下
* 性能测试需要有人研究一下
* 需要有人研究一下树型结构的编码e
* 马宇航在gitlab上配置plantuml
    * [GitLab 之 PlantUML 的配置及使用](https://blog.csdn.net/aixiaoyang168/article/details/76888254)



* http://wx.runzhichina.com 那个悟空图片的图标没有扣除背景，需要有人去再做一个

* 第一个例子, 清空所有数据， 添加5个student。 ,然后查询这100个，然后更新其中一个，然后删除。

