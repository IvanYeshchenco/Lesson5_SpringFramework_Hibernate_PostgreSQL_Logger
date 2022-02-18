package ua.com.dfyzok.lesson_9_decompose_university.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.dfyzok.lesson_9_decompose_university.db.repository.StudentRepository;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Student;
import ua.com.dfyzok.lesson_9_decompose_university.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAll() {
		return studentRepository.findAll();
	}

	@Override
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(long id) {
		studentRepository.deleteById(id);
	}

	@Override
	public Optional<Student> getById(long id) {
		return studentRepository.findById(id);
	}

	@Override
	public Student editStudent(Student student) {
		return studentRepository.save(student);
	}

}
