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

import ua.com.dfyzok.lesson_9_decompose_university.db.repository.FacultyRepository;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Faculty;

@RunWith(MockitoJUnitRunner.class)
public class TestFacultyServiceImpl {
    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    FacultyServiceImpl facultyServiceImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectFacultyGetAll() throws SQLException {
        List<Faculty> facultyList = new ArrayList<Faculty>();
        List<Faculty> facultyListActual = new ArrayList<Faculty>();
        Faculty faculty = new Faculty();
        faculty.setFacultyName("faculty");
        faculty.setFacultyId(1L);
        Faculty faculty1 = new Faculty();
        faculty1.setFacultyName("faculty1");
        faculty1.setFacultyId(2L);
        facultyList.add(faculty);
        facultyList.add(faculty1);
        when(facultyServiceImpl.getAll()).thenReturn(facultyList);
        facultyListActual = facultyServiceImpl.getAll();

        assertEquals(facultyList, facultyListActual);
        Mockito.verify(facultyRepository, times(1)).findAll();
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectFacultyGetById() throws SQLException {
        Faculty faculty = new Faculty();
        Faculty facultyActual = new Faculty();
        final Long id = 1L;
        faculty.setFacultyName("faculty");
        faculty.setFacultyId(id);
        when(facultyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(faculty));
        facultyActual = facultyServiceImpl.getById(id).get();

        assertEquals(faculty, facultyActual);
        Mockito.verify(facultyRepository, times(1)).findById(1L);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectFacultyCreate() throws SQLException {
        Faculty facultyActual = new Faculty();
        final Long id = 1L;
        facultyActual.setFacultyName("faculty1");
        facultyActual.setFacultyId(id);
        facultyActual.setVersion(id);
        facultyServiceImpl.addFaculty(facultyActual);

        Mockito.verify(facultyRepository, times(1)).save(facultyActual);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectFacultyUpdate() throws SQLException {
        Faculty faculty = new Faculty();
        final Long id = 1L;
        faculty.setFacultyName("faculty1");
        faculty.setFacultyId(id);
        facultyServiceImpl.editFaculty(faculty);

        Mockito.verify(facultyRepository, times(1)).save(faculty);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectFacultyDelete() throws SQLException {
        final Long id = 1L;
        facultyServiceImpl.deleteFaculty(id);

        Mockito.verify(facultyRepository, times(1)).deleteById(1L);
    }
}
