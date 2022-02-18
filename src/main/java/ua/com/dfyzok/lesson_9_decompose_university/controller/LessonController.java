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

import ua.com.dfyzok.lesson_9_decompose_university.dto.LessonDto;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Lesson;
import ua.com.dfyzok.lesson_9_decompose_university.service.LessonService;

@Controller
@RequestMapping("/lesson")
public class LessonController {

    @Autowired
    private final LessonService lessonService;

    @Autowired
    private ModelMapper modelMapper;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/list")
    public String getAllLessons(Model model) throws SQLException {
        Collection<Lesson> lessonlist = lessonService.getAll();
        model.addAttribute("list", lessonlist.stream().map(lesson -> modelMapper.map(lesson, LessonDto.class))
                .collect(Collectors.toList()));
        return "lesson/lessonlist.html";
    }

    @GetMapping("/add")
    public String lessonFormGet(Model model) throws SQLException {
        LessonDto lessonDto = modelMapper.map(new Lesson(), LessonDto.class);
        model.addAttribute("lesson", lessonDto);
        return "lesson/addlesson.html";
    }

    @PostMapping("/addsave")
    public String lessonForm(@ModelAttribute("lesson") @Valid LessonDto lessonDto, BindingResult bindingResult)
            throws SQLException {
        if (bindingResult.hasErrors())
            return "lesson/addlesson.html";
        Lesson lesson = modelMapper.map(lessonDto, Lesson.class);
        lessonService.addLesson(lesson);
        return "redirect:/lesson/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) throws SQLException {
        Lesson lesson = lessonService.getById(id).get();
        LessonDto lessonDto = modelMapper.map(lesson, LessonDto.class);
        model.addAttribute("lesson", lessonDto);
        return "lesson/updatelesson.html";
    }

    @PostMapping("/update")
    public String editsave(@ModelAttribute("lesson") @Valid LessonDto lessonDto, BindingResult bindingResult)
            throws SQLException {
        if (bindingResult.hasErrors())
            return "lesson/updatelesson.html";
        Lesson lesson = modelMapper.map(lessonDto, Lesson.class);
        lessonService.editLesson(lesson);
        return "redirect:/lesson/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) throws SQLException {
        lessonService.deleteLesson(id);
        return "redirect:/lesson/list";
    }

}
