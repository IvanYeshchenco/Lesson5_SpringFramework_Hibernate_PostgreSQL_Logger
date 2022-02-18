package ua.com.dfyzok.lesson_9_decompose_university.service;

import java.util.List;
import java.util.Optional;

import ua.com.dfyzok.lesson_9_decompose_university.entity.Course;

public interface CourseService {

    List<Course> getAll();

    Course addCourse(Course course);

    Optional<Course> getById(long id);

    Course editCourse(Course course);

    void deleteCourse(long id);

}
