package org.example.service;

import org.example.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department addNewDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentById(long deptId);

    Department updateDepartment(Department department, long deptId);

    long deleteDepartment(long deptId);
}
