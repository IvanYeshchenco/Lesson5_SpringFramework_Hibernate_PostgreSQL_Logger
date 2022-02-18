package ua.com.dfyzok.lesson_9_decompose_university.service;

import java.util.List;
import java.util.Optional;

import ua.com.dfyzok.lesson_9_decompose_university.entity.Lesson;

public interface LessonService {

    List<Lesson> getAll();

    Lesson addLesson(Lesson lesson);

    void deleteLesson(long id);

    Optional<Lesson> getById(long id);

    Lesson editLesson(Lesson lesson);

}
