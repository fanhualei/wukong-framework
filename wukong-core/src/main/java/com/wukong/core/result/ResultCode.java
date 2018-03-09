package com.wukong.core.result;

public abstract class ResultCode {
    public static final int SuccessCode = 0;

    //业务系统
    public static final Long DefaultAddressNotDeleteErrorCode = 10001L;//默认地址不能删除
    public static final Long NotFindAddressErrorCode = 10002L;//找不到此收货地址
    public static final Long NotFindUserErrorCode = 10003L;//找不到此用户
    public static final Long NotMatchUserAddressErrorCode = 10004L;//用户与收货地址不匹配



}