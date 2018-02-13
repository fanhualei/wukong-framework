package com.wukong.examples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTestsByPara extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestRestTemplate restTemplate;


    /**
     * 传递一个参数
     */
    @DataProvider
    public Iterator<Object[]> nameDataProvider() {

        String[] items= new String[]{"111","2222","3333"};

        List<Object[]> names = new ArrayList<Object[]>();

        for(String u:items) {
            //做一个形式转换
            names.add(new Object[]{u});
        }
        return names.iterator();
    }


    /**
     * 一个参数的测试
     * @param paraValue
     */
    @Test(dataProvider = "nameDataProvider")
    public void testInfoByGet(String paraValue) {
        String url="/hello/info?name="+paraValue;
        Map<String, String> map = this.restTemplate.getForObject(url,Map.class);
        String value=map.get("name");
        assertThat(value).isEqualTo(paraValue);
    }


    /**
     * 传递多参数测试
     */
    @DataProvider
    public Iterator<Object[]> multParaListDataProvider() {
        List<Object[]> paras = new ArrayList<Object[]>();
        for(int i=1;i<5;i++) {
            //做一个形式转换 今后可以做一个函数，在下面读取一个excel表
            paras.add(new Object[]{i,"name"+i,"code"+i});
        }
        return paras.iterator();
    }


    @Test(dataProvider = "multParaListDataProvider")
    public void testAddCity(int id,String name,String code){
        String url="/hello/addCity";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        City postCity=new City(id,name,code);

        HttpEntity<City> formEntity = new HttpEntity<City>(postCity, headers);

        ResponseEntity<City> entity = this.restTemplate.postForEntity(url, formEntity,City.class);

        City returnCity=entity.getBody();
        assertThat(returnCity.getCode()).isEqualTo(postCity.getCode()+"ok");
    }


    /**
     * 传递一个对象数组的测试
     */
    @DataProvider
    public Iterator<Object[]> objectListDataProvider() {



        List<Object[]> paras = new ArrayList<Object[]>();

        for(int i=1;i<5;i++) {
            //做一个形式转换 今后可以做一个函数，在下面读取一个excel表
            paras.add(new Object[]{new City(i,"name"+i,"code"+i)});
        }
        return paras.iterator();
    }


    @Test(dataProvider = "objectListDataProvider")
    public void testAddCity(City postCity){
        String url="/hello/addCity";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        HttpEntity<City> formEntity = new HttpEntity<City>(postCity, headers);

        ResponseEntity<City> entity = this.restTemplate.postForEntity(url, formEntity,City.class);

        City returnCity=entity.getBody();
        assertThat(returnCity.getCode()).isEqualTo(postCity.getCode()+"ok");
    }



    /** @TODO
     * 做一个excel的测试
     */

}
