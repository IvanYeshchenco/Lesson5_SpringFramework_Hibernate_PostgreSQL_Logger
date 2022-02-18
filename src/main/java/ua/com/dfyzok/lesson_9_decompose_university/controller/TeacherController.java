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

import ua.com.dfyzok.lesson_9_decompose_university.dto.TeacherDto;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Teacher;
import ua.com.dfyzok.lesson_9_decompose_university.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private final TeacherService teacherService;

    @Autowired
    private ModelMapper modelMapper;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/list")
    public String getAllteachers(Model model) throws SQLException {
        Collection<Teacher> teacherlist = teacherService.getAll();
        model.addAttribute("list", teacherlist.stream().map(teacher -> modelMapper.map(teacher, TeacherDto.class))
                .collect(Collectors.toList()));
        return "teacher/teacherlist.html";
    }

    @GetMapping("/add")
    public String teacherFormGet(Model model) throws SQLException {
        TeacherDto teacherDto = modelMapper.map(new Teacher(), TeacherDto.class);
        model.addAttribute("teacher", teacherDto);
        return "teacher/addteacher.html";
    }

    @PostMapping("/addsave")
    public String teacherForm(@ModelAttribute("teacher") @Valid TeacherDto teacherDto, BindingResult bindingResult)
            throws SQLException {
        if (bindingResult.hasErrors())
            return "teacher/addteacher.html";
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        teacherService.addTeacher(teacher);
        return "redirect:/teacher/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) throws SQLException {
        Teacher teacher = teacherService.getById(id).get();
        TeacherDto teacherDto = modelMapper.map(teacher, TeacherDto.class);
        model.addAttribute("teacher", teacherDto);
        return "teacher/updateteacher.html";
    }

    @PostMapping("/update")
    public String editsave(@ModelAttribute("teacher") @Valid TeacherDto teacherDto, BindingResult bindingResult)
            throws SQLException {
        if (bindingResult.hasErrors())
            return "teacher/updateteacher.html";
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        teacherService.editTeacher(teacher);
        return "redirect:/teacher/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) throws SQLException {
        teacherService.deleteTeacher(id);
        return "redirect:/teacher/list";
    }

}
