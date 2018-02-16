# wukong-framework
一个基于spring boot的框架


02-12 将原先代码都删除了，实现了下面的功能

      1、多pom
      2、https
      3、testNG测试
      4、rest接口
     

02-13 做了http rest的代码 

      1、添加了文件上传 下载 
      2、post json代码  
      3、http2https重新定向  
      4、testng参数测试
      5、@todo testng 传递对象测试 excel表测试 随机数测试

02-14 集成了mybatis，今天最大的收获是，别把没看懂的代码提交到工程中，会影响其他程序的。

      1、集成了mybatis,按照官网的，越简单越好
      2、testNG中进行了测试，分别测试了Dao与Service，今后只用测试Service
      3、引入了这个aspectj包太老了，看看有没有其他更好的
      ~~4、@todo mybatis多数据源与动态切换，官方应该有特别简单例子，这样符合springboot的特点
      ，别上网看那些代码，写的太复杂  ~~    

02-15 做了主从数据库，实现了多年的心愿。 
      
      1、实现了 1个master库， n个slave库。1个master库， 3个slave库，出现select时，从slave库上轮训选择要查询的库
      2、集成了阿里的druid数据源，spring 推荐的是hikariCP ，速度快稳定。 druid太复杂，但是容易监控sql语句
      3、输入https://localhost:8443/druid/， 能看到druid的监控页面
      4、解决了springBoot跨包的问题
        @SpringBootApplication(scanBasePackages={"com.wukong.core","com.wukong.examples"})
      
      todo 
      ~~1、@todo 撰写一个标签  可以制定从主数据库上读取数据~~
      2、@todo 今后可以动态的配置slave数量
      3、@todo 撰写unitTest保证这部分数据没有问题。
      4、@todo 拦截器可以配置到配置文件中
      ~~5、@todo 将代码移到core目录中~~
      6、@todo 升级druid到1.8.1版本，看看好用不用。 估计要同时升级到springboot2.0
      ~~7、@todo 使用spring的aop，而不是老的apache的aop~~
      ~~8、@todo service方法中只要是:insert update delete add  remove 就表示非查询语句~~
      
      
      到底拦截器做在DAO(sql)上好 还是在service上好
      service的好处
      1、数据源这部分代码简单。
      2、今后可以重用Dao的这部分代码？ 但是想想，这条不成立。
      坏处：
      1、如果有人从controler这部分调用dao，就拦截不住。
      
      
02-16 做了拦截器，可以拦截service上指定的数据库

      1、 @DatasourceAnno(name=master) 或 @DatasourceAnno(name=slave) 后不再根据函数名来判断获取的数据库
      2、maven 引用了 spring-boot-starter-aop ，就不用引用aspectjweaver 
      3、wukong-generator 用来生成相关的代码
      
      todo
      1、代码生成器
      2、安全框架
      3、主键不会返回
      4、生成class类的时候，要把前缀给去掉
      
      