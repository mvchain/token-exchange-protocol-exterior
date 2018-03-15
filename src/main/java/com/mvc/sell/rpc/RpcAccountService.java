package com.mvc.sell.rpc;

import com.github.pagehelper.PageInfo;
import com.mvc.common.msg.Result;
import com.mvc.sell.pojo.bean.Account;
import com.mvc.sell.pojo.dto.TransactionDTO;
import com.mvc.sell.pojo.vo.AccountVO;
import com.mvc.sell.pojo.vo.CapitalVO;
import com.mvc.sell.pojo.vo.TransactionVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * RpcAccountService
 *
 * @author qiyichen
 * @create 2018/3/14 16:56
 */
@FeignClient("token-sell-console")
public interface RpcAccountService {

    @RequestMapping(value="account",method = RequestMethod.POST)
    Result create(@RequestBody Account info);

    @RequestMapping(value="account",method = RequestMethod.PUT)
    Result update(@RequestBody Account info);

    @RequestMapping(value="account/username",method = RequestMethod.GET)
    Result<AccountVO> getAccount(@RequestParam("username") String username);

    @RequestMapping(value="account/{id}",method = RequestMethod.GET)
    Result<AccountVO> getAccount(@PathVariable("id") BigInteger id);

    @RequestMapping(value="account/address/{tokenName}",method = RequestMethod.GET)
    Result address(@PathVariable("tokenName") String tokenName);

    @RequestMapping(value = "account/{id}/balance", method = RequestMethod.GET)
    Result<List<CapitalVO>>  balance(@PathVariable("id") BigInteger id);

    @RequestMapping(value = "transaction", method = RequestMethod.GET)
    Result<PageInfo<TransactionVO>> transactions(@RequestParam Map transactionDTO);
}
