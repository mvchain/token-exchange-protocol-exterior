package com.mvc.sell.controller;

import com.github.pagehelper.Page;
import com.mvc.common.msg.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * order controller
 *
 * @author qiyichen
 * @create 2018/3/10 16:12
 */
@Controller
@RequestMapping("order")
public class OrderController extends BaseController{

    /**
     * get all orders by user
     * @param page
     * @param type
     * @return
     */
    @GetMapping
    @NeedLogin
    Result list(@ModelAttribute Page page, @RequestParam  Integer type) {
        return orderService.list(page, type);
    }


}
