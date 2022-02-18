package ua.com.dfyzok.lesson_9_decompose_university.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.dfyzok.lesson_9_decompose_university.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
