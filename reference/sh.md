# 重要的shell脚本


> 目录

* [服务器端更新脚本](#服务器端更新脚本)




## 服务器端更新脚本

> 操作步骤

* 上传war到 /opt/wk
* 停止tomcat
* 备份原有的webapp程序 到 /opt/wk/backup目录
* 删除原有的webapp目录下的两目录，但是index.html与version.html保留
* 加压war到webapp
* 备份war包
* 将更新结果写入webapp/version.html
* 重启tomcat


![alt](imgs/sh_deploy_server.png)


### sh脚本说明

```youtrack
./apache-tomcat-9.0.2/bin/shutdown.sh


#要求得到时间的名称    

datename=$(date +%Y%m%d_%H%M)
savefilename="backup/save_${datename}.zip"
echo $savefilename

echo " " >> webapp/version.html
echo   "================${datename} start===============" >> webapp/version.html



#备份原有的程序

zip -r $savefilename webapp

echo " " >> webapp/version.html
echo   "backup ok: ${savefilename} " >> webapp/version.html


#删除原有的内容
rm -rf  ./webapp/META-INF/
rm -rf  ./webapp/WEB-INF/

#解压到webapp
unzip wukong-donghai-1.1.RELEASE.war -d webapp/

echo " " >> webapp/version.html
echo   "deploy ok: wukong-donghai-1.1.RELEASE.war " >> webapp/version.html


#备份安装包
mv  wukong-donghai-1.1.RELEASE.war  backup/wukong-donghai1.1.RELEASE${datename}.war

echo " " >> webapp/version.html
echo   "delete wukong-donghai-1.1.RELEASE.war ok " >> webapp/version.html


#启动tomcat


echo " " >> webapp/version.html
echo   "----------------${datename} end----------------" >> webapp/version.html

./apache-tomcat-9.0.2/bin/startup.sh



```