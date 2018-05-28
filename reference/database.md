# 数据库操作技巧

>目录


* [基本操作](#基本操作)
    * [代码自动生成](#代码自动生成)
    * [生成代码说明](#生成代码说明)
    * [多表关联查询](#多表关联查询)
    * [分页插件](#分页插件)


* [其他](#其他)
    * [多数据库源配置](#多数据库源配置)
    * [Mybatis缓存的应用](#mybatis缓存的应用)
    * [druid使用技巧](#druid使用技巧)




## 基本操作



### 代码自动生成

> wukong_generator 工程中用来生成代码



>> 修改generatorConfig.xom

* password需要修改
* <property name="effects" value="wk_user"/> 中输入要生成的table名称


```xml
<jdbcConnection driverClass="com.mysql.jdbc.Driver"
                connectionURL="jdbc:mysql://127.0.0.1:3306/wukong_write?useSSL=false"
                userId="root"
                password="rootmysql">
</jdbcConnection>

<javaClientGenerator type="ANNOTATEDMAPPER" targetPackage="com.wukong.security.dao"  targetProject="log">
    <property name="enableSubPackages" value="true" />
    <property name="prefix" value="wk_"/>
    <!--effects 计划生成的Table，如果为空，就生成全部的-->
    <property name="effects" value="wk_user"/>
    <property name="ignores" value="groups,wk_order"/>
    <property name="targetPackage" value="com.wukong.security.service"/>
    <property name="targetProject" value="log"/>
    <property name="runWukongRule" value="true"/>
</javaClientGenerator>

```


>> 执行WukongGenerator 类，代码会生成到log目录下

<br>

> 参考网址

* [github官网](https://github.com/mybatis/generator)
* [mybatis generator官方参考文档](http://www.mybatis.org/generator/)



### 生成代码说明

#### 目录结构

目录文件 | 说明 |
--------- | --------|
dao.UserMapper.java  | dao主函数，里面包含添加删除修改等基本行 |
dao.UserSqlProvider.java    | 以前在xml中的sql，现在都在这个类中了 |
model.User.java    | user对象 |
model.UserExample.java    | 为了检索而生成的对象|


#### dao类函数说明


函数名 | 说明 | 备注 |
--------- | --------|  --------|
long countByExample(UserExample example) | 通过条件，得到记录数| |
int deleteByExample(UserExample example)    | 通过条件，删除 | 0=没有删除任何记录 |
int deleteByPrimaryKey(Integer userId) | 通过主键删除一条记录 | 0=没有删除任何记录 |
int insert(User record) | 添加记录，如果属性为空，那么也会更新数据库字段 | 0表示没有更新;自增加主键在record里面  |
int insertSelective(User record) | 添加记录，如果属性为空，不更新字段 | 0表示没有更新;自增加主键在record里面  |
List<User> selectByExample(UserExample example) | 根据条件检索 |  |
User selectByPrimaryKey(Integer userId) | 根据主键检索|  |
int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example) | 如果属性为空，那么也会更新数据库字段 |  |
int updateByExample(@Param("record") User record, @Param("example") UserExample example)| 更新记录，如果属性为空，不更新字段|  |
int updateByPrimaryKeySelective(User record) | 根据主键更新，不往数据库中更新null属性|  |
int updateByPrimaryKey(User record)| 根据主键更新，属性=null，会将数据库对应主键更新成null |  |



#### Example类的使用


##### 参考网址

* [官方：Example Class Usage Notes](http://www.mybatis.org/generator/generatedobjects/exampleClassUsage.html)
* [官方:Extending the Example Classes](http://www.mybatis.org/generator/generatedobjects/extendingExampleClass.html)


##### 简单查询

```java
TestTableExample example = new TestTableExample();
example.createCriteria().andField1EqualTo(5);

List<TestTable>  testTables =testTableMapper.selectByExample(example);

```

##### 多条件

```java
TestTableExample example = new TestTableExample();

  example.or()
    .andField1EqualTo(5)
    .andField2IsNull();

  example.or()
    .andField3NotEqualTo(9)
    .andField4IsNotNull();

  List<Integer> field5Values = new ArrayList<Integer>();
  field5Values.add(8);
  field5Values.add(11);
  field5Values.add(14);
  field5Values.add(22);

  example.or()
    .andField5In(field5Values);

  example.or()
    .andField6Between(3, 7);


```

> 等价与

```sql
where (field1 = 5 and field2 is null)
     or (field3 <> 9 and field4 is not null)
     or (field5 in (8, 11, 14, 22))
     or (field6 between 3 and 7)
```


##### 去重

> 使用example中的setDistinct(true)



##### like 不区分大小写

```java
ExtendedExample example = new ExtendedExample();
ExtendedCriteria criteria = (ExtendedCriteria) example.createCriteria();
criteria.andFirstNameLikeInsensitive("fred%");
List results = selectByExample(example);
```

##### in 检索


> 例如

    FIRST_NAME IN (?, ?, ?) 或
    LAST_NAME NOT IN (?, ?, ?, ?)

> 代码这么写

    addCriterion(String anyString, List listOfObjects, String propertyName)
    
    
##### between 检索    

> 例如

    FIRST_NAME BETWEEN ? AND ?
    LAST_NAME NOT BETWEEN ? AND ?
    
> 代码这么写

    addCriterion(String anyString, Object object1, Object object2, String propertyName)
        
