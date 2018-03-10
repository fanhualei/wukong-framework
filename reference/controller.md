# 如何撰写API接口  

###### 通过三行代码,就可以写一个用http可以访问的接口了 [例子代码](../wukong-examples/src/main/java/com/wukong/examples/controller/HelloController.java)

 
> 目录


* [实际开发](#实际开发)
    * [默认规范](#默认规范)
    * [返回格式要统一](#返回格式要统一)
    * [配置访问权限](#配置访问权限)
    * [撰写swagger注释](#撰写swagger注释)

    
* 接口测试
    * [PostMan](postman.md)
    * [swagger2](swagger2.md)
    * [TestNg](testng.md)   

* [基础概念](#基础概念)
    * [返回一个字符串](#返回一个字符串)
    * [返回一个map对象](#返回一个map对象)
    * [返回一个list包含map对象](#返回一个list包含map对象)
    * [返回一个list包含city对象](#返回一个list包含city对象)
    * [request参数是city对象](#request参数是city对象)
    * [上传一个或多个文件](#上传一个或多个文件)
    * [下载一个文件](#下载一个文件)
    
   
    
<br>    


## 实际开发


### 默认规范

* 除了安全模块,所以接口都以 /api开头, 例如user下 /api/user/getUser;/api/user/delUser


<br>

### 返回格式要统一

> 用code msg data形式返回

    {"code":0,"msg":"success","data":{"id":1,"name":"city1","code":"001"}}


详情见:[添加注解就可以返回统一的格式](result.md)


<br>

### 配置访问权限

> @PreAuthorize , info可以被拥有admin或user角色的用户访问

```java
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')  ")
@RequestMapping("/info")
public Map<String, String> getInfo(@RequestParam String name) {
    //代码略
}
```

<br>

### 撰写swagger注释

`不建议使用,因为这些内容可以在代码外写`

> @ApiOperation  @ApiImplicitParam 分别是swagger的注解


```java
@ApiOperation(value="得到名称", notes="")
@ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String")
@RequestMapping("/info")
public Map<String, String> getInfo(@RequestParam String name) {
    //代码略
}
```


<br>


    
## 基础概念

`注释:本部分仅仅说明了功能,实际项目开发中,要注意规范` <br>

`步骤:`
* 新添加一个java文件
* 给class添加注解: @RestController

### 返回一个字符串

* 输入 http://localhost:8080/hello 
* 返回 Hello World

```java
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }
```
<br>

### 返回一个map对象

* 输入 http://localhost:8080/info?name=abc
* 返回 {"name":"abc"}

```java
    @RequestMapping("/info")
    public Map<String, String> getInfo(@RequestParam String name) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        return map;
    }
```
<br>


### 返回一个list包含map对象

* 输入 http://localhost:8080/json
* 返回 [{"name":"Shanhy-1"},{"name":"Shanhy-2"},{"name":"Shanhy-3"},{"name":"Shanhy-4"},{"name":"Shanhy-5"}]

```java
@RequestMapping("/json")
public List<Map<String, String>> getList() {
    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    Map<String, String> map;
    for (int i = 1; i <= 5; i++) {
        map = new HashMap<String, String>();
        map.put("name", "Shanhy-" + i);
        list.add(map);
    }
    return list;
}
```

### 返回一个list包含city对象

* 输入 https://localhost:8443/getCityList
* 返回 [{"id":1,"name":"city1","code":"001" },{"id":2,"name":"city2","code":"002" },{"id":3,"name":"city3","code":"003" }]

```java
@RequestMapping("/getCityList")
public List<City> getCityList() {
    City city1=new City(1,"city1","001");
    City city2=new City(2,"city2","002");
    City city3=new City(3,"city3","003");
    List<City> cityList=Arrays.asList(city1,city2,city3);
    return cityList;
}
```

### request参数是city对象

* 输入 需要在postman中 post 到https://localhost:8443/addCity
* 返回 {"id":1,"name":"city1","code":"001ok" }

```java
@RequestMapping("/addCity")
public City addCity(@RequestBody City city){
    city.setCode(city.getCode()+"ok");
    return city;
}
``` 

### 上传一个或多个文件

> 详细见代码的例子

```java
//上传一个文件
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public String upload(@RequestParam("file") MultipartFile file) {
    if (!file.isEmpty()) {
        try {
            // 这里只是简单例子，文件直接输出到项目路径下。
            // 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
            // 还有关于文件格式限制、文件大小限制，详见：中配置。
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File(uploadPath+file.getOriginalFilename())));
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        }
        return "上传成功";
    } else {
        return "上传失败，因为文件是空的.";
    }
}
```
<br>

```java
//上传多个文件
@RequestMapping(value = "/upload/batch", method = RequestMethod.POST)
public @ResponseBody String batchUpload(HttpServletRequest request) {
    List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
    MultipartFile file = null;
    BufferedOutputStream stream = null;
    for (int i = 0; i < files.size(); ++i) {
        file = files.get(i);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                stream = new BufferedOutputStream(new FileOutputStream(new File(uploadPath+file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                stream = null;
                return "You failed to upload " + i + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + i + " because the file was empty.";
        }
    }
    return "upload successful";
}

```
<br>

### 下载一个文件

> 可以用字节流的形式,将文件输出到客户端,例如向客户端输出一段mp3

```java
@RequestMapping("/download")
public String downLoad(HttpServletResponse response)throws Exception{
    //代码略
}
```

<br>


