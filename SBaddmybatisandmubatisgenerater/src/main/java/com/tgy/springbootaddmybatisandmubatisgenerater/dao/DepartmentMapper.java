package com.tgy.springbootaddmybatisandmubatisgenerater.dao;

import com.tgy.springbootaddmybatisandmubatisgenerater.entity.Department;
import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String departmentId);

    int insert(Department record);

    Department selectByPrimaryKey(String departmentId);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);
}