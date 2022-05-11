package dao;

import com.hibernate.models.Address;
import com.hibernate.models.Employee;

import java.util.List;

public interface EmployeeI {
    List<Employee> getAllEmployees();
    Employee createEmployee(Employee e);
    boolean createEmployeeAndAddress(Address a);
    boolean updateEmployee(Employee e);
    boolean deleteEmployee(Employee e);
    Employee getEmployeeById(int id);
    List<Employee> findEmployeeSalaryGreaterThan(double salary);
    List<Address> findEmployeeAddresses(Employee e);
}
