package com.mvc.sell.controller;

import com.mvc.sell.common.exception.CheckeException;
import com.mvc.sell.service.AccountService;
import com.mvc.sell.service.OrderService;
import com.mvc.sell.service.ProjectService;
import com.mvc.sell.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

/**
 * BaseController
 *
 * @author qiyichen
 * @create 2018/3/10 15:59
 */
@Controller
public class BaseController {

    @Autowired
    ProjectService projectService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    OrderService orderService;

    @Autowired
    AccountService accountService;
    @Autowired
    RedisTemplate redisTemplate;

    void check(String user, String type, String valiCode) throws IllegalAccessException {
        String code = (String) redisTemplate.opsForValue().get(type + "Check" + user);
        if (null == valiCode || !valiCode.equalsIgnoreCase(code)) {
            throw new IllegalAccessException("验证码错误！");
        } else {
            redisTemplate.delete(type + "Check" + user);
        }
    }
}
