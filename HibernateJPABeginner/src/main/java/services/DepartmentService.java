package services;

import com.hibernate.models.Department;
import dao.DepartmentI;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class DepartmentService implements DepartmentI {


    @Override
    public List<Department> getAllDepartment() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Department> d = s.createQuery("from Department", Department.class).list();
        s.close();
        return d;
    }

    @Override
    public Department createDepartment(Department d) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();
            s.persist(d);
            tx.commit();
        } catch (HibernateException exception) {
            if (tx!=null) tx.rollback();
            exception.printStackTrace();

        } finally {
            s.close();
        }
        return d;
    }

    @Override
    public boolean updateDepartment(Department d) {

        Session s = HibernateUtil.getSessionFactory().openSession();
        String hql = "UPDATE Department set name = :name, state = :state where did = :did";
        Transaction tx = null;

        try {
            if(s.get(Department.class, d.getDid()) == null) {
                throw new HibernateException("Department with did " + d.getDid() + " Not Found!");
            }

            tx = s.beginTransaction();
            Query q = s.createQuery(hql);
            q.setParameter("name", d.getName());
            q.setParameter("state", d.getState());
            q.setParameter("did", d.getDid());
            int affected = q.executeUpdate();
            tx.commit();
            return true;
        } catch (HibernateException exception) {
            if (tx != null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return false;
    }

    @Override
    public boolean deleteDepartment(Department d) {
        return false;
    }

    @Override
    public List<Department> findDepartmentlist(String name) {
        return null;
    }
}

