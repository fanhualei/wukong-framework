package com.wukong.core.result;

import lombok.Getter;
import lombok.Setter;

public class ResultCode {

    public static  ResultCode SUCCESS=new ResultCode(0,"success");
    public static  ResultCode PARAM_IS_INVALID=new ResultCode(1000,"param is invalid");


    public  ResultCode (int code,String message) {
        this.code=code;
        this.message=message;
    }

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String  message;

}
