package org.example.controller;

import org.example.entity.Department;
import org.example.response.ResponseMessage;
import org.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        Department dept = departmentService.addNewDepartment(department);
        return new ResponseEntity<>(dept, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getDepartment() {
        List<Department> departmentList = departmentService.getAllDepartments();
        if (departmentList.isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("Department list is empty"), HttpStatus.OK);
        }
        return new ResponseEntity<>(departmentList, HttpStatus.OK);
    }

    @GetMapping("/{deptId}")
    public ResponseEntity<?> getDepartmentById(@PathVariable long deptId) {
        Department dept = departmentService.getDepartmentById(deptId);
        return new ResponseEntity<>(dept, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateDepartment(@RequestBody Department updatedDepartment) {
        Department department = departmentService.updateDepartment(updatedDepartment);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @DeleteMapping("/{deptId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable long deptId) {
        long departmentId = departmentService.deleteDepartment(deptId);
        return new ResponseEntity<>(new ResponseMessage("Department with id " + departmentId + " deleted successfully"), HttpStatus.OK);
    }
}