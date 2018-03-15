package com.mvc.sell.rpc;

import com.github.pagehelper.PageInfo;
import com.mvc.common.msg.Result;
import com.mvc.sell.pojo.dto.BuyDTO;
import com.mvc.sell.pojo.dto.MyProjectDTO;
import com.mvc.sell.pojo.dto.WithdrawDTO;
import com.mvc.sell.pojo.vo.MyProjectVO;
import com.mvc.sell.pojo.vo.ProjectInfoVO;
import com.mvc.sell.pojo.vo.WithdrawInfoVO;
import io.swagger.annotations.Api;
import io.swagger.models.HttpMethod;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * rpc projectService
 *
 * @author qiyichen
 * @create 2018/3/14 19:02
 */
@FeignClient("token-sell-console")
public interface RpcProjectService {

    @RequestMapping(value = "project/account/{id}", method = RequestMethod.GET)
    Result<MyProjectVO> get(@PathVariable("id") BigInteger id, @RequestParam Map<String, Object> myProjectDTO);

    @RequestMapping(value = "project/info/{id}", method = RequestMethod.GET)
    Result<ProjectInfoVO> projectInfo(@PathVariable("id") BigInteger id);

    @RequestMapping(value = "project/account", method = RequestMethod.GET)
    Result<PageInfo<MyProjectVO>> list(@RequestParam Map<String, Object> myProjectDTO);

    @RequestMapping(value = "project/buy", method = RequestMethod.POST)
    Result buy(@RequestBody BuyDTO buyDTO);

    @RequestMapping(value = "config/token", method = RequestMethod.GET)
    Result<List<String>> config();

    @RequestMapping(value = "project/config", method = RequestMethod.GET)
    Result<WithdrawInfoVO> getWithdrawConfig(@RequestParam("tokenName") String tokenName);

    @RequestMapping(value = "project/withdraw", method = RequestMethod.POST)
    Result withdraw(@RequestBody WithdrawDTO withdrawDTO);
}
