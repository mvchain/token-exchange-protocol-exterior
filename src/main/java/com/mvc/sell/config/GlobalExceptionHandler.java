package com.mvc.sell.config;

import com.alibaba.fastjson.JSON;
import com.mvc.common.exception.auth.TokenErrorException;
import com.mvc.common.msg.Result;
import com.mvc.common.msg.ResultGenerator;
import com.mvc.sell.constants.MessageConstants;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author qyc
 */
@ControllerAdvice("com.mvc")
@ResponseBody
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(com.mvc.common.handler.GlobalExceptionHandler.class);

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result loginExceptionException() {
        return ResultGenerator.genFailResult(MessageConstants.TOKEN_ERROR_CODE, MessageConstants.getMsg("TOKEN_EXPIRE"));
    }

    @ExceptionHandler(TokenErrorException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result tokenErrorExceptionException() {
        return ResultGenerator.genFailResult(MessageConstants.TOKEN_EXPIRE_CODE, MessageConstants.getMsg("TOKEN_EXPIRE"));
    }

    @ExceptionHandler(FeignException.class)
    public Result feignExceptionException(HttpServletResponse response, FeignException ex) {
        response.setStatus(ex.status());
        String message = ex.getMessage();
        if (ex.getMessage().indexOf("{") > 0) {
            message = (String) JSON.parseObject(ex.getMessage().substring(ex.getMessage().indexOf("{")), HashMap.class).get("message");
        }
        return ResultGenerator.genFailResult(message.trim());
    }


}
