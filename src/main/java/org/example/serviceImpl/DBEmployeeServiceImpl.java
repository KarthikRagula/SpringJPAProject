package org.example.serviceImpl;


import org.example.entity.Department;
import org.example.entity.Employee;
import org.example.exception.DepartmentNotFoundException;
import org.example.exception.EmployeeNotFoundException;
import org.example.repository.DepartmentRepository;
import org.example.repository.EmployeeRepository;
import org.example.service.DBEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DBEmployeeServiceImpl implements DBEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public long addNewEmployee(Employee employee) {
        Optional<Department> department = departmentRepository.findById(employee.getDepartment().getDeptId());
        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("Department with id " + employee.getDepartment().getDeptId() + " not found");
        }
        employeeRepository.save(employee);
        return employee.getEmpId();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long empId) {
        Optional<Employee> employee = employeeRepository.findById(empId);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee with id " + empId + " not found");
        }
        return employee.get();
    }

    @Override
    public long updateEmployee(Employee updatedEmployee, long empId) {
        Optional<Employee> emp = employeeRepository.findById(empId);
        Optional<Department> dep = departmentRepository.findById(updatedEmployee.getDepartment().getDeptId());
        if (emp.isEmpty()) {
            throw new EmployeeNotFoundException("Employee with id " + empId + " not found");
        } else if (dep.isEmpty()) {
            throw new DepartmentNotFoundException("Department with id " + updatedEmployee.getDepartment().getDeptId() + " not found");
        }
        updatedEmployee.setEmpId(empId);
        employeeRepository.save(updatedEmployee);
        return empId;
    }

    @Override
    public long deleteEmployee(long empId) {
        Optional<Employee> emp = employeeRepository.findById(empId);
        if (emp.isEmpty()) {
            throw new EmployeeNotFoundException("Employee with id " + empId + " not found");
        }
        employeeRepository.delete(emp.get());
        return empId;
    }
}
