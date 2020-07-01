package com.tgy.springbootaddmybatisandmubatisgenerater;

import com.tgy.springbootaddmybatisandmubatisgenerater.dao.EmployeeMapper;
import com.tgy.springbootaddmybatisandmubatisgenerater.entity.Employee;
import org.springframework.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootaddmybatisandmubatisgeneraterApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void test1(){
        Employee employee=employeeMapper.selectByPrimaryKey(6);
        Assert.notNull(employee,"用户不存在");
    }

    @Test
    public void test2(){
        Employee employee=employeeMapper.selectByPrimaryKey(1);
        if (employee == null) {
            throw new IllegalArgumentException("用户不存在");
        }
    }

}
