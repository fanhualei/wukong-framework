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

### add exec code in pom.xml

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

#### add sh file to deploy 

```youtrack
echo "hello world,I will deploy app to webserver !!!"

echo $1

echo "I'will deploy this file to server bu ssh!!!"
```





<br>

## GitLab持续集成

<br>

## Docker部署