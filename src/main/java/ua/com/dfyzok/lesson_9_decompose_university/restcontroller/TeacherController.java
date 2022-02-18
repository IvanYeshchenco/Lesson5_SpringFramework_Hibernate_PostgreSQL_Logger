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
import ua.com.dfyzok.lesson_9_decompose_university.entity.Teacher;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.TeacherNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.service.TeacherService;

@Api(value = "Teacher")
@RestController("TeahcerRestController")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teachers")
    List<Teacher> loadAll() {
        return teacherService.getAll();
    }

    @PostMapping("/teachers")
    Teacher addTeacher(@RequestBody Teacher newTeacher) {
        return teacherService.addTeacher(newTeacher);
    }

    @GetMapping("/teachers/{id}")
    Teacher loadOne(@PathVariable Long id) throws TeacherNotFoundException {
        return teacherService.getById(id).orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @PutMapping("/teachers/{id}")
    Teacher replaceTeacher(@RequestBody Teacher newTeacher, @PathVariable Long id) {
        return teacherService.getById(id).map(teacher -> {
            teacher.setPosition(newTeacher.getPosition());
            teacher.setLessons(newTeacher.getLessons());
            return teacherService.editTeacher(teacher);
        }).orElseGet(() -> {
            newTeacher.setTeacherId(id);
            return teacherService.editTeacher(newTeacher);
        });
    }

    @DeleteMapping("/teachers/{id}")
    void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }
}
