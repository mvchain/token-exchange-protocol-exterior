package com.mvc.sell.controller;

import com.mvc.common.context.BaseContextHandler;
import com.mvc.common.msg.Result;
import com.mvc.common.msg.ResultGenerator;
import com.mvc.sell.common.annotation.NeedLogin;
import com.mvc.sell.pojo.dto.*;
import com.mvc.sell.pojo.vo.CapitalVO;
import com.mvc.sell.util.VerifyUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * account controller
 *
 * @author qiyichen
 * @create 2018/3/10 16:15
 */
@RestController
@RequestMapping("account")
public class AccountController extends BaseController {

    @ApiOperation("用户注册")
    @PostMapping
    Result createUser(@RequestBody @Valid UserDTO userDTO) throws IllegalAccessException {
        check(userDTO.getEmail(), "email", userDTO.getEmailCode());
        return accountService.create(userDTO);
    }

    @ApiOperation("发送验证码邮件")
    @GetMapping("email")
    Result sendEmail(@RequestParam @NotNull(message = "邮箱不能为空") String email) {
        accountService.sendEmail(email);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("获取图片验证码, 注意session, 不同服务session注意分离")
    @GetMapping(value = "/validate/image", produces = "image/png")
    public void codeImage(HttpServletResponse response, HttpSession session) throws Exception {
        Object[] objs = VerifyUtil.createImage();
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
        String key = "imageCheck" + session.getId();
        redisTemplate.opsForValue().set(key, objs[0]);
        redisTemplate.expire(key, 2, TimeUnit.MINUTES);
    }

    @ApiOperation("用户登录")
    @PostMapping("login")
    Result login(@RequestBody @Valid LoginDTO loginDTO, HttpSession session) throws IllegalAccessException {
        check(session.getId(), "image", loginDTO.getImageCode());
        return ResultGenerator.genSuccessResult(accountService.login(loginDTO));
    }

    @PostMapping("token/refresh")
    @ApiOperation("刷新令牌")
    Result refresh() {
        return ResultGenerator.genSuccessResult(accountService.refresh());
    }

    @ApiOperation("忘记密码")
    @PostMapping("forget")
    Result forget(@RequestBody @Valid ForgetDTO forgetDTO) throws IllegalAccessException {
        check(forgetDTO.getEmail(), "email", forgetDTO.getEmailCode());
        return accountService.forget(forgetDTO);
    }

    @ApiOperation("校验邮箱码")
    @PostMapping("email/check")
    @NeedLogin
    Result checkEmail(@RequestBody @Valid EmailDTO emailDTO) throws IllegalAccessException {
        check(emailDTO.getEmail(), "email", emailDTO.getEmailCode());
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("重新绑定邮箱")
    @PutMapping("email")
    @NeedLogin
    Result updateEmail(@RequestBody @Valid EmailDTO emailDTO) throws IllegalAccessException {
        check(emailDTO.getEmail(), "email", emailDTO.getEmailCode());
        return accountService.updateEmail(emailDTO);
    }

    @ApiOperation("更新密码")
    @PutMapping("pwd")
    @NeedLogin
    Result updatePwd(@RequestBody @Valid PwdDTO pwdDTO) throws IllegalAccessException {
        check(BaseContextHandler.get("username").toString(), "email", pwdDTO.getEmailCode());
        return accountService.updatePwd(pwdDTO);
    }

    @ApiOperation("更新交易密码")
    @PutMapping("transaction/pwd")
    @NeedLogin
    Result updateTransPwd(@RequestBody @Valid TransPwdDTO transPwdDTO) throws IllegalAccessException {
        check(BaseContextHandler.get("username").toString(), "email", transPwdDTO.getEmailCode());
        return accountService.updateTransPwd(transPwdDTO);
    }

    @ApiOperation("获取充值地址")
    @GetMapping("address")
    @NeedLogin
    Result<String> address(@RequestParam String coinName) {
        return accountService.address(coinName);
    }

    @ApiOperation("获取余额")
    @GetMapping("balance")
    @NeedLogin
    Result<List<CapitalVO>> balance() {
        return accountService.balance();
    }

    @ApiOperation("获取冲提历史")
    @GetMapping("balance/history")
    @NeedLogin
    Result balanceHistory(@ModelAttribute TransactionDTO transactionDTO) {
        return accountService.balanceHistory(transactionDTO);
    }

}
