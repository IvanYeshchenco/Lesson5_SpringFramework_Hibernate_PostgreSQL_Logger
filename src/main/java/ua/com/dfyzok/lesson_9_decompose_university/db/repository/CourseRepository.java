package ua.com.dfyzok.lesson_9_decompose_university.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.dfyzok.lesson_9_decompose_university.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
