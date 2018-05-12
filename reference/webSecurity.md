# Web Server 安全配置


> 目录

* os安全配置
    * [变更22端口](#变更22端口)

* apache安全配置
    * [关闭目录检索](#关闭目录检索)
    * [不显示版本号与端口号](#不显示版本号与端口号)
    * [apache相关资料](#apache相关资料)

* tomcat安全配置

* nginx安全配置

* redis安全配置

* mysql安全配置

* gitlab安全配置

<br>


## os安全配置

* 密码一定不能通过聊天工具和email进行发送.
* 密码与用户名或地址,分别通过不同的途径发送

### 变更22端口

* sudo vim /etc/ssh/sshd_config
    * 例如将22变更成1422
* service sshd restart
    * 重启服务
    
## apache安全配置

### 关闭目录检索

* 修改httpd.conf
    * vim /etc/apache2/apache2.conf

> 去掉 Indexes检索命令       
```xml
<Directory /var/www/>
     ##  fanhualei modify
     ## Options Indexes FollowSymLinks
        Options FollowSymLinks
        AllowOverride None
        Require all granted
</Directory>

```

### 不显示版本号与端口号

* 修改conf-enabled/security.conf
    * vim /etc/apache2/conf-enabled/security.conf 
        *ServerTokens OS -> ServerTokens OS 
        *ServerSignature On -> ServerSignature On

 
### apache相关资料
[确保Apache Web服务器安全的8种安全设置技巧](http://www.laozuo.org/5503.html)
