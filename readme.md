wukong-framework
===

gitlab


`springboot2.0` `springsecurity` `testng`  `mybatis` `jwt` `https` `redis`





## 快速掌握

* [环境配置](reference/readme.md "开打环境配置文档")
    * [开发环境配置](reference/readme.md "开打环境配置文档")
    * [服务器环境配置](reference/webEnvironment.md "开打环境配置文档")
    * [服务器安全配置](reference/webSecurity.md)
    * [部署到服务器上](reference/ci.md "部署到服务器上")

* 基础开发
    * [如何撰写API接口](reference/controller.md)
    * [如何撰写数据库操作](reference/database.md)
 
* 单元测试
    * [如何使用TestNG测试](reference/testng.md)
    * [如何使用postMan测试](reference/postman.md)
    * [如何使用swagger进行测试](reference/swagger2.md)

* 安全
    * [security功能说明](reference/security.md)
    * [如何配置权限](reference/security-config.md)
    * [防止重复提交](reference/preventrepeat.md)
    * [关闭与开启security认证](reference/tip.md) `见·关闭security认证·小节`
    


* 其他
    * [如何记录log](reference/uselog.md)
    * [如何使用Redis缓存](reference/redis.md)
    * [消息队列使用](reference/mq.md)
    * [如何使用elasticsearch](reference/elasticsearch.md)
    * [SpringBoot使用小技巧](reference/tip.md)
    * [bugs一些错误](reference/bugs.md)
    * [gitlab使用](reference/gitlab.md)
    * [Https配置](reference/https.md)


<br><br>


## 设计参考

* [变更记录](reference/log.md "开打变更记录文档")
* [core库说明](wukong-core/readme.md )
* [security库说明](wukong-security/readme.md )
* [examples项目说明](wukong-examples/readme.md )
* [代码生成器说明](wukong-generator/readme.md )



## next todo list

* 代码中的http转https没有意义，应该在nginx上配置，如果配置成功，删除这部分代码。
* .ssh gitlab中conf起作用后，ssh无法远程登录，下周问马宇航



