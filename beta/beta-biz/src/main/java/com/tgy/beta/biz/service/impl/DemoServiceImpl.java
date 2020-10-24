package com.tgy.beta.biz.service.impl;

import com.tgy.beta.biz.service.DemoService;
import com.tgy.beta.dao.entity.Employee;
import com.tgy.beta.dao.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DragonSwimDiving
 * @program beta
 * @Date 2020-07-02 16:09
 **/
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public String test() {
        Employee employee = employeeMapper.selectByPrimaryKey(1);
        return employee.toString();
    }
}
