package ua.com.dfyzok.lesson_9_decompose_university.service.implementation;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.dfyzok.lesson_9_decompose_university.db.repository.TimeTableRepository;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Lesson;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.GroupNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.TeacherNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.service.TimeTableService;

@Service
public class TimeTableServiceImpl implements TimeTableService {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Override
    public List<Lesson> fetchTimeTableStudent(String dateBegin, String dateEnd, Long groupId)
            throws SQLException, GroupNotFoundException {
        boolean exist = timeTableRepository.existsById(groupId);
        if (exist) {
            return timeTableRepository.fetchTimeTableStudent(dateBegin, dateEnd, groupId);
        } else
            throw new GroupNotFoundException(groupId);
    }

    @Override
    public List<Lesson> fetchTimeTableTeacher(String dateBegin, String dateEnd, Long teacherId)
            throws SQLException, TeacherNotFoundException {
        boolean exist = timeTableRepository.existsById(teacherId);
        if (exist) {
            return timeTableRepository.fetchTimeTableTeacher(dateBegin, dateEnd, teacherId);
        } else
            throw new TeacherNotFoundException(teacherId);
    }

}
