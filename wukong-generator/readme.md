
参考的例子


Mybatis Generator最完整配置详解
http://blog.csdn.net/testcs_dn/article/details/77881776

官方文档
http://www.mybatis.org/generator


代码说明：

GeneratorDAO  是读取xml文件来生成mybatis相关类的。(不推荐使用)

GeneratorDaoWithJava 不读取xml文件，有以下功能：

    1、可以读取数据库中所有的表，自动生成
    2、可以设置前缀wk_，例如wk_order 生成 order类
    3、设置ignores，可以忽略那些表不生成相关的对象
    
    todo 
    1、开发plugin，来实现扩展的功能。
    2、通过插件 自动生成Service类。
    3、用springboot 自动配置，来简化代码