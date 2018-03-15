package com.mvc.sell.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * RpcTransactionService
 *
 * @author qiyichen
 * @create 2018/3/15 11:54
 */
@FeignClient("token-sell-console")
public interface RpcTransactionService {

}
