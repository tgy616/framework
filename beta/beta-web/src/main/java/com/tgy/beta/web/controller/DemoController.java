package com.tgy.beta.web.controller;

import com.tgy.beta.biz.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DragonSwimDiving
 * @program beta
 * @Date 2020-07-02 15:40
 **/
@RestController
@RequestMapping("/demo")
public class DemoController{

    @Autowired
    private DemoService demoService;

    @GetMapping("/test")
    public String test(){
        return demoService.test();
    }

}
