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

import ua.com.dfyzok.lesson_9_decompose_university.dto.CourseDto;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Course;
import ua.com.dfyzok.lesson_9_decompose_university.service.CourseService;

@RequestMapping("/course")
@Controller
public class CourseController {

    @Autowired
    private final CourseService courseService;

    @Autowired
    private ModelMapper modelMapper;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/list")
    public String getAllCourses(Model model) throws SQLException {
        Collection<Course> courselist = courseService.getAll();
        model.addAttribute("list", courselist.stream().map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList()));
        return "course/courselist.html";
    }

    @GetMapping("/add")
    public String courseFormGet(Model model) throws SQLException {
        CourseDto courseDto = modelMapper.map(new Course(), CourseDto.class);
        model.addAttribute("course", courseDto);
        return "course/addcourse.html";
    }

    @PostMapping("/addsave")
    public String courseForm(@ModelAttribute("course") @Valid CourseDto courseDto, BindingResult bindingResult)
            throws SQLException {
        if (bindingResult.hasErrors())
            return "course/addcourse.html";
        Course course = modelMapper.map(courseDto, Course.class);
        courseService.addCourse(course);
        return "redirect:/course/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) throws SQLException {
        Course course = courseService.getById(id).get();
        CourseDto courseDto = modelMapper.map(course, CourseDto.class);
        model.addAttribute("course", courseDto);
        return "course/updatecourse.html";
    }

    @PostMapping("/update")
    public String editsave(@ModelAttribute("course") @Valid CourseDto courseDto, BindingResult bindingResult)
            throws SQLException {
        if (bindingResult.hasErrors())
            return "course/updatecourse.html";
        Course course = modelMapper.map(courseDto, Course.class);
        courseService.editCourse(course);
        return "redirect:/course/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) throws SQLException {
        courseService.deleteCourse(id);
        return "redirect:/course/list";
    }

}
