# 代码分支说明文档

> 本文档主要记录了当前代码的分支管理情况



## 主分支

> master 分支

* 工程中最新的代码


> 1.1.x 分支(未发布)

* 这个分支是本次开发的一个里程碑，会固定一个稳定的版本。


> 0.9.x 分支 (备份代码用)

* 这是2018-5-23为了本分当前代码做的分支
* 备份的原因，这个分支里面的代码不实用，例如：
    * http 转 https
    * 全局的返回格式
    * 全局的错误异常处理
* 所以在删除这些代码前，我准备做个分支，备份一下。

<br>

* 注意这个分支只能参考，不能取。    









## 开发分支

* 自己定义的分支，今后如果人数多的话，这些分支要合并




## 参考资料


> idea 创建分支

* 很简单，在idea branches中new 一个分支就行，然后就push到服务器上。
* 创建完分支，新建立分支成为idea当前分支，这样就要切回原先的分支。

> idea 切换分支

* 看下图，checkout就可以了

![alt](imgs/gitlab_change_branch.png)


> 网上资料

* [idea创建git分支](https://blog.csdn.net/feicongcong/article/details/76431471)
* [IDEA git新建分支tag标签](https://jingyan.baidu.com/article/656db918ca023fe380249c68.html)

