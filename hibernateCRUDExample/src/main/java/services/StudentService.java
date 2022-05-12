package services;

import dao.StudentI;
import model.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class StudentService implements StudentI {

    @Override
    public void saveStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //start the transaction
            transaction = session.beginTransaction();

            //save student object
            session.save(student);

            //commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void updateStudent(Student s) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //start the transaction
            transaction = session.beginTransaction();

            //save student object
            session.saveOrUpdate(s);

            //commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public Student getStudentById(long id) {
        Transaction transaction = null;
        Student student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //start the transaction
            transaction = session.beginTransaction();

            //save student object
            student = session.get(Student.class, id);

            //commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return student;

    }

    @Override
    public List<Student> getAllStudents() {
        Transaction transaction = null;
        List<Student> students = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //start the transaction
            transaction = session.beginTransaction();

            //save student object
            students = session.createQuery("from Student").list();

            //commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return students;

    }

    @Override
    public void deleteStudent(long id) {
        Transaction transaction = null;
        Student student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //start the transaction
            transaction = session.beginTransaction();

            student = session.get(Student.class, id);
            //get specific student to delete
            session.delete(student);

            //commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }


    }
}