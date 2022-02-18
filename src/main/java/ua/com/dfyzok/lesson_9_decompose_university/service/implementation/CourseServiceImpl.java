package ua.com.dfyzok.lesson_9_decompose_university.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.dfyzok.lesson_9_decompose_university.db.repository.CourseRepository;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Course;
import ua.com.dfyzok.lesson_9_decompose_university.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Optional<Course> getById(long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course editCourse(Course course) {
        return courseRepository.save(course);
    }

}
