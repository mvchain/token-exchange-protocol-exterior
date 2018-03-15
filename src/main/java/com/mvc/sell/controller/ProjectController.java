package com.mvc.sell.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.mvc.common.msg.Result;
import com.mvc.sell.pojo.dto.MyProjectDTO;
import com.mvc.sell.pojo.vo.MyProjectVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

/**
 * project controller
 *
 * @author qiyichen
 * @create 2018/3/10 15:52
 */
@RestController
@RequestMapping("project")
public class ProjectController extends BaseController{
    @ApiOperation("项目列表")
    @GetMapping
    Result<PageInfo<MyProjectVO>> list(@ModelAttribute MyProjectDTO myProjectDTO){
        return projectService.list(myProjectDTO);
    }

    @ApiOperation("项目详情")
    @GetMapping("{id}")
    Result<MyProjectVO> entity(@PathVariable BigInteger id) {
        return projectService.get(id);
    }

}
