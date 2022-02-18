package ua.com.dfyzok.lesson_9_decompose_university.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.dfyzok.lesson_9_decompose_university.db.repository.TeacherRepository;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Teacher;
import ua.com.dfyzok.lesson_9_decompose_university.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Optional<Teacher> getById(long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher editTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

}
