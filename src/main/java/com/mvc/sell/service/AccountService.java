package com.mvc.sell.service;

import com.github.pagehelper.PageInfo;
import com.mvc.common.context.BaseContextHandler;
import com.mvc.common.msg.Result;
import com.mvc.common.msg.ResultGenerator;
import com.mvc.sell.constants.MessageConstants;
import com.mvc.sell.pojo.bean.Account;
import com.mvc.sell.pojo.dto.*;
import com.mvc.sell.pojo.vo.AccountVO;
import com.mvc.sell.pojo.vo.CapitalVO;
import com.mvc.sell.pojo.vo.TokenVO;
import com.mvc.sell.pojo.vo.TransactionVO;
import com.mvc.sell.util.BeanUtil;
import com.mvc.sell.util.JwtHelper;
import com.mvc.sell.util.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * AccountService
 *
 * @author qiyichen
 * @create 2018/3/14 16:54
 */
@Service
public class AccountService extends BaseService {

    public Result create(UserDTO userDTO) {
        Result<AccountVO> result = rpcAccountService.getAccount(userDTO.getEmail());
        Assert.isNull(result.getData().getId(), MessageConstants.USER_EXIST);
        Account account = (Account) BeanUtil.copyProperties(userDTO, new Account());
        account.setUsername(userDTO.getEmail());
        return rpcAccountService.create(account);
    }


    public void sendEmail(String email) {
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        mailService.send(email, String.valueOf(code));
        String key = "emailCheck" + email;
        redisTemplate.opsForValue().set(key, String.valueOf(code));
        redisTemplate.expire(key, 5, TimeUnit.MINUTES);
    }

    public TokenVO login(LoginDTO loginDTO) {
        Result<AccountVO> account = rpcAccountService.getAccount(loginDTO.getUsername());
        Assert.notNull(account.getData().getId(), MessageConstants.PWD_ERR);
        Assert.isTrue(account.getData().getPassword().equalsIgnoreCase(loginDTO.getPassword()), MessageConstants.PWD_ERR);
        String token = JwtHelper.createToken(loginDTO.getUsername(), account.getData().getId());
        String refreshToken = JwtHelper.createRefresh(loginDTO.getUsername(), account.getData().getId());
        return new TokenVO(token, refreshToken, account.getData().getId());
    }

    public Result forget(ForgetDTO forgetDTO) {
        Result<AccountVO> account = rpcAccountService.getAccount(forgetDTO.getEmail());
        Assert.notNull(account.getData(), MessageConstants.USER_EMPTY);
        Account accountNew = new Account();
        accountNew.setId(account.getData().getId());
        accountNew.setPassword(forgetDTO.getPassword());
        return rpcAccountService.update(accountNew);
    }

    public Result updateEmail(EmailDTO emailDTO) {
        Account account = new Account();
        BigInteger userId = (BigInteger) BaseContextHandler.get("userId");
        account.setId(userId);
        account.setUsername(emailDTO.getEmail());
        return rpcAccountService.update(account);
    }

    public Result updatePwd(PwdDTO pwdDTO) {
        Account account = new Account();
        BigInteger userId = (BigInteger) BaseContextHandler.get("userId");
        Result<AccountVO> accountResult = rpcAccountService.getAccount(userId);
        Assert.notNull(accountResult.getData().getId(), MessageConstants.USER_EMPTY);
        Assert.isTrue(accountResult.getData().getPassword().equalsIgnoreCase(pwdDTO.getPassword()), MessageConstants.PWD_ERR);
        account.setId(userId);
        account.setPassword(pwdDTO.getNewPassword());
        return rpcAccountService.update(account);
    }

    public Result updateTransPwd(TransPwdDTO transPwdDTO) {
        Account account = new Account();
        BigInteger userId = (BigInteger) BaseContextHandler.get("userId");
        account.setId(userId);
        account.setTransactionPassword(transPwdDTO.getTransactionPassword());
        return rpcAccountService.update(account);
    }

    public Result<String> address(String tokenName) {
        // all token address is same as eth address.
        BigInteger userId = (BigInteger) BaseContextHandler.get("userId");
        Result<AccountVO> accountResult = rpcAccountService.getAccount(userId);
        Assert.notNull(accountResult.getData().getId(), MessageConstants.USER_EMPTY);
        List<CapitalVO> result = balance().getData().stream().filter(obj -> tokenName.equalsIgnoreCase(obj.getTokenName())).collect(Collectors.toList());
        Assert.isTrue(result.size() > 0 && result.get(0).getRechargeStatus() == 1, MessageConstants.ADDRESS_CLOSE);
        return ResultGenerator.genSuccessResult(accountResult.getData().getAddressEth());
    }

    public Result<List<CapitalVO>> balance() {
        BigInteger userId = (BigInteger) BaseContextHandler.get("userId");
        return rpcAccountService.balance(userId);
    }

    public Result<PageInfo<TransactionVO>> balanceHistory(@ModelAttribute TransactionDTO transactionDTO) {
        BigInteger userId = (BigInteger) BaseContextHandler.get("userId");
        transactionDTO.setUserId(userId);
        return rpcAccountService.transactions(MapUtils.java2Map(transactionDTO));
    }

    public String refresh() {
        BigInteger userId = (BigInteger) BaseContextHandler.get("userId");
        String username = (String) BaseContextHandler.get("username");
        return JwtHelper.createToken(username, userId);
    }

}
