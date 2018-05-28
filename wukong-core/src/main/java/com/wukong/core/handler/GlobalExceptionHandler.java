package com.wukong.core.handler;

import com.wukong.core.enums.ResultCode;
import com.wukong.core.exceptions.BusinessException;
import com.wukong.core.result.DefaultErrorResult;
import com.wukong.core.result.ParameterInvalidItem;
import com.wukong.core.result.ParameterInvalidItemHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;


@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler  {


    /**
     * 处理400类异常
     * 违反约束异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public DefaultErrorResult handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {


        log.error("handleConstraintViolationException start, uri:{}, caused by: ", request.getRequestURI(), e);
        List<ParameterInvalidItem> parameterInvalidItemList = ParameterInvalidItemHelper.convertCVSetToParameterInvalidItemList(e.getConstraintViolations());
        return DefaultErrorResult.failure(ResultCode.COMM_PARAM_IS_INVALID, e, HttpStatus.BAD_REQUEST, parameterInvalidItemList);

    }


    /**
     * 处理验证参数封装错误时异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public DefaultErrorResult handleConstraintViolationException(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error("handleConstraintViolationException start, uri:{}, caused by: ", request.getRequestURI(), e);
        return DefaultErrorResult.failure(ResultCode.COMM_PARAM_IS_INVALID, e, HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理参数绑定时异常（反400错误码）
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public DefaultErrorResult handleBindException(BindException e, HttpServletRequest request) {
        log.error("handleBindException start, uri:{}, caused by: ", request.getRequestURI(), e);
        List<ParameterInvalidItem> parameterInvalidItemList = ParameterInvalidItemHelper.convertBindingResultToMapParameterInvalidItemList(e.getBindingResult());
        return DefaultErrorResult.failure(ResultCode.COMM_PARAM_IS_INVALID, e, HttpStatus.BAD_REQUEST, parameterInvalidItemList);

    }


    /**
     * 处理使用@Validated注解时，参数验证错误异常（反400错误码）
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DefaultErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("handleMethodArgumentNotValidException start, uri:{}, caused by: ", request.getRequestURI(), e);
        List<ParameterInvalidItem> parameterInvalidItemList = ParameterInvalidItemHelper.convertBindingResultToMapParameterInvalidItemList(e.getBindingResult());
        return DefaultErrorResult.failure(ResultCode.COMM_PARAM_IS_INVALID, e, HttpStatus.BAD_REQUEST, parameterInvalidItemList);

    }

    /* 处理自定义异常 */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<DefaultErrorResult> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.error("handleBusinessException start, uri:{}, exception:{}, caused by: {}", request.getRequestURI(), e.getClass(), e.getMessage());

        DefaultErrorResult defaultErrorResult = DefaultErrorResult.failure(e);


        return ResponseEntity.status(600).body(defaultErrorResult);
        /*
        return ResponseEntity

                .status(HttpStatus.valueOf(defaultErrorResult.getStatus()))
                .body(defaultErrorResult);
                */

    }

    /* 处理运行时异常 （反500错误码）*/
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public DefaultErrorResult handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        //TODO 可通过邮件、微信公众号等方式发送信息至开发人员、记录存档等操作

        log.error("handleRuntimeException start, uri:{}, caused by: ", request.getRequestURI(), e);
        return DefaultErrorResult.failure(ResultCode.COMM_SYSTEM_INNER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR);



    }

    //https://www.cnblogs.com/yangzhilong/p/7904665.html

}