package org.example.serviceImpl;

import jakarta.transaction.Transactional;
import org.example.entity.Department;
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
//        int a=5/0;
        return department.getDeptId();
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(long deptId) {
        Optional<Department> dep=departmentRepository.findById(deptId);
        if(dep.isEmpty()){
            return null;
        }
        return dep.get();
    }

    @Override
    public long updateDepartment(Department dep, long deptId) {
        Optional<Department> department = departmentRepository.findById(deptId);
        if(department.isEmpty()){
            return -1;
        }
        Department department1=department.get();
        department1.setDeptName(dep.getDeptName());
        departmentRepository.save(department1);
        return deptId;
    }

    @Override
    public long deleteDepartment(long deptId) {
        Optional<Department> department = departmentRepository.findById(deptId);
        if(department.isEmpty()){
            return -1;
        }
        departmentRepository.delete(department.get());
        return deptId;
    }
}
