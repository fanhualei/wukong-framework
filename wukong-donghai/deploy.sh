#!/bin/bash


echo "hello world,I will deploy app to webserver !!!"


echo $1

scp -P 1422  -r $1 root@47.92.0.57:/opt/wk/

echo "==========upload file ok================="

ssh -l root -p 1422 47.92.0.57 "cd /opt/wk/; pwd ; ./deploy_server.sh"

echo "==========run server sh ok================="


#上传文件
#scp -P 1422  -r wukong-donghai-1.1.RELEASE.war root@47.92.0.57:/opt/wk/
#执行完毕输出：wukong-donghai-1.1.RELEASE.war                100%   45MB 450.6KB/s   01:42
# deploy.sh     target/wukong-donghai-1.1.RELEASE.war
# ssh -l root -p 1422 47.92.0.57


