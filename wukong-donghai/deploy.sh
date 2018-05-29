echo "hello world,I will deploy app to webserver !!!"


echo $1



#上传文件
#scp -P 1422  -r wukong-donghai-1.1.RELEASE.war root@47.92.0.57:/opt/wk/

scp -P 1422  -r $1 root@47.92.0.57:/opt/wk/

#执行完毕输出：wukong-donghai-1.1.RELEASE.war                100%   45MB 450.6KB/s   01:42


