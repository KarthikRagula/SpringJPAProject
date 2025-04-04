package org.example.controller;

import org.example.entity.Employee;
import org.example.response.ResponseMessage;
import org.example.service.DBEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dbemployee")
public class DBEmployeeController {

    @Autowired
    private DBEmployeeService dbEmployeeService;

    @PostMapping("/")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        Employee emp = dbEmployeeService.addNewEmployee(employee);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getEmployee() {
        List<Employee> employeeList = dbEmployeeService.getAllEmployees();
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("Employee list is empty"), HttpStatus.OK);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable long empId) {
        Employee emp = dbEmployeeService.getEmployeeById(empId);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee updatedEmployee, @PathVariable long empId) {
        Employee employee = dbEmployeeService.updateEmployee(updatedEmployee, empId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long empId) {
        long employeeId = dbEmployeeService.deleteEmployee(empId);
        return new ResponseEntity<>(new ResponseMessage("Employee with id " + employeeId + " deleted successfully"), HttpStatus.OK);
    }
}