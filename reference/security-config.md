# security模块使用说明


>目录

* [直接在程序中配置](#直接在程序中配置)
* [通过数据库进行动态配置](#通过数据库进行动态配置)   

<br>


## 直接在程序中配置

### 通过@PreAuthorize配置每个用户的角色.

```java
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')  ")
@RequestMapping("/hello")
public String getInfo(String name) {
    return "hello "+name;
}
```

### 用户的角色在定义在user_role表中

> [数据库表结构](sql/security/schema.sql)


<br>

## 通过数据库进行动态配置

### 在resource表中定义要访问的rul

<br>

### 通过操作role_resource,动态配置权限


resource
