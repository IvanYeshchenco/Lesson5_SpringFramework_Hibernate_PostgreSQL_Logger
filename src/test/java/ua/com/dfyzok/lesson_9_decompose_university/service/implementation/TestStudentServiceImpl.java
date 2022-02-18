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

import ua.com.dfyzok.lesson_9_decompose_university.db.repository.StudentRepository;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Student;

@RunWith(MockitoJUnitRunner.class)
public class TestStudentServiceImpl {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImpl studentServiceImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectStudentGetAll() throws SQLException {
        List<Student> studentList = new ArrayList<Student>();
        List<Student> studentListActual = new ArrayList<Student>();
        Student student = new Student();
        student.setName("student");
        student.setStudentId(1L);
        student.setSex("male");
        student.setAge(22);
        Student student1 = new Student();
        student1.setName("student1");
        student1.setStudentId(2L);
        student1.setSex("female");
        student1.setAge(33);
        studentList.add(student);
        studentList.add(student1);
        when(studentServiceImpl.getAll()).thenReturn(studentList);
        studentListActual = studentServiceImpl.getAll();

        assertEquals(studentList, studentListActual);
        Mockito.verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectStudentGetById() throws SQLException {
        Student student = new Student();
        Student studentActual = new Student();
        final Long id = 1L;
        student.setName("student");
        student.setStudentId(id);
        student.setSex("male");
        student.setAge(22);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        studentActual = studentServiceImpl.getById(id).get();

        assertEquals(student, studentActual);
        Mockito.verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectStudentCreate() throws SQLException {
        Student studentActual = new Student();
        final Long id = 1L;
        studentActual.setName("student1");
        studentActual.setStudentId(id);
        studentActual.setVersion(id);
        studentActual.setSex("male");
        studentActual.setAge(22);
        studentServiceImpl.addStudent(studentActual);

        Mockito.verify(studentRepository, times(1)).save(studentActual);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectStudentUpdate() throws SQLException {
        Student student = new Student();
        final Long id = 1L;
        student.setName("student1");
        student.setStudentId(id);
        student.setSex("male");
        student.setAge(22);
        studentServiceImpl.editStudent(student);

        Mockito.verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectStudentDelete() throws SQLException {
        final Long id = 1L;
        studentServiceImpl.deleteStudent(id);

        Mockito.verify(studentRepository, times(1)).deleteById(1L);
    }
}
