# git 使用规范

> 目录

* [提交代码流程规范](#提交代码流程规范)
* [commit注释规范](#commit注释规范)


<br>


## 提交代码流程规范

### 在提交meger request前，应该从服务器pull代码，然后在本地rebase后，再push

> 流程如下图：

![alt](imgs/git_specification.png) 


<br>

## commit注释规范


### 动词+修改内容

一般情况下，提交 GIT 时的注释可以分成几类，可以用几个动词开始：

    add    ( 新加内容 )
    bugfix ( 修复bug )
    modify ( 修改原有内容 )
    delete ( 删除了某些 )


假如有 Issues 系统，其中可以包含 Issue 的 ID。

    比如：Issue #123456



例子

    add 开放平台验证接口, /OAuth/access_token,/OAuth/refresh_token 
    modify Token类，修改为使用子类继承，支援多种Token调用 
    modify Timer类增加ACCESS_TOKEN_TIMER常量 
    bugfix 验证码短信文字乱码问题 
    delete Model/UserType.php
