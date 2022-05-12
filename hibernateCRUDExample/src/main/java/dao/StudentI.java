package dao;

import model.Student;

import java.util.List;

public interface StudentI {

    //save Student
    void saveStudent(Student s);

    //get All Students
    List<Student> getAllStudents();


    //get Student By Id
    Student getStudentById(long id);

    //Update Student
    void updateStudent(Student s);


    void deleteStudent(long id);
    //Delete Student
}
