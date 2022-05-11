package com.hibernate;

import com.hibernate.models.Address;
import com.hibernate.models.Department;
import com.hibernate.models.Employee;
import com.hibernate.services.DepartmentService;
import com.hibernate.services.EmployeeService;
import com.hibernate.util.HibernateUtil;
import lombok.extern.java.Log;
import org.hibernate.Session;

@Log
public class MainRunner {

    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeService();
        DepartmentService departmentService = new DepartmentService();

        Employee e1 = new Employee("jafer", 70000);
        Address a1 = new Address("123 st", "", "plano", 75000, e1);

        employeeService.createEmployee(new Employee("john", 60000));
        employeeService.createEmployee(new Employee("jane", 50000));

        boolean saved = employeeService.createEmployeeAndAddress(a1);

                e1.setName("someone else");
        e1.setSalary(101010);
        boolean updated = employeeService.updateEmployee(e1);
        log.info("Saved: "+ Boolean.toString(saved));
        log.info("Updated: "+ Boolean.toString(updated));
        log.info("allEmployees: " +employeeService.getAllEmployees().toString());
        log.info("employeebyid: " + employeeService.getEmployeeById(1).toString());
        log.info("EmployeeSalaryGreaterThan: " + employeeService.findEmployeeSalaryGreaterThan(100000).toString());
        log.info("deleted employee id 1: " + Boolean.toString(employeeService.deleteEmployee(employeeService.getEmployeeById(1))));
        log.info("find employee address: " + employeeService.findEmployeeAddresses(e1).toString());

        log.info(employeeService.EmployeeIdAndName().toString());
        Address a5 = new Address("555 st", "", "seattle", 98109, new Employee());
        employeeService.addAddress(a5, 3);
        log.info(employeeService.findEmployeeAddresses(e1).toString());
        Address a6 = new Address("252 st", "", "shoreline", 98111, new Employee());
        employeeService.addAddress(a6,2);
        Employee someone = new Employee("", 12);
        someone.setId(2);
        log.info(employeeService.findEmployeeAddresses(someone).toString());

        Department d = new Department("dep4");
        Session s = HibernateUtil.getSessionFactory().openSession();
        departmentService.addDepartmentAndEmployee(d);























//
//
//        Department d1 = new Department(4, "trying", "wa");

//        departmentService.createDepartment(new Department("dep1", "wa"));
//        departmentService.createDepartment(new Department("dep2", "NC"));
//

//

////

//
//
//        //Department
//
//
//
//        //Department d22 = new Department();
//       // Employee e2 = new Employee("Brooke", 90000);
//
//        d1.setName("Helllo");
//        d1.setState("WAWA");
//        boolean updates = departmentService.updateDepartment(d1);
//        log.info("updates department: " + Boolean.toString(updates));
//        log.info("allDepartment: " + departmentService.getAllDepartment().toString());
//

    } // main method
} // MainRunner
