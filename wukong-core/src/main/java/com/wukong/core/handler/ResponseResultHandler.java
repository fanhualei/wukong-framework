package com.wukong.core.handler;

import com.wukong.core.result.SingleResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {



    /**
     * 判断支持的类型
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType){
        SingleResponseResult singleResponseResult=
                AnnotationUtils.findAnnotation(returnType.getMethod(),SingleResponseResult.class);
        //注意，这里必须返回true，否则不会执行beforeBodyWrite方法
        return singleResponseResult == null ? false : true ;
    }

    @Override
    @Nullable
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType,
                      Class<? extends HttpMessageConverter<?>> selectedConverterType,
                      ServerHttpRequest request, ServerHttpResponse response){

        SingleResponseResult singleResponseResult=
            AnnotationUtils.findAnnotation(returnType.getMethod(),SingleResponseResult.class);

        //找到自定的注解类
        if(singleResponseResult!=null){
            Map<String,Object> returnMap=new HashMap<String,Object>();
            returnMap.put(singleResponseResult.value(),body);
            return returnMap;
        }
        return body;
    }


}


/**
 * 可以将status设置成200多，来处理
 * response.setStatusCode(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
 *
 *
 */