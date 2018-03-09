package com.wukong.examples.controller;


import com.wukong.core.result.APIResult;
import com.wukong.core.result.PageResult;
import com.wukong.examples.entity.City;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController {

    @RequestMapping("/success")
    public PageResult<List<City>> findPageResultSuccess(HttpServletRequest req) {
        List<City> cities = new ArrayList<City>();
        for (int i = 1; i <= 5; i++) {
            City city = new City(i,"city"+1,"00"+i);
            cities.add(city);
        }
        PageResult<List<City>> result = PageResult.newSuccessResult(1, 2, cities.size(), cities);
        return result;
    }

    @RequestMapping("/fail")
    public PageResult<List<City>> findPageResultFail(HttpServletRequest req) {
        List<City> cities = new ArrayList<City>();
        PageResult<List<City>> result = PageResult.newFailResult(1, "分页查询失败");
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/success")
    public APIResult<City> findAPIResultSuccess(HttpServletRequest req) {
        City city = new City(1,"city1","001");
        APIResult<City> result = APIResult.newSuccessResult(city);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fail")
    public APIResult<City> findAPIResultFail(HttpServletRequest req) {
        APIResult<City> result = APIResult.newFailResult(1, "没有找到用户");
        return result;
    }


}
