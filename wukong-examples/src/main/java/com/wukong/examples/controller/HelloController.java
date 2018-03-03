package com.wukong.examples.controller;


import com.wukong.examples.entity.City;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


@RestController  //等同于类上加@Controller 和 方法上加@RequestBody
@RequestMapping("/hello")  //映射地址：映射一级路径
public class HelloController  {

    /**
     * 默认映射，可不填二级
     * <p>
     * 地址：http://localhost:8080/hello
     *      https://localhost:8443/hello
     * 显示：Hello Spring-Boot
     */
    @ApiOperation(value="欢迎", notes="")
    @RequestMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')  ")
    public String hello() {
        return "Hello World";
    }

    /**
     * 带参数
     * Json显示-返回Map格式
     * <p>
     * 地址：http://localhost:8080/hello/info?name=abc
     * 显示：{"name":"张三"}
     */
    @ApiOperation(value="得到名称", notes="")
    @ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String")
    @RequestMapping("/info")
    public Map<String, String> getInfo(@RequestParam String name) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        return map;
    }

    /**
     * Json显示-返回List格式
     * <p>
     * 地址：http://localhost:8080/hello/json
     * 显示：[{"name":"Shanhy-1"},{"name":"Shanhy-2"},{"name":"Shanhy-3"},{"name":"Shanhy-4"},{"name":"Shanhy-5"}]
     */
    @ApiOperation(value="得到列表", notes="" )
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


    /**
     * web日志实例
     * <p>
     * 地址：http://localhost:8080/hello/logo
     */
    @RequestMapping("/logo")
    public String logo() {
        return "show logo";
    }


    /**
     * 得到用户的列表
     * 地址：https://localhost:8443/hello/getCityList
     */
    @RequestMapping("/getCityList")
    public List<City> getCityList() {

        City city1=new City(1,"city1","001");
        City city2=new City(2,"city2","002");
        City city3=new City(3,"city3","003");

        List<City> cityList=Arrays.asList(city1,city2,city3);

        return cityList;
    }

    /**
     * post 一个city信息，并返回一个数据
     * 地址：https://localhost:8443/hello/addCity
     * 利用postman进行测试
     */
    @ApiOperation(value="添加city", notes="" )
    @RequestMapping("/addCity")
    public City addCity(@RequestBody City city){
        city.setCode(city.getCode()+"ok");
        return city;
    }

    @Value("${wukong.web.upload.path:/home/fan/IdeaProjects/wukong-framework/log/upload/}")
    private String uploadPath;

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


    @RequestMapping("/download")
    public String downLoad(HttpServletResponse response)throws Exception{

        String renStr="download ok";

        String filename="倪妮.jpg";
        String filePath = uploadPath ;
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在

            response.setContentType("application/force-download;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(filename, "UTF-8"));

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {

                e.printStackTrace();
                renStr=e.toString();
            }

            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return renStr;
    }






}