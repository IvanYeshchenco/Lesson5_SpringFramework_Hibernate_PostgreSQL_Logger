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
import ua.com.dfyzok.lesson_9_decompose_university.entity.Student;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.StudentNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.service.StudentService;

@Api(value = "Student")
@RestController("StudentRestController")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    List<Student> loadAll() {
        return studentService.getAll();
    }

    @PostMapping("/students")
    Student addStudent(@RequestBody Student newStudent) {
		return studentService.addStudent(newStudent);
    }

    @GetMapping("/students/{id}")
    Student loadOne(@PathVariable Long id) throws StudentNotFoundException {
        return studentService.getById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @PutMapping("/students/{id}")
    Student replaceStudent(@RequestBody Student newStudent, @PathVariable Long id) {
        return studentService.getById(id).map(student -> {
            student.setGroup(newStudent.getGroup());
            return studentService.editStudent(student);
        }).orElseGet(() -> {
            newStudent.setStudentId(id);
            return studentService.editStudent(newStudent);
        });
    }

    @DeleteMapping("/students/{id}")
    void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
