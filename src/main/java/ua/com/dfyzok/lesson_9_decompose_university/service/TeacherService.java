package ua.com.dfyzok.lesson_9_decompose_university.service;

import java.util.List;
import java.util.Optional;

import ua.com.dfyzok.lesson_9_decompose_university.entity.Teacher;

public interface TeacherService {

    List<Teacher> getAll();

    Teacher addTeacher(Teacher teacher);

    void deleteTeacher(long id);

    Optional<Teacher> getById(long id);

    Teacher editTeacher(Teacher teacher);

}
