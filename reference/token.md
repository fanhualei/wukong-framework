## jwt Token 的实现技巧


<br>

> 关键点

    1: Token的命名规则与redis存储技巧
    2: Token校验规则
    3: Token往redis追加与清空的规则
    4: redis存储技巧
    
    

<br>


### Token的命名规则

    例如：wukong:security:token:1:dkdsdsds
    wukong+security+{userid}+{create}
    {userid}=当前用户id
    {create}=生成tonken的时间
    
    好处：同一个用户的每个客户端，登录一次就会有一个token，互不干扰。
    坏处：token比较多(解决方案，今后可以做个定时清空)


### Token校验规则

    +与redis中的对比
        1:与redis存储的是否一致
        2:是否过期
    +与当前用户做对比
        1:当前用户是否禁用
        2:当前用户是否修改了密码

### Token往redis追加与清空的规则

    1:用户Login成功后，会新产生一个Token，并添加到redis
    2:用户拿着老的Token，来请求新Token时，需要更新redis中的token
    修改用户表要更新Token的情况：
    1:删除用户，要清空reidis，这种情况很少。
    2:系统主动清空redis
    3:用户修改密码，可以清空这个用户下的redis Token(也可以不清空)
    

### redis存储技巧
    
    得到一个token: get wukong+security+{userid}+{sign} 
    刷新一个token: put wukong+security+{userid}+{sign} , newvalue
    清除某用户下的所有token: del wukong+security+{userid}*
    
    
