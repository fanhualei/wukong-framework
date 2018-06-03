package com.wukong.core.controller;

import com.wukong.core.util.LocaleMessageSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


@RestController
public class LanguageController {

    @Autowired
    private LocaleMessageSourceUtil messageSourceUtil;

    @RequestMapping("/changeSessionLanauage")
    public String changeSessionLanauage(HttpServletRequest request, HttpServletResponse response,
                                        String lang){
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if("zh".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
        }else if("en".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("en", "US"));
        }
        return "lang is:"+ lang;
    }

    @RequestMapping("/welcome")
    public String welcome(){
        String welcome = messageSourceUtil.getMessage("welcome");
        return welcome;
    }

}