package com.mvc.sell.service;

import com.mvc.common.context.BaseContextHandler;
import com.mvc.common.msg.Result;
import com.mvc.sell.pojo.dto.OrderDTO;
import com.mvc.sell.util.MapUtils;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * orderService
 *
 * @author qiyichen
 * @create 2018/3/14 18:56
 */
@Service
public class OrderService extends BaseService{

    public Result list(OrderDTO orderDTO) {
        BigInteger userId = (BigInteger) BaseContextHandler.get("userId");
        orderDTO.setUserId(userId);
        return rpcOrderService.list(MapUtils.java2Map(orderDTO));
    }
}
