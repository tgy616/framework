package com.tgy.springbootaddmybatisandmubatisgenerater.dao;

import com.tgy.springbootaddmybatisandmubatisgenerater.entity.Employee;
import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Integer id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);
}