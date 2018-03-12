package com.mvc.sell.controller;

import com.mvc.common.msg.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * transaction controller
 *
 * @author qiyichen
 * @create 2018/3/10 16:05
 */
@Controller
@RequestMapping("transaction")
public class TransactionController extends BaseController {

    /**
     * get project info by id, user must be login
     * @param id
     * @return
     */
    @GetMapping("project/{id}")
    @NeedLogin
    Result projectInfo(@PathVariable Integer id) {
        return transactionService.projectInfo(id);
    }

    /**
     * buy token, user must be login
     * @param buyDTO
     * @return
     */
    @PostMapping
    @NeedLogin
    Result project (@RequestBody @Valid BuyDTO buyDTO){
        return transactionService.buy(buyDTO);
    }

    @PostMapping("withdraw")
    @NeedLogin
    @CheckTransPwd
    Result withdraw(@RequestBody @Valid WithdrawDTO withdrawDTO) {
        return transactionService.withdraw(withdrawDTO);
    }

    @GetMapping
    @NeedLogin
    Result getWithdraw(@RequestParam String coinName) {
        return transactionService.getWithdrawConfig(coinName);
    }


}
