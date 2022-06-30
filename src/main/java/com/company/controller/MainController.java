package com.company.controller;

import com.company.service.GroupService;
import com.company.service.PrivateEventService;
import com.company.service.PublicEventService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    private GroupService groupService;

    private PrivateEventService privateEventService;

    private PublicEventService publicEventService;

    @GetMapping("/users")
    public ModelAndView setUsersView() {
        ModelAndView modelAndView = new ModelAndView("users.html");
        modelAndView.addObject("users", userService.findAll());
        return modelAndView;
    }

    @GetMapping("/groups")
    public ModelAndView setGroupsView() {
        ModelAndView modelAndView = new ModelAndView("groups.html");
       // modelAndView.addObject("groups", groupService.findAll());
        return modelAndView;
    }

    @GetMapping("/privateEvent")
    public ModelAndView setPrivateEventView() {
        ModelAndView modelAndView = new ModelAndView("privateEvent.html");
        //modelAndView.addObject("privateEvents", privateEventService.findAll());
        return modelAndView;
    }

    @GetMapping("/publicEvent")
    public ModelAndView setPublicEventView() {
        ModelAndView modelAndView = new ModelAndView("publicEvent.html");
       // modelAndView.addObject("publicEvents", privateEventService.findAll());
        return modelAndView;
    }



}
