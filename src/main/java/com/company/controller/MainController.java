package com.company.controller;

import com.company.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private PrivateEventService privateEventService;

    @Autowired
    private PublicEventService publicEventService;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/home")
    public ModelAndView setHomeView() {
        ModelAndView modelAndView = new ModelAndView("home.html");
        modelAndView.addObject("announcements", announcementService.getLastThreeAnnouncements());
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView setUsersView() {
        ModelAndView modelAndView = new ModelAndView("users.html");
        modelAndView.addObject("users", userService.findAll());
        return modelAndView;
    }

    @GetMapping("/groups")
    public ModelAndView setGroupsView() {
        ModelAndView modelAndView = new ModelAndView("groups.html");
        modelAndView.addObject("groups", groupService.findByUserId(1));
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

    @GetMapping("/notifications")
    public ModelAndView setNotificationView() {
        ModelAndView modelAndView = new ModelAndView("notifications.html");
        modelAndView.addObject("notifications", notificationService.findByUserId(1));
        return modelAndView;
    }


}
