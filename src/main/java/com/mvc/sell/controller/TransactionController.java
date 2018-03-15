package com.mvc.sell.controller;

import com.mvc.common.msg.Result;
import com.mvc.sell.common.annotation.NeedLogin;
import com.mvc.sell.pojo.dto.BuyDTO;
import com.mvc.sell.pojo.dto.WithdrawDTO;
import com.mvc.sell.pojo.vo.ProjectInfoVO;
import com.mvc.sell.pojo.vo.WithdrawInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

/**
 * transaction controller
 *
 * @author qiyichen
 * @create 2018/3/10 16:05
 */
@RestController
@RequestMapping("transaction")
public class TransactionController extends BaseController {

    @ApiOperation("查询项目购买信息")
    @GetMapping("project/{id}")
    @NeedLogin
    Result<ProjectInfoVO> projectInfo(@PathVariable BigInteger id) {
        return transactionService.projectInfo(id);
    }


    @PostMapping
    @ApiOperation("购买")
    @NeedLogin
    Result project (@RequestBody @Valid BuyDTO buyDTO){
        return transactionService.buy(buyDTO);
    }
    @ApiOperation("查询可见令牌名称")
    @GetMapping("token")
    @NeedLogin
    Result<List<String>> getTokenNameList() {
        return transactionService.getTokens();
    }
    @ApiOperation("查询提现信息")
    @GetMapping
    @NeedLogin
    Result<WithdrawInfoVO> getWithdrawInfo(@RequestParam String tokenName) {
        return transactionService.getWithdrawConfig(tokenName);
    }
    @ApiOperation("提现")
    @PostMapping("withdraw")
    @NeedLogin
    Result withdraw(@RequestBody @Valid WithdrawDTO withdrawDTO) {
        return transactionService.withdraw(withdrawDTO);
    }



}
