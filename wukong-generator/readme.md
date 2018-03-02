
参考的例子


### 建议使用注解形式


Mybatis Generator最完整配置详解
http://blog.csdn.net/testcs_dn/article/details/77881776

官方文档
http://www.mybatis.org/generator


快速入门

    1、打开generatorConfig.xml ，按照mybatis的配置进行配置
    2、下面代码是经过特殊配置的
            <javaClientGenerator type="XMLMAPPER" targetPackage="com.wukong.examples.dao"  targetProject="log">
                <property name="enableSubPackages" value="true" />
                <property name="prefix" value="wk_"/>
                <property name="ignores" value=""/>
                <property name="targetPackage" value="com.wukong.examples.service"/>
                <property name="targetProject" value="log"/>
                <property name="runWukongRule" value="true"/>
            </javaClientGenerator>
    3、preifx 去掉数据库的前缀
      ingores 要忽略掉的表名字
      targetPackage service的包名
      targetProject 生成的路径，例如log会在程序的更目录下的log进行生成
      runWukongRule=true 是按照代码的功能生成文件，并清空xml中table配置
                   =false按照mybatis的配置生成
                   
    4、运行 WukongGenerator ,并生成代码              


TODO

    1、要测试一下这样的表 wk_order_detail生成对象的名称，以及wk_orderDetail
    2、今后多谢谢代码，把Service代码生成器一步一步完善一下。
    3、今后生成Swagger，用来生成API自动化测试界面。


代码说明：

    java
        WukongGenerator.java
        backup --备份文件的目录
        mybatis --为mybatis xml配置文件，查询数据库中的表，并添加到context中
            plugins --mybatis插件类，一个实验的类
            TableInfo.java --存储表的信息
            TablesInDatabase.java  --得到数据库所有表的信息
        service --为生成service的类
            DaoBeanInfo.java --Dao生成的代码信息
            ServiceConfig.java -- Service控制配置信息
            ServiceGenerator.java --用来生成Service的工具类
            ServiceImpInfo.java   --Service生成的代码信息
        util    --工具类，得到路径，得到锁紧
    resources
        application.properties
        generatorConfig.xml
        mysql-connector-java-5.1.45-bin.jar    

 
   
    
配置技巧:有时候不好用
   
    1、备注的注释方法
       数据库表中的表和列备注将被添加到生成的注释中。
    <commentGenerator>
      <property name =“addRemarkComments”value =“true”/>
    </ commentGenerator>
    
    2、去掉表的前缀,在Table中添加下面代码
    <domainObjectRenamingRule searchString =“^ Sys”replaceString =“”/>
    