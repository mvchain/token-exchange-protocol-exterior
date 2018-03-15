package com.mvc.sell.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.mvc.common.context.BaseContextHandler;
import com.mvc.common.msg.Result;
import com.mvc.sell.pojo.dto.MyProjectDTO;
import com.mvc.sell.pojo.vo.MyProjectVO;
import com.mvc.sell.util.MapUtils;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * ProjectService
 *
 * @author qiyichen
 * @create 2018/3/14 19:03
 */
@Service
public class ProjectService extends BaseService {

    public Result<PageInfo<MyProjectVO>> list(MyProjectDTO myProjectDTO) {
        BigInteger userId = (BigInteger) BaseContextHandler.get("userId");
        myProjectDTO.setUserId(userId);
        return rpcProjectService.list(MapUtils.java2Map(myProjectDTO));
    }

    public Result<MyProjectVO> get(BigInteger id) {
        BigInteger userId = (BigInteger) BaseContextHandler.get("userId");
        MyProjectDTO myProjectDTO = new MyProjectDTO();
        myProjectDTO.setId(id);
        myProjectDTO.setUserId(userId);
        return rpcProjectService.get(id, MapUtils.java2Map(myProjectDTO));
    }
}
