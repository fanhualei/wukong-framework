package com.wukong.donghai.controller;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 这个类用来测试single的不同返回类型的情况
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SingleResponseControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestRestTemplate restTemplate;

    String baseUrl="/test/single";

    /**
     * 测试字符串
     */
    @Test
    public void testString() {
        String url=baseUrl+"/string";

        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("Hello World 你好世界");
    }

    @Test
    public void testString1() {
        String url=baseUrl+"/string1";

        ResponseEntity<Map> entity
                = this.restTemplate.getForEntity(url,Map.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Map<String,String> map = entity.getBody();
        String value=map.get("result");
        assertThat(value).isEqualTo("Hello World 你好世界");
    }

    /**
     * 测试数值类型 "/num","/long","/bigdecimal"
     */
    @DataProvider
    public Iterator<Object[]> testUrlDataProvider() {
        String[] items= new String[]{"/num","/long","/bigdecimal"};
        List<Object[]> names = new ArrayList<Object[]>();
        for(String u:items) {
            //做一个形式转换
            names.add(new Object[]{u});
        }
        return names.iterator();
    }


    @Test(dataProvider = "testUrlDataProvider")
    public void testNumber(String testUrl) {
        String url=baseUrl+testUrl;

        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("666");
    }

    @Test(dataProvider = "testUrlDataProvider")
    public void testNumber1(String testUrl) {
        String url=baseUrl+testUrl+"1";

        ResponseEntity<Map> entity = this.restTemplate.getForEntity(url, Map.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Map<String,Object> map=entity.getBody();

        Object object=map.get("result");
        System.out.printf(object.toString());
        String ren =object.toString();
        assertThat(ren).isEqualTo("666");
    }

    /**
     * 测试日期
     */
    @Test
    public void testDate() {
        String url=baseUrl+"/date";

        ResponseEntity<Date> entity = this.restTemplate.getForEntity(url, Date.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Calendar cal= Calendar.getInstance();
        cal.set(2018,6,1,23,15,16);
        Date date= cal.getTime();

        Date renDate = entity.getBody();

        String dateStr=  DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss");
        String renDateStr=  DateFormatUtils.format(renDate,"yyyy-MM-dd HH:mm:ss");
        assertThat(dateStr).isEqualTo(renDateStr);
        System.out.println(renDateStr);
    }

    /**
     * 放入Map的日期类型，都被转换成Long
     */
    @Test
    public void testDate1() throws Exception{
        String url=baseUrl+"/date1";

        ResponseEntity<Map> entity
                = this.restTemplate.getForEntity(url,Map.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println(entity.getBody());


        Map<String,String> map=entity.getBody();


        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date renDate=sdf.parse(map.get("result"));

        Calendar cal= Calendar.getInstance();
        cal.set(2018,6,1,23,15,16);
        Date date= cal.getTime();

        String dateStr=  DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss");
        String renDateStr=  DateFormatUtils.format(renDate,"yyyy-MM-dd HH:mm:ss");
        assertThat(dateStr).isEqualTo(renDateStr);
        System.out.println(renDateStr);
    }

    /**
     * 测试异常
     */
    @Test
    public void testE() {
        String url=baseUrl+"/e";
        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isNotEqualTo(HttpStatus.OK);
        System.out.println(entity.getBody());
    }

    /**
     * 测试Map
     */
    @Test
    public void testMap() {
        String url=baseUrl+"/map";
        ResponseEntity<Map> entity = this.restTemplate.getForEntity(url, Map.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Map<String,String> map =entity.getBody();
        assertThat(map.get("name")).isEqualTo("小明");
    }

    /**
     * 这种对象，不能添加注解
     */
    @Test
    public void testMap1() {
        String url=baseUrl+"/map1";
        ResponseEntity<Map> entity = this.restTemplate.getForEntity(url, Map.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Map<String,String> map =entity.getBody();
        assertThat(map.get("name")).isEqualTo("小明");
    }


}
