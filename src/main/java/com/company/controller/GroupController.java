package com.company.controller;

import com.company.dtos.GroupDTO;
import com.company.dtos.UserDTO;
import com.company.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("groups")
public class GroupController {

    @Autowired
    private GroupService service;

    @GetMapping("findAll")
    private ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView("groups/findAllPage.html");
        modelAndView.addObject("users", service.findAll());
        return modelAndView;
    }

    @GetMapping("findById")
    private ModelAndView findById(@RequestParam(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("groups/findByIdPage.html");
        modelAndView.addObject("groupDTO", service.findById(id));
        return modelAndView;
    }

    @GetMapping("findByUserId")
    private ModelAndView findByGroupId(@RequestParam(value = "groupId") int groupId) {
        ModelAndView modelAndView = new ModelAndView("groups/findByUserId.html");
        modelAndView.addObject("groupDTO", service.findByUserId(groupId));
        return modelAndView;
    }

    @GetMapping("add")
    private ModelAndView add(@RequestParam(value = "userId") int userId) {
        ModelAndView modelAndView = new ModelAndView("groups/findByIdPage.html");
        modelAndView.addObject("groupDTO", service.add(userId, new GroupDTO()));
        return modelAndView;
    }

    @GetMapping("update")
    private ModelAndView update(@RequestParam(value = "groupId") int groupId) {
        ModelAndView modelAndView = new ModelAndView("groups/updatePage.html");
        modelAndView.addObject("groupDTO", service.update(groupId, new GroupDTO()));
        return modelAndView;
    }

}
