package com.mvc.sell.controller;

import com.github.pagehelper.Page;
import com.mvc.common.msg.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * project controller
 *
 * @author qiyichen
 * @create 2018/3/10 15:52
 */
@Controller
@RequestMapping("project")
public class ProjectController extends BaseController{

    /**
     * get all project by page
     * @param page
     * @param type
     * @return
     */
    @GetMapping
    Result list(@ModelAttribute @Valid Page page, @RequestParam Integer type){
        return projectService.list(page, type);
    }

    /**
     * get project info by id
     * @param id
     * @return
     */
    @GetMapping("{id}")
    Result entity(@PathVariable Integer id) {
        return projectService.get(id);
    }


}
