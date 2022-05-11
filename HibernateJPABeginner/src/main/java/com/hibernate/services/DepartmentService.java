package com.hibernate.services;

import com.hibernate.models.Department;
import com.hibernate.models.Employee;
import com.hibernate.dao.DepartmentI;
import lombok.extern.java.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.hibernate.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Log
public class DepartmentService implements DepartmentI {


    @Override
    public List<Department> getAllDepartment() {
        Session sd = HibernateUtil.getSessionFactory().openSession();
        List<Department> d = sd.createQuery("from Department", Department.class).list();
        sd.close();
        return d;
    }

    @Override
    public Department createDepartment(Department d) {
        Session sd = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = sd.beginTransaction();
            sd.persist(d);
            tx.commit();
        } catch (HibernateException exception) {
            if (tx!=null) tx.rollback();
            exception.printStackTrace();

        } finally {
            sd.close();
        }
        return d;
    }

    @Override
    public boolean updateDepartment(Department d) {

        Session sd = HibernateUtil.getSessionFactory().openSession();
        String hql = "UPDATE Department set name = :name, state = :state where did = :did";
        Transaction tx = null;

        try {
            if(sd.get(Department.class, d.getDid()) == null) {
                throw new HibernateException("Department with did " + d.getDid() + " Not Found!");
            }

            tx = sd.beginTransaction();
            Query q1 = sd.createQuery(hql, Department.class);// Add this Department.class to avoid deprecated on createQuery
            q1.setParameter("name", d.getName());
            q1.setParameter("state", d.getState());
            q1.setParameter("did", d.getDid());
            int affected = q1.executeUpdate();
            log.info("Rows affected from updating department: " + affected);
            tx.commit();
            return true;
        } catch (HibernateException exception) {
            if (tx != null) tx.rollback();
            exception.printStackTrace();
        } finally {
            sd.close();
        }
        return false;
    }

    @Override
    public boolean deleteDepartment(Department d) {
        Session sd = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = sd.beginTransaction();
            sd.delete(d);
            tx.commit();
            return true;
        } catch (HibernateException exception) {
            if (tx != null)
                tx.rollback();
            exception.printStackTrace();
        } finally {
            sd.close();
        }
        return false;
    }


    @Override
    public List<Employee> findDepartmentOfEmployee(Department d) {
        Session sd = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = sd.beginTransaction();
            Query<Employee> q = sd.createNamedQuery("findDepartmentOfEmployee", Employee.class);
            q.setParameter("d", d);
            return q.getResultList();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        } finally {
            sd.close();
        }
        return new ArrayList<>();
    }

    @Override
    public void addDepartmentAndEmployee(Department d) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();

            s.persist(d);
            tx.commit();
        } catch (HibernateException exception) {
            if(tx != null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }

    }

    @Override
    public void addEmployeesToDepartment(Set<Employee> employees) {

    }
}

