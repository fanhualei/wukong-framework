# 系统属性配置规范


所有的属性，都必须有默认值<br>
属性名=wukong.projectname.功能名

> 目录

* [框架层属性](#框架层属性)

* [应用层属性](#应用层属性)

* [springboot属性]()



## 框架层属性

属性 | 默认值 |   引用模块 |  说明 | 
--------- | --------| --------| --------|
wukong.security.permiturls=/hello,/wechat/**  | 空字符串 | security模块  | 配置不受权限控制的url  | 
wukong.swagger.basePackage=com.wukong.examples.controller  | com.wukong.*.controller  | core模块   |  swagger2扫描的包  | 
wukong.web.upload.path=/home/fan/IdeaProjects/wukong-framework/log/upload/| upload/ | 业务模块  | 文件上传与下载路径  |