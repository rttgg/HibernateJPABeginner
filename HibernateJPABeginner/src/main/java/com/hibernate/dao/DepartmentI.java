package com.hibernate.dao;

import com.hibernate.models.Department;
import com.hibernate.models.Employee;

import java.util.List;
import java.util.Set;

public interface DepartmentI {

    List<Department> getAllDepartment();
    Department createDepartment(Department d);
    boolean updateDepartment(Department d);
    boolean deleteDepartment(Department d);
    List<Employee> findDepartmentOfEmployee(Department d);


    //after the third branch
    void addDepartmentAndEmployee(Department d);
    void addEmployeesToDepartment(Set<Employee> employees);


}
