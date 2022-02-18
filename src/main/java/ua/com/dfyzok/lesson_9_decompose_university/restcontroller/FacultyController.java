package ua.com.dfyzok.lesson_9_decompose_university.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Faculty;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.FacultyNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.service.FacultyService;

@Api(value = "Faculty")
@RestController("FacultyRestController")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/faculties")
    List<Faculty> loadAll() {
        return facultyService.getAll();
    }

    @PostMapping("/faculties")
    Faculty addFaculty(@RequestBody Faculty newFaculty) {
        return facultyService.addFaculty(newFaculty);
    }

    @GetMapping("/faculties/{id}")
    Faculty loadOne(@PathVariable Long id) throws FacultyNotFoundException {
        return facultyService.getById(id).orElseThrow(() -> new FacultyNotFoundException(id));
    }

    @PutMapping("/faculties/{id}")
    Faculty replaceFaculty(@RequestBody Faculty newFaculty, @PathVariable Long id) {
        return facultyService.getById(id).map(faculty -> {
            faculty.setFacultyName(newFaculty.getFacultyName());
            return facultyService.editFaculty(faculty);
        }).orElseGet(() -> {
            newFaculty.setFacultyId(id);
            return facultyService.editFaculty(newFaculty);
        });
    }

    @DeleteMapping("/faculties/{id}")
    void deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }

}
