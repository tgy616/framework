package com.tgy.springbootaddmybatisandmubatisgenerater.controller;

import com.tgy.springbootaddmybatisandmubatisgenerater.entity.Employee;
import com.tgy.springbootaddmybatisandmubatisgenerater.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * test eg
 *
 * @author DragonSwimDiving
 * @program SBaddmybatisandmubatisgenerater
 * @Date 2020-06-09 10:04
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/getEmployee",method = RequestMethod.GET)
    public Employee getById(Integer id){
        return testService.getById(id);
    }
    @GetMapping("/test")
    public String test(){
        return "test context";
    }
}
