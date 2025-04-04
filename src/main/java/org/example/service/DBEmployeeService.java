package org.example.service;

import org.example.entity.Employee;

import java.util.List;

public interface DBEmployeeService {
    long addNewEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(long empId);

    long updateEmployee(Employee updatedEmployee, long empId);

    long deleteEmployee(long empId);
}
