package services;

import com.hibernate.models.Employee;

import dao.EmployeeI;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class EmployeeService implements EmployeeI {
    @Override
    public List<Employee> getAllEmployees() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Employee> e = s.createQuery("from Employee",Employee.class).list();
        s.close();
        return e;
    }

    @Override
    public Employee createEmployee(Employee e) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();
            s.persist(e);
            tx.commit();
        } catch (HibernateException exception) {
            if(tx!=null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return e;




    }
}
