package com.mvc.sell.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
}
