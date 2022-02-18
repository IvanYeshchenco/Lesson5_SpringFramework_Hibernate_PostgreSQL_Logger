package ua.com.dfyzok.lesson_9_decompose_university.service;

import java.util.List;
import java.util.Optional;

import ua.com.dfyzok.lesson_9_decompose_university.entity.Faculty;

public interface FacultyService {

    List<Faculty> getAll();

    Faculty addFaculty(Faculty faculty);

    Optional<Faculty> getById(long id);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(long id);

}
