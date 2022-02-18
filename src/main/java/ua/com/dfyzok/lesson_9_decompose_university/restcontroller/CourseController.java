package ua.com.dfyzok.lesson_9_decompose_university.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Course;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.CourseNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.service.CourseService;

@Api(value = "Course")
@RestController("CourseRestController")
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    List<Course> loadAll() {
        return courseService.getAll();
    }

    @PostMapping("/courses")
    Course addCourse(@RequestBody Course newCourse) {
        return courseService.addCourse(newCourse);
    }

    @GetMapping("/courses/{id}")
    Course loadOne(@PathVariable Long id) throws CourseNotFoundException {
        return courseService.getById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }

    @PutMapping("/courses/{id}")
    Course replaceCourse(@RequestBody Course newCourse, @PathVariable Long id) {
        return courseService.getById(id).map(course -> {
            course.setCourseName(newCourse.getCourseName());
            return courseService.editCourse(course);
        }).orElseGet(() -> {
            newCourse.setCourseId(id);
            return courseService.editCourse(newCourse);
        });
    }

    @DeleteMapping("/courses/{id}")
    void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}
