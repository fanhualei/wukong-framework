# 部署到服务器

> 目录

* [手工部署](#手工部署)
* [Maven部署](#maven部署)
* [GitLab持续集成](#gitlab持续集成)
* [Docker部署](#docker部署)



## 手工部署

### 到Idea代码生成目录,将代码复制到webapp中
    
* 例如:/IdeaProjects/wukong-framework/wukong-examples/target/wukong-examples-1.1.RELEASE

```youtrack
sudo chown fan /opt/wk/webapp
cd /opt/wk/webapp
jar -xvf /home/fan/IdeaProjects/wukong-framework/wukong-examples/target/wukong-examples-1.1.RELEASE.war

```

### 启动Tomcat与Apache

### 测试

* 测试Tomcat http://127.0.0.1:20180/hello
* 测试Apache http://www.wk.com/hello

<br>


## Maven部署

> 通过maven，自动打包并将程序部署到远程服务器上


### 在maven中添加plugin，调用[上传服务器的shell脚本文件](#上传war文件到服务器)

````xml
<build>
         <plugins>
             <plugin>
                 <artifactId>exec-maven-plugin</artifactId>
                 <groupId>org.codehaus.mojo</groupId>
                 <executions>
                     <execution>
                         <id>uncompress</id>
                         <phase>package</phase>
                         <goals>
                             <goal>exec</goal>
                         </goals>
                         <configuration>
                             <executable>${basedir}/wukong-donghai/deploy.sh   </executable>
                             <arguments>${basedir}/wukong-donghai/target/wukong-donghai-1.1.RELEASE.war</arguments>
                         </configuration>
                     </execution>
                 </executions>
             </plugin>
         </plugins>
</build>
````

### 上传war文件到服务器，并远程调用[服务器上的自动部署shell脚本](#服务器上的自动部署shell脚本)

> 自动将$1文件上传到服务器/opt/wk目录，并执行服务器上的部署脚本，这里用到三个知识点

* [无密码ssh登录远程服务器](cmd.md#ssh免密码登录)
* [远程执行服务器脚本](https://www.cnblogs.com/sparkdev/p/6842805.html)


```youtrack
#!/bin/bash
echo $1
scp -P 1422  -r $1 root@47.92.0.57:/opt/wk/
ssh -l root -p 1422 47.92.0.57 "cd /opt/wk/; pwd ; ./deploy_server.sh"
```

> [shell代码](../wukong-donghai/deploy.sh)


### 服务器上的自动部署shell脚本

    * 上传war到 /opt/wk
    * 停止tomcat
    * 备份原有的webapp程序 到 /opt/wk/backup目录
    * 删除原有的webapp目录下的两目录，但是index.html与version.html保留
    * 加压war到webapp
    * 备份war包
    * 将更新结果写入webapp/version.html
    * 重启tomcat


> [shell代码](sh.md#服务器自动部署shell脚本)



<br>

## GitLab持续集成

<br>

## Docker部署