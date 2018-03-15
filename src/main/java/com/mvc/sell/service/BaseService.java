package com.mvc.sell.service;

import com.mvc.sell.rpc.RpcAccountService;
import com.mvc.sell.rpc.RpcOrderService;
import com.mvc.sell.rpc.RpcProjectService;
import com.mvc.sell.rpc.RpcTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * BaseService
 *
 * @author qiyichen
 * @create 2018/3/14 16:55
 */
@Service
public class BaseService {

    @Autowired
    MailService mailService;
    @Autowired
    RpcAccountService rpcAccountService;
    @Autowired
    RpcOrderService rpcOrderService;
    @Autowired
    RpcProjectService rpcProjectService;
    @Autowired
    RpcTransactionService rpcTransactionService;
    @Autowired
    RedisTemplate redisTemplate;
}
