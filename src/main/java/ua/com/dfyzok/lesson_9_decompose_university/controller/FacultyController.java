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

import ua.com.dfyzok.lesson_9_decompose_university.dto.FacultyDto;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Faculty;
import ua.com.dfyzok.lesson_9_decompose_university.service.FacultyService;

@Controller
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private final FacultyService facultyService;

    @Autowired
    private ModelMapper modelMapper;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/list")
    public String getAllFaculties(Model model) throws SQLException {
        Collection<Faculty> facultylist = facultyService.getAll();
        model.addAttribute("list", facultylist.stream().map(faculty -> modelMapper.map(faculty, FacultyDto.class))
                .collect(Collectors.toList()));
        return "faculty/facultylist.html";
    }

    @GetMapping("/add")
    public String facultyFormGet(Model model) throws SQLException {
        FacultyDto facultyDto = modelMapper.map(new Faculty(), FacultyDto.class);
        model.addAttribute("faculty", facultyDto);
        return "faculty/addfaculty.html";
    }

    @PostMapping("/addsave")
    public String facultyForm(@ModelAttribute("faculty") @Valid FacultyDto facultyDto, BindingResult bindingResult)
            throws SQLException {
        if (bindingResult.hasErrors())
            return "faculty/addfaculty.html";
        Faculty faculty = modelMapper.map(facultyDto, Faculty.class);
        facultyService.addFaculty(faculty);
        return "redirect:/faculty/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) throws SQLException {
        Faculty faculty = facultyService.getById(id).get();
        FacultyDto facultyDto = modelMapper.map(faculty, FacultyDto.class);
        model.addAttribute("faculty", facultyDto);
        return "faculty/updatefaculty.html";
    }

    @PostMapping("/update")
    public String editsave(@ModelAttribute("faculty") @Valid FacultyDto facultyDto, BindingResult bindingResult)
            throws SQLException {
        if (bindingResult.hasErrors())
            return "faculty/updatefaculty.html";
        Faculty faculty = modelMapper.map(facultyDto, Faculty.class);
        facultyService.editFaculty(faculty);
        return "redirect:/faculty/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) throws SQLException {
        facultyService.deleteFaculty(id);
        return "redirect:/faculty/list";
    }

}
