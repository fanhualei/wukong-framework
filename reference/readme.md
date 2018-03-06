#Wukong framework Documentation<br> 


## 环境配置

<br>

>快速搭建开发环境
    
    关键步骤
    1:idea 2017.3+
    2:idea 安装 lombok插件
    3:idea jdk1.8 (不能是1.9)
    4:mysql 5.7.21+ 安装
    5:redis 安装
    6:idea 从github上下载代码(需要注册github)
    7:idea 刷新pom.xml并build整个工程
    8:mysql 中建立db，并执行schema.sql(wukong-security/src/main/resources/dbsql目录下)
    9:application-db.properties 修改数据库的名称与密码(wukong-examples/src/main/resources/目录下)
    10:右键点击 DemoApplicaiton Run, 执行整个程序
    11: 打开浏览器，输入https://localhost:8443/author/jwt/login?username=admin&password=admin，系统显示出一个字符串
          

 