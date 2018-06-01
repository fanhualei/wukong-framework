# Java与Mysql数据对应关系

> 目录

* [Java数据类型和MySql数据类型对应表](https://www.cnblogs.com/jerrylz/p/5814460.html)

* [常见问题](#常见问题)
    * 





场景 |  mysql  | java   | 备注 |
--------- | --------| --------| --------|
主键  | BIGINT(20) unsigned |  long |  权限管理模块，可以使用int类型 |
性别  |  |  |   使用整形 |
是否 |  |    |  不建议使用boolean|
人150 岁之内 | unsigned tinyint |    | 无符号值：0 到 255 |
龟 数百岁    | unsigned smallint |    |  无符号值：0 到 65535|
恐龙化石数千万年 | unsigned int |    | 无符号值：0 到约 42.9 亿 |
太阳 约 50 亿年 | unsigned bigint |    |  无符号值：0 到约 10 的 19 次方|
是否 |  |    |  |
小数类型 | decimal  |    | 禁止使用 float 和 double,如果存储的数据范围超过 decimal 的范围，建议将数据拆成整数和小数分开存储 |








````xml
<javaTypeResolver type="com.generator.MyJavaTypeResolver">  
    <property name="forceBigDecimals" value="false" />                                            <!-- 类型解析器 -->  
</javaTypeResolver>  



<!-- java类型处理器
    用于处理DB中的类型到Java中的类型，默认使用JavaTypeResolverDefaultImpl；
    注意一点，默认会先尝试使用Integer，Long，Short等来对应DECIMAL和 NUMERIC数据类型；
-->

<javaTypeResolvertype="org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl">
    <!--
        true：使用BigDecimal对应DECIMAL和 NUMERIC数据类型
        false：默认,
           scale>0;length>18：使用BigDecimal;
           scale=0;length[10,18]：使用Long；
           scale=0;length[5,9]：使用Integer；
           scale=0;length<5：使用Short；
     -->
    <propertyname="forceBigDecimals" value="false"/>
</javaTypeResolver>


````