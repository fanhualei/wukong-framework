## 防止重复提交(需要实现)

`prtoken` : 是prevent repeat tonken简称

<br>

>目录

* [在程序中使用](#在程序中使用)
    * [通过注解标记要拦截函数](#通过注解标记要拦截函数)
    * [提交前获得prtoken](#提交前获得prtoken)
    
* [实现说明](#实现说明)
    
<br>
    
### 在程序中使用  

#### 通过注解标记要拦截函数

> 添加@PreventRepeat()，会检查request中是否含有prtoken,并与redis中的进行校验。

```java
@RequestMapping("/info")
@PreventRepeat()
public Map<String, String> getInfo(@RequestParam String name) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("name", name);
    return map;
}

```

#### 提交前获得prtoken

> 客户端在提交请求前，需要获得一个prtoken，并放入到request

```java
String url="/author/jwt/getPrtoken";
ResponseEntity<Map> entity = this.restTemplate.getForEntity(url, Map.class);
Map<String, String> returnMap=entity.getBody();
String token=returnMap.get("prtoken");

```

<br>

### 实现说明 

>在redis中定义wukong+security+{userid}+{create}+{prtoken}的key,用来存储prtoken

* 1:客户端在提交前，请求"/author/jwt/getPrtoken"
* 2:服务器会随机生成一个数，放入到redis中
* 3:客户端在提交的request包含prtoken
* 4:服务器判断request中的prtoken与redis中的是否相同
    * 4.1:如果相同，就从redis中删除这个值，并继续请求
    * 4.2:如果不同，就给客户端返回一个错误信息


