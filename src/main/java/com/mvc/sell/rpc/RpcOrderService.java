package com.mvc.sell.rpc;

import com.github.pagehelper.PageInfo;
import com.mvc.common.msg.Result;
import com.mvc.sell.pojo.dto.OrderDTO;
import com.mvc.sell.pojo.vo.OrderVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * rpc OrderService
 *
 * @author qiyichen
 * @create 2018/3/14 18:56
 */
@FeignClient("token-sell-console")
public interface RpcOrderService {

    @RequestMapping(value="order",method = RequestMethod.GET)
    Result<PageInfo<OrderVO>> list (@RequestParam Map orderDTO);
}
