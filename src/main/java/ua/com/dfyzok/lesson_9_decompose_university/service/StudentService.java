package ua.com.dfyzok.lesson_9_decompose_university.service;

import java.util.List;
import java.util.Optional;

import ua.com.dfyzok.lesson_9_decompose_university.entity.Student;

public interface StudentService {

    List<Student> getAll();

    Student addStudent(Student student);

    void deleteStudent(long id);

    Optional<Student> getById(long id);

    Student editStudent(Student student);

}
