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

import ua.com.dfyzok.lesson_9_decompose_university.dto.GroupDto;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Group;
import ua.com.dfyzok.lesson_9_decompose_university.service.GroupService;

@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private final GroupService groupService;

    @Autowired
    private ModelMapper modelMapper;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/list")
    public String getAllGroup(Model model) throws SQLException {
        Collection<Group> grouplist = groupService.getAll();
        model.addAttribute("list",
                grouplist.stream().map(group -> modelMapper.map(group, GroupDto.class)).collect(Collectors.toList()));
        return "group/grouplist.html";
    }

    @GetMapping("/add")
    public String groupFormGet(Model model) throws SQLException {
        GroupDto groupDto = modelMapper.map(new Group(), GroupDto.class);
        model.addAttribute("group", groupDto);
        return "group/addgroup.html";
    }

    @PostMapping("/addsave")
    public String groupForm(@ModelAttribute("group") @Valid GroupDto groupDto, BindingResult bindingResult)
            throws SQLException {
        if (bindingResult.hasErrors())
            return "group/addgroup.html";
        Group group = modelMapper.map(groupDto, Group.class);
        groupService.addGroup(group);
        return "redirect:/group/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) throws SQLException {
        Group group = groupService.getById(id).get();
        GroupDto groupDto = modelMapper.map(group, GroupDto.class);
        model.addAttribute("group", groupDto);
        return "group/updategroup.html";
    }

    @PostMapping("/update")
    public String editsave(@ModelAttribute("group") @Valid GroupDto groupDto, BindingResult bindingResult)
            throws SQLException {
        if (bindingResult.hasErrors())
            return "group/updategroup.html";
        Group group = modelMapper.map(groupDto, Group.class);
        groupService.editGroup(group);
        return "redirect:/group/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) throws SQLException {
        groupService.deleteGroup(id);
        return "redirect:/group/list";
    }

}
