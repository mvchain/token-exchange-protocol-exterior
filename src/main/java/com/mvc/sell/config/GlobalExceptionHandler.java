package com.mvc.sell.config;

import com.alibaba.fastjson.JSON;
import com.mvc.common.msg.Result;
import com.mvc.common.msg.ResultGenerator;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Result loginExceptionException(HttpServletResponse response, LoginException ex) {
        response.setStatus(403);
        return ResultGenerator.genFailResult(ex.getMessage());
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
