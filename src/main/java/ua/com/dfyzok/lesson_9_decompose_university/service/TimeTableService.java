package ua.com.dfyzok.lesson_9_decompose_university.service;

import java.sql.SQLException;
import java.util.List;

import ua.com.dfyzok.lesson_9_decompose_university.entity.Lesson;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.GroupNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.TeacherNotFoundException;

public interface TimeTableService {

    List<Lesson> fetchTimeTableStudent(String dataBegin, String dataEnd, Long groupId)
            throws SQLException, GroupNotFoundException;

    List<Lesson> fetchTimeTableTeacher(String dateBegin, String dateEnd, Long teacherId)
            throws SQLException, TeacherNotFoundException;
}
