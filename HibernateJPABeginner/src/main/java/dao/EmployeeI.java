package dao;

import com.hibernate.models.Employee;

import java.util.List;

public interface EmployeeI {
    List<Employee> getAllEmployees();
    Employee createEmployee(Employee e);
}
