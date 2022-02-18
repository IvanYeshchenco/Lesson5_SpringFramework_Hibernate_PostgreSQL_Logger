package ua.com.dfyzok.lesson_9_decompose_university.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Lesson;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.LessonNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.service.LessonService;

@Api(value = "Lesson")
@RestController("LessonRestController")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping("/lessons")
    List<Lesson> loadAll() {
        return lessonService.getAll();
    }

    @PostMapping("/lessons")
    Lesson addLesson(@RequestBody Lesson newLesson) {
        return lessonService.addLesson(newLesson);
    }

    @GetMapping("/lessons/{id}")
    Lesson loadOne(@PathVariable Long id) throws LessonNotFoundException {
        return lessonService.getById(id).orElseThrow(() -> new LessonNotFoundException(id));
    }

    @PutMapping("/lessons/{id}")
    Lesson replaceLesson(@RequestBody Lesson newLesson, @PathVariable Long id) {
        return lessonService.getById(id).map(lesson -> {
            lesson.setLessonName(newLesson.getLessonName());
            lesson.setTime(newLesson.getTime());
            lesson.setCourseName(newLesson.getCourseName());
            lesson.setGroup(newLesson.getGroup());
            lesson.setTeacher(newLesson.getTeacher());
            return lessonService.editLesson(lesson);
        }).orElseGet(() -> {
            newLesson.setLessonId(id);
            return lessonService.editLesson(newLesson);
        });
    }

    @DeleteMapping("/lessons/{id}")
    void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }
}
