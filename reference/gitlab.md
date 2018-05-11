# gitLab使用

> 目录

* gitlab安装

* [gitlab反向代理](#gitlab反向代理)

* [阿里云通过命令开放端口](https://www.aliyun.com/jiaocheng/153471.html)




## gitlab反向代理


### 修改配置文件

* sudo vim  /var/opt/gitlab/nginx/conf/gitlab-http.conf
* listen *:19180;
    * 将 80 端口变成 19180
* sudo gitlab-ctl restart
    将gitlab重启
 
### 修改apache的方向代理

```xml
<VirtualHost *:80>
        ServerName gitlab.runzhichina.com
        ProxyPass / http://127.0.0.1:19180/
        ProxyPassReverse / http://127.0.0.1:19180/

        ServerAdmin webmaster@localhost
        DocumentRoot /var/www/html

        ErrorLog ${APACHE_LOG_DIR}/gitlab_error.log
        CustomLog ${APACHE_LOG_DIR}/gitlab_access.log combined
</VirtualHost>



### 还是访问不了,看看是不是阿里端口出现错误
```    
    