package ua.com.dfyzok.lesson_9_decompose_university.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.dfyzok.lesson_9_decompose_university.dto.LessonSlotDto;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Lesson;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.GroupNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.TeacherNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.service.TimeTableService;

@RequestMapping("/timetable")
public class TimeTableController {

    @Autowired
    private final TimeTableService timeTableService;

    public TimeTableController(TimeTableService timeTableService) {
        this.timeTableService = timeTableService;
    }

    @GetMapping("/timetablehome")
    public String timetableHome(Model model) throws SQLException {
        return "timetable/timetablehome.html";
    }

    @GetMapping("/timetablestudentform")
    public String timeTableStudentFormGet(Model model) throws SQLException {
        model.addAttribute("timetablecontainer", new LessonSlotDto());
        return "timetable/studenttimetable.html";
    }

    @GetMapping("/timetableteacherform")
    public String timeTableTeacherFormGet(Model model) throws SQLException {
        model.addAttribute("timetablecontainer", new LessonSlotDto());
        return "timetable/teachertimetable.html";
    }

    @PostMapping("/searchtimetablestudent")
    public String timeTableStudent(Model model, @ModelAttribute("timetablecontainer") LessonSlotDto timeTableContainer)
            throws SQLException, GroupNotFoundException {
        Collection<Lesson> timeTableList = new ArrayList<>();
        timeTableList = timeTableService.fetchTimeTableStudent(timeTableContainer.getTimeBegin(),
                timeTableContainer.getTimeEnd(), timeTableContainer.getUserId());
        model.addAttribute("timetable", timeTableList);
        return "timetable/timetable.html";
    }

    @PostMapping("/searchtimetableteachet")
    public String timeTableTeacher(Model model, @ModelAttribute("timetablecontainer") LessonSlotDto timeTableContainer)
            throws SQLException, TeacherNotFoundException {
        Collection<Lesson> timeTableList = new ArrayList<>();
        timeTableList = timeTableService.fetchTimeTableTeacher(timeTableContainer.getTimeBegin(),
                timeTableContainer.getTimeEnd(), timeTableContainer.getUserId());
        model.addAttribute("timetable", timeTableList);
        return "timetable/timetable.html";
    }

}
