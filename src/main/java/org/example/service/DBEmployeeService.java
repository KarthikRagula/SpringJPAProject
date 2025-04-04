package org.example.service;

import org.example.entity.Employee;

import java.util.List;

public interface DBEmployeeService {
    Employee addNewEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(long empId);

    Employee updateEmployee(Employee updatedEmployee, long empId);

    long deleteEmployee(long empId);
}
