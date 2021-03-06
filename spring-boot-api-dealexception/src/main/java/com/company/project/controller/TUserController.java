package com.company.project.controller;
import com.alibaba.fastjson.JSON;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TUser;
import com.company.project.service.TUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/03/27.
*/
@RestController
@RequestMapping("/t/user")
public class TUserController {
    @Resource
    private TUserService tUserService;

    @PostMapping("/add")
    public Result add(TUser tUser) {
        tUserService.save(tUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        tUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TUser tUser) {
        tUserService.update(tUser);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/detail")
    public Result detail(Integer id) {
        TUser tUser = tUserService.findById(id);
        return ResultGenerator.genSuccessResult(tUser);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TUser> list = tUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
