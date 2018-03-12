package com.mvc.sell.controller;

import com.github.pagehelper.Page;
import com.mvc.common.msg.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * account controller
 *
 * @author qiyichen
 * @create 2018/3/10 16:15
 */
@Controller
@RequestMapping("account")
public class AccountController extends BaseController{

    /**
     * get all balance
     * @return
     */
    @GetMapping("balance")
    @NeedLogin
    Result balance() {
        return accountService.balance();
    }

    /**
     * get balance history by page
     * @param page
     * @return
     */
    @GetMapping("balance/history")
    @NeedLogin
    Result balanceHistory(@ModelAttribute Page page) {
        return  accountService.balanceHistory(page);
    }

    @GetMapping("address")
    @NeedLogin
    Result address(@RequestParam String coinName){
        return accountService.address(coinName);
    }

    @GetMapping("email")
    Result sendEmail(@RequestParam Integer type) {
        return accountService.sendEmail(type);
    }

    @PostMapping("email")
    @NeedLogin
    @Check(type = {"email"})
    Result updateEmail(@RequestBody @Valid  EmailDTO emailDTO) {
        return accountService.updateEmail(emailDTO);
    }

    @PostMapping("pwd")
    @NeedLogin
    @Check(type = {"password", "image"})
    Result updatePwd(@RequestBody @Valid PwdDTO pwdDTO) {
        return accountService.updatePwd(pwdDTO);
    }

    @PostMapping("transaction/pwd")
    @NeedLogin
    @Check(type = {"transactionPassword", "email"})
    Result updateTransPwd(@RequestBody @Valid TransPwdDTO transPwdDTO) {
        return accountService.updateTransPwd(transPwdDTO);
    }

    @PostMapping
    Result createUser(@RequestBody @Valid UserDTO userDTO) {
        return  accountService.create(userDTO);
    }

    @PostMapping("login")
    @Check(type = {"image"})
    Result login(@RequestBody @Valid LoginDTO loginDTO) {
        return  accountService.login(loginDTO);
    }

    @PostMapping("forget")
    @Check(type = {"email"})
    Result forget( @RequestBody @Valid ForgetDTO forgetDTO) {
        return  accountService.forget(forgetDTO);
    }


}
