package ua.com.dfyzok.lesson_9_decompose_university.controller;

import java.sql.SQLException;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.dfyzok.lesson_9_decompose_university.dto.StudentDto;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Student;
import ua.com.dfyzok.lesson_9_decompose_university.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @Autowired
    private ModelMapper modelMapper;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public String getAllstudents(Model model) throws SQLException {
        Collection<Student> studentlist = studentService.getAll();
        model.addAttribute("list", studentlist.stream().map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList()));
        return "student/studentlist.html";
    }

    @GetMapping("/add")
    public String studentFormGet(Model model) throws SQLException {
        StudentDto studentDto = modelMapper.map(new Student(), StudentDto.class);
        model.addAttribute("student", studentDto);
        return "student/addstudent.html";
    }

    @PostMapping("/addsave")
    public String studentForm(@ModelAttribute("student") @Valid StudentDto studentDto, BindingResult bindingResult)
            throws SQLException {
        if (bindingResult.hasErrors())
            return "student/addstudent.html";
        Student student = modelMapper.map(studentDto, Student.class);
        studentService.addStudent(student);
        return "redirect:/student/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) throws SQLException {
        Student student = studentService.getById(id).get();
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        model.addAttribute("student", studentDto);
        return "student/updatestudent.html";
    }

    @PostMapping("/update")
    public String editsave(@ModelAttribute("student") @Valid StudentDto studentDto, BindingResult bindingResult)
            throws SQLException {
        if (bindingResult.hasErrors())
            return "student/updatestudent.html";
        Student student = modelMapper.map(studentDto, Student.class);
        studentService.editStudent(student);
        return "redirect:/student/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) throws SQLException {
        studentService.deleteStudent(id);
        return "redirect:/student/list";
    }

}
