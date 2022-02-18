package ua.com.dfyzok.lesson_9_decompose_university.db.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.dfyzok.lesson_9_decompose_university.entity.Lesson;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.GroupNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.TeacherNotFoundException;

public interface TimeTableRepository extends JpaRepository<Lesson, Long> {

    @Query("FROM lesson WHERE lesson_time BETWEEN ?1 AND ?2 AND lesson_group_id = ?3")
    List<Lesson> fetchTimeTableStudent(String dateBegin, String dateEnd, Long groupId)
            throws SQLException, GroupNotFoundException;

    @Query(value = "FROM lesson WHERE lesson_time BETWEEN ?1 AND ?2 AND lesson_teacher_id = ?3", nativeQuery = true)
    List<Lesson> fetchTimeTableTeacher(String dateBegin, String dateEnd, Long teacherId)
            throws SQLException, TeacherNotFoundException;
}
