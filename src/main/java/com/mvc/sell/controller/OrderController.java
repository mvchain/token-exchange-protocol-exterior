package com.mvc.sell.controller;

import com.github.pagehelper.Page;
import com.mvc.common.msg.Result;
import com.mvc.sell.common.annotation.NeedLogin;
import com.mvc.sell.pojo.dto.OrderDTO;
import com.mvc.sell.pojo.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * order controller
 *
 * @author qiyichen
 * @create 2018/3/10 16:12
 */
@RestController
@RequestMapping("order")
public class OrderController extends BaseController{

    @ApiOperation("参与的订单")
    @GetMapping
    @NeedLogin
    Result<Page<OrderVO>> list(@ModelAttribute OrderDTO orderDTO) {
        return orderService.list(orderDTO);
    }


}
