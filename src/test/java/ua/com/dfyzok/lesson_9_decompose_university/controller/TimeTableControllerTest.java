package ua.com.dfyzok.lesson_9_decompose_university.controller;

import static org.mockito.Mockito.times;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.ui.Model;

import ua.com.dfyzok.lesson_9_decompose_university.dto.LessonSlotDto;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Lesson;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.GroupNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.TeacherNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.service.TimeTableService;

@RunWith(MockitoJUnitRunner.class)
public class TimeTableControllerTest {

    @Mock
    TimeTableService timeTableService;
    @Mock
    Model model;

    @InjectMocks
    TimeTableController timeTableController;

    // @DateTimeFormat(iso = ISO.DATE)
    String dateTestBegin = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date(1));
    // @DateTimeFormat(iso = ISO.DATE)
    String dateTestEnd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date(2));

    @Test
    public void whenMockTimeTableService_thenTimeTableControllerCorrectTimeTableStudentFormGet() throws SQLException {
        TimeTableController timeTableController = new TimeTableController(timeTableService);
        timeTableController.timeTableStudentFormGet(model);
        Mockito.verify(model, times(1)).addAttribute(Mockito.anyString(), Mockito.notNull(LessonSlotDto.class));
        Mockito.verifyNoMoreInteractions(model);
    }

    @Test
    public void whenMockTimeTableService_thenTimeTableControllerCorrectTimeTableTeacherFormGet() throws SQLException {
        TimeTableController timeTableController = new TimeTableController(timeTableService);
        timeTableController.timeTableTeacherFormGet(model);
        Mockito.verify(model, times(1)).addAttribute(Mockito.anyString(), Mockito.notNull(LessonSlotDto.class));
        Mockito.verifyNoMoreInteractions(model);
    }

    @Test
    public void whenMockTimeTableService_thenTimeTableControllerCorrectTimeTableStudent()
            throws SQLException, GroupNotFoundException {
        TimeTableController timeTableController = new TimeTableController(timeTableService);
        Collection<Lesson> timeTableList = new ArrayList();
        LessonSlotDto lessonSlotDto = new LessonSlotDto();
        Long studentId = 0L;
        Mockito.lenient().doReturn(timeTableList).when(timeTableService).fetchTimeTableStudent(
                Mockito.notNull(String.class), Mockito.notNull(String.class), (long) Mockito.anyInt());
        lessonSlotDto.setTimeBegin(dateTestBegin);
        lessonSlotDto.setTimeEnd(dateTestEnd);
        lessonSlotDto.setUserId(studentId);
        timeTableController.timeTableStudent(model, lessonSlotDto);
        Mockito.verify(timeTableService, times(1)).fetchTimeTableStudent(dateTestBegin, dateTestEnd, studentId);
        Mockito.verify(model, times(1)).addAttribute(Mockito.anyString(), Mockito.notNull(Collection.class));
        Mockito.verifyNoMoreInteractions(timeTableService);
    }

    @Test
    public void whenMockTimeTableService_thenTimeTableControllerCorrectTimeTableTeacher()
            throws SQLException, TeacherNotFoundException {
        TimeTableController timeTableController = new TimeTableController(timeTableService);
        Collection<Lesson> timeTableList = new ArrayList();
        LessonSlotDto lessonSlotDto = new LessonSlotDto();
        Long teacherId = 0L;
        Mockito.lenient().doReturn(timeTableList).when(timeTableService).fetchTimeTableTeacher(
                Mockito.notNull(String.class), Mockito.notNull(String.class), (long) Mockito.anyInt());
        lessonSlotDto.setTimeBegin(dateTestBegin);
        lessonSlotDto.setTimeEnd(dateTestEnd);
        lessonSlotDto.setUserId(teacherId);
        timeTableController.timeTableTeacher(model, lessonSlotDto);
        Mockito.verify(timeTableService, times(1)).fetchTimeTableTeacher(dateTestBegin, dateTestEnd, teacherId);
        Mockito.verify(model, times(1)).addAttribute(Mockito.anyString(), Mockito.notNull(Collection.class));
        Mockito.verifyNoMoreInteractions(timeTableService);
    }

}
