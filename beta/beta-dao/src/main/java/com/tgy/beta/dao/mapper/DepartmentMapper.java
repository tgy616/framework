package com.tgy.beta.dao.mapper;

import com.tgy.beta.dao.entity.Department;
import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String departmentId);

    int insert(Department record);

    Department selectByPrimaryKey(String departmentId);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);
}