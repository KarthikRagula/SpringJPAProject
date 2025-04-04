package org.example.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@SequenceGenerator(name="department_sequence", sequenceName = "department_sequence", allocationSize = 1)
@Table(name="DEPARTMENT")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_sequence")
    @Column(name="dept_id",nullable = false)
    private long deptId;
    @Column(name="department_name")
    private String deptName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees;

    public Department(long deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public Department() {
    }

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return deptId == that.deptId && Objects.equals(deptName, that.deptName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptId, deptName);
    }
}