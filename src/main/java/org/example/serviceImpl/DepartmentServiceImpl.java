package org.example.serviceImpl;

import jakarta.transaction.Transactional;
import org.example.entity.Department;
import org.example.exception.DepartmentNotFoundException;
import org.example.repository.DepartmentRepository;
import org.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public long addNewDepartment(Department department) {
        departmentRepository.save(department);
        return department.getDeptId();
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(long deptId) {
        Optional<Department> dep = departmentRepository.findById(deptId);
        if (dep.isEmpty()) {
            throw new DepartmentNotFoundException("Department with id " + deptId + " not found");
        }
        return dep.get();
    }

    @Override
    public long updateDepartment(Department dep, long deptId) {
        Optional<Department> department = departmentRepository.findById(deptId);
        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("Department with id " + deptId + " not found");
        }
        dep.setDeptId(deptId);
        departmentRepository.save(dep);
        return deptId;
    }

    @Override
    public long deleteDepartment(long deptId) {
        Optional<Department> department = departmentRepository.findById(deptId);
        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("Department with id " + deptId + " not found");
        }
        departmentRepository.delete(department.get());
        return deptId;
    }
}
