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
import ua.com.dfyzok.lesson_9_decompose_university.entity.Group;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.GroupNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.service.GroupService;

@Api(value = "Group")
@RestController("GroupRestController")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/groups")
    List<Group> loadAll() {
        return groupService.getAll();
    }

    @PostMapping("/groups")
    Group addGroup(@RequestBody Group newGroup) {
        return groupService.addGroup(newGroup);
    }

    @GetMapping("/groups/{id}")
    Group loadOne(@PathVariable Long id) throws GroupNotFoundException {
        return groupService.getById(id).orElseThrow(() -> new GroupNotFoundException(id));
    }

    @PutMapping("/groups/{id}")
    Group replaceGroup(@RequestBody Group newGroup, @PathVariable Long id) {
        return groupService.getById(id).map(group -> {
            group.setGroupName(newGroup.getGroupName());
            group.setCapacity(newGroup.getCapacity());
            group.setLessons(newGroup.getLessons());
            group.setStudents(newGroup.getStudents());
            return groupService.editGroup(group);
        }).orElseGet(() -> {
            newGroup.setGroupId(id);
            return groupService.editGroup(newGroup);
        });
    }

    @DeleteMapping("/groups/{id}")
    void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

}
