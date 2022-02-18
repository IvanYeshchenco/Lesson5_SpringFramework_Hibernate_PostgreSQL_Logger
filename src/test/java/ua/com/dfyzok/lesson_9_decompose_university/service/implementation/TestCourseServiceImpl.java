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

import ua.com.dfyzok.lesson_9_decompose_university.db.repository.CourseRepository;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Course;

@RunWith(MockitoJUnitRunner.class)
public class TestCourseServiceImpl {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    CourseServiceImpl courseServiceImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectCourseGetAll() throws SQLException {
        List<Course> courseList = new ArrayList<Course>();
        List<Course> courseListActual = new ArrayList<Course>();
        Course course = new Course();
        course.setCourseName("course");
        course.setCourseId(1L);
        Course course1 = new Course();
        course1.setCourseName("course1");
        course1.setCourseId(2L);
        courseList.add(course);
        courseList.add(course1);
        when(courseServiceImpl.getAll()).thenReturn(courseList);
        courseListActual = courseServiceImpl.getAll();

        assertEquals(courseList, courseListActual);
        Mockito.verify(courseRepository, times(1)).findAll();
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectCourseGetById() throws SQLException {
        Course course = new Course();
        Course courseActual = new Course();
        final Long id = 1L;
        course.setCourseName("course");
        course.setCourseId(id);
        when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(course));
        courseActual = courseServiceImpl.getById(id).get();

        assertEquals(course, courseActual);
        Mockito.verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectCourseCreate() throws SQLException {
        Course courseActual = new Course();
        final Long id = 1L;
        courseActual.setCourseName("course1");
        courseActual.setCourseId(id);
        courseActual.setVersion(id);
        courseServiceImpl.addCourse(courseActual);

        Mockito.verify(courseRepository, times(1)).save(courseActual);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectCourseUpdate() throws SQLException {
        Course course = new Course();
        final Long id = 1L;
        course.setCourseName("course1");
        course.setCourseId(id);
        courseServiceImpl.editCourse(course);

        Mockito.verify(courseRepository, times(1)).save(course);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectCourseDelete() throws SQLException {
        final Long id = 1L;
        courseServiceImpl.deleteCourse(id);

        Mockito.verify(courseRepository, times(1)).deleteById(1L);
    }

}
