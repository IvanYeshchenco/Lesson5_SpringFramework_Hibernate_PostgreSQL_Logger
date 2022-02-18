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

import ua.com.dfyzok.lesson_9_decompose_university.db.repository.TeacherRepository;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Teacher;

@RunWith(MockitoJUnitRunner.class)
public class TestTeacherServiceImpl {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    TeacherServiceImpl teacherServiceImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectTeacherGetAll() throws SQLException {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        List<Teacher> teacherListActual = new ArrayList<Teacher>();
        Teacher teacher = new Teacher();
        teacher.setName("teacher");
        teacher.setTeacherId(1L);
        teacher.setSex("male");
        teacher.setAge(22);
        Teacher teacher1 = new Teacher();
        teacher1.setName("teacher1");
        teacher1.setTeacherId(2L);
        teacher1.setSex("female");
        teacher1.setAge(33);
        teacherList.add(teacher);
        teacherList.add(teacher1);
        when(teacherServiceImpl.getAll()).thenReturn(teacherList);
        teacherListActual = teacherServiceImpl.getAll();

        assertEquals(teacherList, teacherListActual);
        Mockito.verify(teacherRepository, times(1)).findAll();
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectTeacherGetById() throws SQLException {
        Teacher teacher = new Teacher();
        Teacher teacherActual = new Teacher();
        final Long id = 1L;
        teacher.setName("teacher");
        teacher.setTeacherId(id);
        teacher.setSex("male");
        teacher.setAge(22);
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        teacherActual = teacherServiceImpl.getById(id).get();

        assertEquals(teacher, teacherActual);
        Mockito.verify(teacherRepository, times(1)).findById(1L);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectTeacherCreate() throws SQLException {
        Teacher teacherActual = new Teacher();
        final Long id = 1L;
        teacherActual.setName("teacher1");
        teacherActual.setTeacherId(id);
        teacherActual.setVersion(id);
        teacherActual.setSex("male");
        teacherActual.setAge(22);
        teacherServiceImpl.addTeacher(teacherActual);

        Mockito.verify(teacherRepository, times(1)).save(teacherActual);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectTeacherUpdate() throws SQLException {
        Teacher teacher = new Teacher();
        final Long id = 1L;
        teacher.setName("teacher1");
        teacher.setTeacherId(id);
        teacher.setSex("male");
        teacher.setAge(22);
        teacherServiceImpl.editTeacher(teacher);

        Mockito.verify(teacherRepository, times(1)).save(teacher);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectTeacherDelete() throws SQLException {
        final Long id = 1L;
        teacherServiceImpl.deleteTeacher(id);

        Mockito.verify(teacherRepository, times(1)).deleteById(1L);
    }
}
