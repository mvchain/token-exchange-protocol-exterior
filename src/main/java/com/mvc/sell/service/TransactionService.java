package com.mvc.sell.service;

import com.mvc.common.msg.Result;
import com.mvc.sell.pojo.dto.BuyDTO;
import com.mvc.sell.pojo.dto.WithdrawDTO;
import com.mvc.sell.pojo.vo.ProjectInfoVO;
import com.mvc.sell.pojo.vo.WithdrawInfoVO;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * TransactionService
 *
 * @author qiyichen
 * @create 2018/3/15 11:53
 */
@Service
public class TransactionService extends BaseService {

    public Result<ProjectInfoVO> projectInfo(BigInteger id) {
        return rpcProjectService.projectInfo(id);
    }

    public Result buy(BuyDTO buyDTO) {
        return rpcProjectService.buy(buyDTO);
    }

    public Result<List<String>> getTokens() {
        return rpcProjectService.config();
    }

    public Result<WithdrawInfoVO> getWithdrawConfig(String tokenName) {
        return rpcProjectService.getWithdrawConfig(tokenName);
    }

    public Result withdraw(WithdrawDTO withdrawDTO) {
        return rpcProjectService.withdraw(withdrawDTO);
    }
}
