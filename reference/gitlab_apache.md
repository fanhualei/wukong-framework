# gitlab 与 apache 同时部署在一台服务器


gitlab自带nginx

方案2失败

> 目录

* [方案1:gitlab监听80并方向代理apache](#方案1:gitlab监听80并方向代理apache)
    * [修改apache端口80->19180](#修改apache端口80->19180)
    * [gitlab的nginx设置方向代理指向apache](#gitlab的nginx设置方向代理指向apache)
    * [重启并进行测试](#重启并进行测试)
* [方案2:apache监听80并方向代理gitlab](#方案2:apache监听80并方向代理gitlab)
    * [修改gitlab的nginx端口80->19180](#修改gitlab的nginx端口80->19180)
    * [apache配置反向代理指向gitlab的nginx](#apache配置反向代理指向gitlab的nginx)
    * [重启后进行测试](#重启后进行测试)
        * 失败出现505错误
* [参考网址](#参考网址)
        
<br>


## 方案1:gitlab监听80并方向代理apache

### 修改apache端口80->19180


> 修改conf的接口
* sudo vim /etc/apache2/ports.conf

```xml

Listen 19180

<IfModule ssl_module>
        Listen 19143
</IfModule>

<IfModule mod_gnutls.c>
        Listen 19143
</IfModule>

```
<br>

> 修改各个虚拟主机的借口
* sudo vim /etc/apache2/sites-available/000-default.conf     

```properties
# fanhualei modiyf 80->19180

<VirtualHost *:19180>

```

### gitlab的nginx设置方向代理指向apache

> 建立一个apache.conf的文件,并在nginx.conf中incliude

* apache.conf文件的配置,指向apache的19180端口

````properties
server {
  listen 80;
  server_name wx.runzhichina.com;
  server_tokens off;
  ## Don't show the nginx version number, a security best practice

  location / {
    proxy_set_header   X-Real-IP $remote_addr;
    proxy_set_header   Host      $http_host;
    proxy_pass  http://127.0.0.1:19180;
  }

}
````

<br>

* 在nginx.conf中incliude

```properties
  include /var/opt/gitlab/nginx/conf/nginx-status.conf;
  #fanhualei add
  include /var/opt/gitlab/nginx/conf/apache.conf;
```


### 重启并进行测试

> http://wx.runzhichina.com/  看到apache的首页
> http://gitlab.runzhichina.com  看到gitlab的首页


<br>


## 方案2:apache监听80并方向代理gitlab

### 修改gitlab的nginx端口80->19180 

* sudo vim  /var/opt/gitlab/nginx/conf/gitlab-http.conf
    * listen *:19180;
        * 将 80 端口变成 19180
* sudo vim /etc/gitlab/gitlab.rb
    * nginx['listen_prot']=80>19180        
* sudo gitlab-ctl restart
    将gitlab重启
    
    
### apache配置反向代理指向gitlab的nginx

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
```

### 重启后进行测试

> http://wx.runzhichina.com/  看到apache的首页
> http://gitlab.runzhichina.com  看到505错误页面
>> http://gitlab.runzhichina.com:19180可以看到gitlab首页
 
        
## 考网址        


* [阿里云通过命令开放端口](https://www.aliyun.com/jiaocheng/153471.html)
* [Ubuntu 修改Apache2端口](https://blog.csdn.net/haitunmin/article/details/74931617)
* [Ubuntu下 Nginx 反向代理 Apache、Tomcat](https://www.aliyun.com/jiaocheng/125698.html)