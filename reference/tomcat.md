# tomcat 使用


> 目录

* [安装tomcat](#安装tomcat)
* [安全配置](webSecurity.md#tomcat安全配置)
* 其他技巧
    * 批处理多个tomcat
    * 做成服务自启动
    * tomcat状态的监控
    
    
## 安装tomcat
 
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