## redis的使用


<br>

> 关键点

    1: redis使用规范
    2: 在代码中使用redis
    3：通过注解使用redis
    4:redis安装
    
    
    
> redis使用规范

>> 非DB系统缓存规范 

    key = wukong:模块名称:缓存名字:主键
        例如tonken缓存的命名 key = wukong:security:token:{userid}
        
>> DB层查询缓存规范

    1:保存对象
        key= 表名:主键值  value=对象
        例如：user:1=userObject     
    2:保存表字段的明细
        key=表名:主键字段名:主键数:字段名  value=字段值
        例如：user:userid:1:username="amdin"
             user:userid:1:email="fanhl@189.cn"
        检索：
             keys user:userid:1* 可以查出有几个属性
             
                  
>> 在代码中使用redis

<br>



>> 通过注解使用redis



>> 附录：redis安装

    安装
        sudo apt-get install redis-server
    检查状态
        sudo /etc/init.d/redis-server status
    登录redis即获得帮助
        redis-cli
        help    
    基本使用命令
        查看所有的key列表  keys *
        增加一条记录key1  set key1 "hello"
        得到数据         get key1
        增加一条数字记录  set key2 1
        让数字自增       INCR key2
        删除一个        del key1   
        
[更多使用说明](https://www.cnblogs.com/zongfa/p/7808807.html  "打开网页")        