package com.wukong.core.exceptions;


import com.wukong.core.enums.ResultCode;
import com.wukong.core.util.StringUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @desc 业务异常类
 *
 * @author zhumaer
 * @since 9/18/2017 3:00 PM
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 194906846739586856L;

    protected String code;

    protected String message;

    protected ResultCode resultCode;

    protected Object data;

//    public BusinessException() {
//
//    }
//
//    public BusinessException(String message) {
//        this();
//        this.message = message;
//    }
//
//    public BusinessException(String format, Object... objects) {
//        this();
//        this.message = StringUtil.formatIfArgs(format, "{}", objects);
//    }
//
//    public BusinessException(ResultCode resultCode, Object data) {
//        this(resultCode);
//        this.data = data;
//    }


    public BusinessException(ResultCode resultCode) {
        this.resultCode = resultCode;
        this.code = resultCode.code().toString();
        this.message = resultCode.message();
    }

}