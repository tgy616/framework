package com.tgy.springbootaddmybatisandmubatisgenerater.service.impl;

import com.tgy.springbootaddmybatisandmubatisgenerater.dao.EmployeeMapper;
import com.tgy.springbootaddmybatisandmubatisgenerater.entity.Employee;
import com.tgy.springbootaddmybatisandmubatisgenerater.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author DragonSwimDiving
 * @program SBaddmybatisandmubatisgenerater
 * @Date 2020-06-09 10:07
 **/
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }
}
