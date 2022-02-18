package ua.com.dfyzok.lesson_9_decompose_university.service.implementation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ua.com.dfyzok.lesson_9_decompose_university.db.repository.LessonRepository;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Lesson;

@RunWith(MockitoJUnitRunner.class)
public class TestLessonServiceImpl {
    @Mock
    private LessonRepository lessonRepository;

    @InjectMocks
    LessonServiceImpl lessonServiceImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectLessonGetAll() throws SQLException {
        List<Lesson> lessonList = new ArrayList<Lesson>();
        List<Lesson> lessonListActual = new ArrayList<Lesson>();
        Lesson lesson = new Lesson();
        lesson.setLessonName("lesson");
        lesson.setLessonId(1L);
        lesson.setCourseName("History");
        Lesson lesson1 = new Lesson();
        lesson1.setLessonName("lesson1");
        lesson1.setLessonId(2L);
        lesson1.setCourseName("Mathematic");
        lessonList.add(lesson);
        lessonList.add(lesson1);
        when(lessonServiceImpl.getAll()).thenReturn(lessonList);
        lessonListActual = lessonServiceImpl.getAll();

        assertEquals(lessonList, lessonListActual);
        Mockito.verify(lessonRepository, times(1)).findAll();
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectLessonGetById() throws SQLException {
        Lesson lesson = new Lesson();
        Lesson lessonActual = new Lesson();
        final Long id = 1L;
        lesson.setLessonName("lesson");
        lesson.setLessonId(id);
        lesson.setCourseName("History");
        when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));
        lessonActual = lessonServiceImpl.getById(id).get();

        assertEquals(lesson, lessonActual);
        Mockito.verify(lessonRepository, times(1)).findById(1L);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectLessonCreate() throws SQLException {
        Lesson lessonActual = new Lesson();
        final Long id = 1L;
        lessonActual.setLessonName("lesson1");
        lessonActual.setLessonId(id);
        lessonActual.setVersion(id);
        lessonActual.setCourseName("History");
        lessonServiceImpl.addLesson(lessonActual);

        Mockito.verify(lessonRepository, times(1)).save(lessonActual);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectLessonUpdate() throws SQLException {
        Lesson lesson = new Lesson();
        final Long id = 1L;
        lesson.setLessonName("lesson1");
        lesson.setLessonId(id);
        lesson.setCourseName("History");
        lessonServiceImpl.editLesson(lesson);

        Mockito.verify(lessonRepository, times(1)).save(lesson);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectLessonDelete() throws SQLException {
        final Long id = 1L;
        lessonServiceImpl.deleteLesson(id);

        Mockito.verify(lessonRepository, times(1)).deleteById(1L);
    }
}
