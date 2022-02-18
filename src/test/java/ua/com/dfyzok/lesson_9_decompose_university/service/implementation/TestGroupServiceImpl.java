package ua.com.dfyzok.lesson_9_decompose_university.service.implementation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ua.com.dfyzok.lesson_9_decompose_university.db.repository.GroupRepository;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Group;

@RunWith(MockitoJUnitRunner.class)
public class TestGroupServiceImpl {
    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    GroupServiceImpl groupServiceImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectGroupGetAll() throws SQLException {
        List<Group> groupList = new ArrayList<Group>();
        List<Group> groupListActual = new ArrayList<Group>();
        Group group = new Group();
        group.setGroupName("group");
        group.setGroupId(1L);
        group.setCapacity(15);
        Group group1 = new Group();
        group1.setGroupName("group1");
        group1.setGroupId(2L);
        group.setCapacity(17);
        groupList.add(group);
        groupList.add(group1);
        when(groupServiceImpl.getAll()).thenReturn(groupList);
        groupListActual = groupServiceImpl.getAll();

        assertEquals(groupList, groupListActual);
        Mockito.verify(groupRepository, times(1)).findAll();
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectGroupGetById() throws SQLException {
        Group group = new Group();
        Group groupActual = new Group();
        final Long id = 1L;
        group.setGroupName("group");
        group.setGroupId(id);
        group.setCapacity(17);
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        groupActual = groupServiceImpl.getById(id).get();

        assertEquals(group, groupActual);
        Mockito.verify(groupRepository, times(1)).findById(1L);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectGroupCreate() throws SQLException {
        Group groupActual = new Group();
        final Long id = 1L;
        groupActual.setGroupName("group1");
        groupActual.setGroupId(id);
        groupActual.setVersion(id);
        groupActual.setCapacity(17);
        groupServiceImpl.addGroup(groupActual);

        Mockito.verify(groupRepository, times(1)).save(groupActual);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectGroupUpdate() throws SQLException {
        Group group = new Group();
        final Long id = 1L;
        group.setGroupName("group1");
        group.setGroupId(id);
        group.setCapacity(17);
        groupServiceImpl.editGroup(group);

        Mockito.verify(groupRepository, times(1)).save(group);
    }

    @Test
    public void whenMockJdbcTemplate_thenCorrectGroupDelete() throws SQLException {
        final Long id = 1L;
        groupServiceImpl.deleteGroup(id);

        Mockito.verify(groupRepository, times(1)).deleteById(1L);
    }

}
