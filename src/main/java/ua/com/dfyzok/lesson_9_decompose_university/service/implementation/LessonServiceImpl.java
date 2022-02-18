package ua.com.dfyzok.lesson_9_decompose_university.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.dfyzok.lesson_9_decompose_university.db.repository.LessonRepository;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Lesson;
import ua.com.dfyzok.lesson_9_decompose_university.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson addLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public void deleteLesson(long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public Optional<Lesson> getById(long id) {
        return lessonRepository.findById(id);
    }

    @Override
    public Lesson editLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

}
