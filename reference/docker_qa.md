                                
[TOC]
## 1:我的笔记本能装吗?  4G 内存 i5处理器。
Docker本身只有10多M 需要的空间很小   只要是64位  内核 版本3.18以上（最新docker 版本1.9）就行 
## 2：的日常的环境是ubuntu，下面所有问题是基于ubuntu的.

## 3: 我在一天机器上安装两个docker环境，这两个docker环境中都有安装了tomcat，tomcat使用了8080端口。 我怎么访问到这两个tomcat通过浏览器。
[参考](https://blog.csdn.net/qq_29994609/article/details/51730640 "参考")

**docker指令：`docker run -p ip:hostPort:containerPort redis`**

使用-p参数会分配宿主机的端口映射到虚拟机。 
IP表示主机的IP地址。 
hostPort表示宿主机的端口。 
containerPort表示虚拟机的端口。

**支持的格式有三种：**

ip:hostPort:containerPort：映射指定地址的指定端口到虚拟机的指定端口（不常用） 
如：127.0.0.1:3306:3306，映射本机的3306端口到虚拟机的3306端口。 
ip::containerPort：映射指定地址的任意端口到虚拟机的指定端口。（不常用） 
如：127.0.0.1::3306，映射本机的3306端口到虚拟机的3306端口。 
hostPort:containerPort：映射本机的指定端口到虚拟机的指定端口。（常用） 
如：3306:3306，映射本机的3306端口到虚拟机的3306端口。
## 4: 每个docker环境，都有一个root用户,类似虚拟机，可以远程登录吗？
[参考](https://blog.csdn.net/u010397369/article/details/41045251 "参考")
* 推荐 `sudo docker exec -it 775c7c9ee1e1 /bin/bash`

## 5: 怎么在ubuntu安装docker，并从docker hub 安装不同的镜像。
[参考](https://blog.csdn.net/dante_003/article/details/70208908 "参考")
```
##下载并安装docker
curl -sSL https://get.daocloud.io/docker | sh 
##修改docker镜像地址，官方的镜像库连接太慢，这里转到daocloud镜像库。 
curl -sSL https://get.daocloud.io/daotools/set_mirror.sh | sh -s http://91c0cc1e.m.daocloud.io 
## 启动docker服务，并设置开机启动 
systemctl enable docker.service && service docker start
```

## 6:  我自己能建立镜像服务器吗？
[参考](https://blog.csdn.net/ronnyjiang/article/details/71189392 "参考")

## 7： 我自己怎么能生成一个镜像。里面有： nginx tomcat redis java mysql 还有我自己的程序。
[参考](https://blog.csdn.net/birdben/article/details/49877549 "参考")

## 8： 两个docker镜像怎么相互访问。
[参考](https://yq.aliyun.com/articles/55912 "参考")
* docker --link

## 9： docker有什么常用的工具吗？

## 10: 现在有没有关于tensorflow的docker镜像吗？
[daocloud](http://hub.daocloud.io/repos/916d06d3-db06-4802-b632-328e5c991373 "daocloud")

## 11: docker是否包含操作系统，例如 ubuntu cerntos等
包含不同操作系统环境，但实际运行机制与虚拟机不同

## 12：找个时间帮我安装一个 ubuntu16.04操作系统，另外在上面安装一个docker的测试环境。


##  

* [安装Typora](https://blog.csdn.net/mutilcam_prince/article/details/78217176?locationNum=8&fps=1)


* [用国内地址一键安装docker](https://blog.csdn.net/dante_003/article/details/70208908)


* [xiuga]
                            