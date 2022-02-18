package ua.com.dfyzok.lesson_9_decompose_university.service;

import java.util.List;
import java.util.Optional;

import ua.com.dfyzok.lesson_9_decompose_university.entity.Group;

public interface GroupService {

    List<Group> getAll();

    Group addGroup(Group group);

    Optional<Group> getById(long id);

    Group editGroup(Group group);

    void deleteGroup(long id);
}
