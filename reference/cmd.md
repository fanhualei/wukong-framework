# linux常用命令


* [vim](#vim)
* [压缩与解压](#压缩与解压)
* [文件上传与下载](#文件上传与下载)
* [文件目录操作]()
* [权限操作]()
* [远程登录操作]()

<br>


## vim 

> 基本操作

* 复制
    * 单行复制
        * 在命令模式下，将光标移动到将要复制的行处，按“yy”进行复制；
    * 多行复制
        * 在命令模式下，将光标移动到将要复制的首行处，按“nyy”复制n行；其中n为1、2、3……
    * 粘贴
        * 在命令模式下，将光标移动到将要粘贴的行处，按“p”进行粘贴         

* 删除
    * dd:删除游标所在的一整行(常用)
    * d1G:删除光标所在到第一行的所有数据
    * dG:删除光标所在到最后一行的所有数据
    * d$:删除光标所在处，到该行的最后一个字符
    * d0:那个是数字0,删除光标所在到该行的最前面的一个字符
    * [其他不常用](https://blog.csdn.net/chenyoper/article/details/78260007)

> 参考文档


## 压缩与解压

> 基本操作

* 解压
    * tar zxvf FileName.tar.gz
* 压缩
    * tar zcvf FileName.tar.gz DirName    


> 参考文档

* [Ubuntu下各种压缩与解压的方式小结](http://www.jb51.net/article/112207.htm)



## 文件上传与下载

> 基本操作

* 文件上传
    * scp  -P 1422  -r /opt/jdk1.8.0_161.tar.gz  root@47.92.10.57:/opt/
        * 将本地文件,通过1422端口上传到服务器上

* 文件下载
    * scp   -P 1422 -r root@47.92.10.57:/opt/readme.txt ./
        * 将远程服务器的readme下载到本地

> 参考文档

* [ubuntu SSH 连接、远程上传下载文件](https://www.cnblogs.com/qinduanyinghua/p/7152812.html)
* [scp命令需要指定端口时要紧跟在scp后](https://www.cnblogs.com/jixingke/p/6213074.html)










