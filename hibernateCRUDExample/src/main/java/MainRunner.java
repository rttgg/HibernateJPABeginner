import model.Student;
import services.StudentService;

import java.util.List;

public class MainRunner {

    public static void main(String[] args) {

        StudentService studentService = new StudentService();

        //test saveStudent
        Student student1 = new Student("Ramesh", "Fedatare", "rames@gmail.com");
        Student student3 = new Student("Tedd", "tedd", "tedd@gmail.com");
        Student student4 = new Student("ro", "ro", "ro@gmail.com");
        studentService.saveStudent(student1);
        studentService.saveStudent(student3);
        studentService.saveStudent(student4);

        //test updateStudent
        student1.setFirstName("roze");
        studentService.updateStudent(student1);

        //test getStudentById
        Student student2 = studentService.getStudentById(student1.getId());
        System.out.println("get student by id: " + student2.getId());

        //test getAllStudents
        List<Student> students = studentService.getAllStudents();
        students.forEach(student -> System.out.println(student.getId()));
        System.out.println("list of all student: " + studentService.getAllStudents());

        //test deleteStudent
        studentService.deleteStudent(student1.getId());


    }
}
