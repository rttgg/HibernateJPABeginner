import com.hibernate.models.Address;
import com.hibernate.models.Department;
import com.hibernate.models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.List;

public class MainRunner {

    private static SessionFactory factory;
    public static void main(String[] args) {
        //to create session factory
        try {
            factory = new Configuration()
                    .configure(new File("src/main/resources/hibernate.cfg.xml"))
                    .buildSessionFactory();
        } catch (Throwable ex){
            ex.printStackTrace();
        }

        Session s = factory.openSession();
        Transaction t = s.beginTransaction();
        Employee e1 = new Employee("jafer", 70000);
        Employee e2 = new Employee("John", 60000);
        Employee e3= new Employee("Jane", 50000);
        Address a1 = new Address("123 ABC st", "", "charlotte", 22222, e1);
        Address a2 = new Address("aaa ABC st", "", "concord", 22222, e2);
        Department d1 = new Department(1,"place", "123 hello st.",e3);

        s.persist(a1);
        s.persist(e2);
        s.persist(e3);
        t.commit();
        List<Employee> e = s.createQuery("from Employee").list();

        System.out.println(e);

        s.close();

    }


}
