package com.hibernate.services;

import com.hibernate.dao.EmployeeI;
import com.hibernate.models.Address;
import com.hibernate.models.Employee;
import com.hibernate.util.HibernateUtil;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.java.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log
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
            if (tx!=null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return e;
    }

    @Override
    public boolean createEmployeeAndAddress(Address a) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();

            s.persist(a);
            tx.commit();
            return true;
        } catch (HibernateException exception) {
            if (tx!=null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return false;
    }

    @Override
    public boolean updateEmployee(Employee e) {

        Session s = HibernateUtil.getSessionFactory().openSession();
        String hql = "UPDATE Employee set name = :name , salary = :salary where id = :id";
        Transaction tx = null;

        try {
            if(s.get(Employee.class, e.getId()) == null){
                throw new HibernateException("Employee with ID " + e.getId() + " Not Found!");
            }


            tx = s.beginTransaction();
            Query q = s.createQuery(hql);
            q.setParameter("name", e.getName());
            q.setParameter("salary", e.getSalary());
            q.setParameter("id", e.getId());
            int affected = q.executeUpdate();
            log.info("Rows affected from updating: " + affected);
            tx.commit();
            return true;
        } catch (HibernateException exception) {
            if (tx!=null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return false;



    }

    @Override
    public boolean deleteEmployee(Employee e) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();
            s.delete(e);
            tx.commit();
            return true;
        } catch (HibernateException exception) {
            if (tx!=null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }

        return false;

    }

    @Override
    public Employee getEmployeeById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            Employee e = s.get(Employee.class,id);
            if(e == null)
                throw new HibernateException("Did not find employee");
            else
                return e;

        } catch (HibernateException exception) {
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return new Employee();
    }

    @Override
    public List<Employee> findEmployeeSalaryGreaterThan(double salary) {

        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.select(root).where(cb.gt(root.get("salary"), salary));
            Query<Employee> q = s.createQuery(cr);
            return q.getResultList();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            s.close();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Address> findEmployeeAddresses(Employee e) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            Query<Address> q = s.createNamedQuery("findEmployeeAddresses", Address.class);
            q.setParameter("e", e);
            return q.getResultList();

        } catch (HibernateException exception) {
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return new ArrayList<>();
    }

    @Override
    public Map<Integer, String> EmployeeIdAndName() {
        String hql = "select e.id as id, e.name as name, a.city as city from Employee e join Address as a";
        Session s = HibernateUtil.getSessionFactory().openSession();
        Map<Integer, String> employeeMap = null;
        try {
            employeeMap = s.createQuery(hql, Tuple.class).getResultStream()
                    .collect(
                            Collectors.toMap(
                                    tuple -> ((Number) tuple.get("id")).intValue(),
                                    tuple -> ((String) tuple.get("name"))
                            )
                    );
            List<Object[]> objectList = s.createQuery(hql).list();
            for(Object[] c : objectList)
                System.out.println(Arrays.toString(c));

        } catch (HibernateException exception) {
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return employeeMap;
    }

    @Override
    public void addAddress(Address a, int emp_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            a.setEmployee(s.get(Employee.class, emp_id));
            s.merge(a);
            tx.commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
            if(tx != null) tx.rollback();
        } finally {
            s.close();
        }
    }

}
