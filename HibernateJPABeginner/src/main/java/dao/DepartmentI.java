package dao;

import com.hibernate.models.Department;

import java.util.List;

public interface DepartmentI {

    List<Department> getAllDepartment();
    Department createDepartment(Department d);
    boolean updateDepartment(Department d);
    boolean deleteDepartment(Department d);
    List<Department> findDepartmentlist(String name);


}
