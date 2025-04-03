package org.example.serviceImpl;


import org.example.entity.Department;
import org.example.entity.Employee;
import org.example.repository.DepartmentRepository;
import org.example.repository.EmployeeRepository;
import org.example.request.EmployeeRequest;
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
    public long addNewEmployee(EmployeeRequest employeeRequest) {
        Optional<Department> department = departmentRepository.findById(employeeRequest.getDeptId());
        if (department.isEmpty()) {
            return -2;
        }
        Department dep=department.get();
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setAge(employeeRequest.getAge());
        employee.setPhone(employeeRequest.getPhone());
        employee.setDepartment(dep);
        employeeRepository.save(employee);
        return employee.getEmpId();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long empId) {
        Optional<Employee> employee=employeeRepository.findById(empId);
        if(employee.isEmpty()){
            return null;
        }
        return employee.get();
    }

    @Override
    public long updateEmployee(EmployeeRequest updatedEmployeeRequest, long empId) {
        Optional<Employee> emp = employeeRepository.findById(empId);
        Optional<Department> dep = departmentRepository.findById(updatedEmployeeRequest.getDeptId());
        if (emp.isEmpty()) {
            return -1;
        } else if (dep.isEmpty()) {
            return -2;
        }
        Employee employee=emp.get();
        employee.setName(updatedEmployeeRequest.getName());
        employee.setAge(updatedEmployeeRequest.getAge());
        employee.setPhone(updatedEmployeeRequest.getPhone());
        employee.setDepartment(dep.get());
        employeeRepository.save(employee);
        return empId;
    }

    @Override
    public long deleteEmployee(long empId) {
        Optional<Employee> emp = employeeRepository.findById(empId);
        if (emp.isPresent()) {
            employeeRepository.delete(emp.get());
            return empId;
        }
        return -1;
    }
}
