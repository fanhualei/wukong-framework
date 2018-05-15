# gitLab

> 目录

* 服务器配置
    * 安装
    * 安全配置
* [gitLab使用](#gitLab使用)
    * [设置ssh key](#设置ssh key)
    * todoList    
    * ci持续集成
    * uml集成
    * wiki
    
    
    
## gitLab使用


### 设置ssh key

使用ssh提交代码，比较安全

> 主要步骤

* 为gitlab生成key
    * ssh-keygen -t rsa -C 'fanhl@189.cn' -f ~/.ssh/gitlab-rsa
* 将gitlab-rsa.pub的内容复制到gitlab网站上
    * cat ~/.ssh/gitlab-rsa.pub
* 在iead里面配置秘钥



> 参考网址

* [GitLab配置ssh key](https://www.cnblogs.com/hafiz/p/8146324.html)

    