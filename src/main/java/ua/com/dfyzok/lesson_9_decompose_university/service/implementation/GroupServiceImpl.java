package ua.com.dfyzok.lesson_9_decompose_university.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.dfyzok.lesson_9_decompose_university.db.repository.GroupRepository;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Group;
import ua.com.dfyzok.lesson_9_decompose_university.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroup(long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public Optional<Group> getById(long id) {
        return groupRepository.findById(id);
    }

    @Override
    public Group editGroup(Group group) {
        return groupRepository.save(group);
    }

}
