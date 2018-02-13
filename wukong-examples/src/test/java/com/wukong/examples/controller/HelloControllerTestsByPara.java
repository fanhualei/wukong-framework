package com.wukong.examples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
     * @return
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

    /** @TODO
     * 传递一个对象数组的测试
     */

    /** @TODO
     * 做一个excel的测试
     */

}
