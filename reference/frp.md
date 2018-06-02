# frp 指南

今后可以通过树莓派，来吧家里的机器开放出来

> 目录


* 功能说明
    * 通过ssh访问内网机器
    * token认证
    * 通过http访问内网机器(@todo)
    

* 具体配置
    * [常用命令](#常用命令)
    * [阿里云上需要开放的端口](#阿里云上需要开放的端口)
    * [ini配置](#ini配置)
        * [服务器端ini配置](#服务器端ini配置)
        * [客户端ini配置](#客户端ini配置)




## 具体配置

当前所有都是在ubuntu上安装

### 常用命令

#### 基本命令

    #启动 frps
    ./frps -c ./frps.ini
    
    #启动 frpc
    ./frpc -c ./frpc.ini
    
    #通过ssh访问内网机器
    ssh -oPort=6000 test@x.x.x.x
    
    #客户端热加载配置文件
    frpc reload -c ./frpc.ini
    
#### 防止关闭命令行窗口frp服务停止

> 远程登录服务器 启动 frp 当关闭窗口，frp 服务停止

##### 可以用nohup命令，后台启动frp

    # 进入后台要先查查这个启动了吗
    ps -ef|grep frp
    nohup ./frps -c ./frps.ini &
    

### 阿里云上需要开放的端口

* 17000端口:server上全局的端口
* 16000端口:某个内网机器ssh代理的接口
    * 如果内网有n个机器，就需要在服务器开放16000~1600n个接口。
    * 还有一种实现方法，通过nginx进行反向代理，然后16000~1600n接口不向外开放

### ini配置

#### 服务器端ini配置

```properties
[common]
bind_port = 17000
token = 654321
```

#### 客户端ini配置

```properties
# frpc.ini
[common]
server_addr = x.x.x.x
server_port = 17000

[ssh]
type = tcp
local_ip = 127.0.0.1
local_port = 22
remote_port = 16000 #服务器端的端口
```


> 参考文档
* [官网文档](https://github.com/fatedier/frp/blob/master/README_zh.md)
* [使用frp实现内网穿透](https://www.jianshu.com/p/e8e26bcc6fe6)