
package com.wukong.examples.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;


import com.wukong.examples.entity.City;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestRestTemplate restTemplate;

//    @BeforeMethod
//    public void getToken(){
//        String url="/author/jwt/login?username=admin&password=admin";
//        ResponseEntity<Map> entity = this.restTemplate.getForEntity(url, Map.class);
//        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Map<String, String> returnMap=entity.getBody();
//        String token=returnMap.get("token");
//        BasicJwtInterceptor basicJwtInterceptor= new BasicJwtInterceptor(token);
//        restTemplate.getRestTemplate().getInterceptors().add(basicJwtInterceptor);
//    }


    @Test
    public void testHello() {
        String url="/hello";


        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("\"Hello World\"");
    }





    @Test
    public void testInfoByGet() {
        String url="/hello/info?name=张三";
        Map<String, String> map = this.restTemplate.getForObject(url,Map.class);
        String value=map.get("name");
        assertThat(value).isEqualTo("张三");
    }


    @Test
    public void testJson() {
        String url="/hello/json";
        List<Map<String, String>> list = this.restTemplate.getForObject(url,List.class);

        for(int i=0;i<list.size();i++){
            Map<String, String> map =list.get(i);
            String value=map.get("name");
            assertThat(value).isEqualTo("Shanhy-"+(i+1));
        }
    }


    /**
     * post a para
     */
    @Test
    public void testInfoByPost() {
        String url="/hello/info";

        MultiValueMap<String, String> paraMap= new LinkedMultiValueMap<String, String>();
        paraMap.add("name", "张三");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request
                = new HttpEntity<MultiValueMap<String, String>>(paraMap, headers);

        ResponseEntity<Map> entity = this.restTemplate.postForEntity(url, request,Map.class);

        Map<String, String> returnMap=entity.getBody();
        String value=returnMap.get("name");
        assertThat(value).isEqualTo("张三");
    }


    @Test
    public void testLogo() {
        String url="/hello/logo";
        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("\"show logo\"");
    }

    @Test
    public void testGetCityList() {
        String url="/hello/getCityList";

        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        String jsonArrayStr=entity.getBody();
        List<City> cityList = JSON.parseObject(jsonArrayStr, new TypeReference<List<City>>() {});
        int i=1;
        for(City city:cityList){
            assertThat(city.getId()).isEqualTo(i);
            i++;
        }


    }

    /**
     * 重点  test post json and return json
     * 1 add header
     * 2 set city object to formEntify
     * 3 post and get city object
     *
     */
    @Test
    public void testAddCity(){
        String url="/hello/addCity";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        City postCity=new City(1,"city1","001");
        HttpEntity<City> formEntity = new HttpEntity<City>(postCity, headers);


        ResponseEntity<City> entity = this.restTemplate.postForEntity(url, formEntity,City.class);

        City returnCity=entity.getBody();
        assertThat(returnCity.getCode()).isEqualTo(postCity.getCode()+"ok");
    }


    //@Test
    //@TODO 上传中文文件名有乱码
    public  void testUploadBatch(){
        String url="/hello/upload/batch";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data; charset=UTF-8");
        headers.setContentType(type);

        MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();

        URL path=HelloControllerTests.class.getResource("/");

        FileSystemResource fileSystemResource1 = new FileSystemResource(path.getPath()+"upload1.txt");
        FileSystemResource fileSystemResource2 = new FileSystemResource(path.getPath()+"上传文件2.txt");
        form.add("testPara","paraValue");
        form.add("file", fileSystemResource1);
        form.add("file", fileSystemResource2);

        ResponseEntity<String> entity = this.restTemplate.postForEntity(url, form,String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("upload successful");
    }

    //@Test
    public void testDownLoad() throws Exception {
        String url = "/hello/download";
        HttpHeaders headers = new HttpHeaders();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            List list = new ArrayList();
            list.add(MediaType.valueOf("application/octet-stream"));
            headers.setAccept(list);

            ResponseEntity<byte[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<byte[]>(headers),
                    byte[].class);

            byte[] result = response.getBody();

            inputStream = new ByteArrayInputStream(result);

            URL path = HelloControllerTests.class.getResource("/");
            File file = new File(path.getPath() + "downloadfile.png");
            if (!file.exists()) {
                file.createNewFile();
            }

            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }


        }
    }




}
