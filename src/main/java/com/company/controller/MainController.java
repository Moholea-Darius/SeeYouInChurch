package com.company.controller;

import com.company.dtos.UserDTO;
import com.company.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

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
    public ModelAndView getHomeView() {
        ModelAndView modelAndView = new ModelAndView("home.html");
        try {
            modelAndView.addObject("announcements", announcementService.getLastThreeAnnouncements());
        } catch (WebClientResponseException e) {
            modelAndView.addObject("noAnnouncements", "There are no announcements");
        }
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView getUsersView() {
        ModelAndView modelAndView = new ModelAndView("users.html");
            modelAndView.addObject("users", userService.findAll());
        return modelAndView;
    }

    @GetMapping("/groups")
    public ModelAndView getGroupsView() {
        ModelAndView modelAndView = new ModelAndView("groups.html   DEPRACTEDDDDDD");
        try {
            modelAndView.addObject("groups", groupService.findAll());
        } catch (WebClientResponseException e) {
            modelAndView.addObject("noGroups", "Join a group to see them");
        }
        return modelAndView;
    }

    @GetMapping("/privateEvent")
    public ModelAndView getPrivateEventView() {
        ModelAndView modelAndView = new ModelAndView("privateEvent.html");
        modelAndView.addObject("privateEvents", privateEventService.findAll());
        return modelAndView;
    }

    @GetMapping("/publicEvent")
    public ModelAndView getPublicEventView() {
        ModelAndView modelAndView = new ModelAndView("publicEvent.html");
        modelAndView.addObject("publicEvents", privateEventService.findAll());
        return modelAndView;
    }

    @GetMapping("/notifications")
    public ModelAndView getNotificationView() {
        UserDTO userDTO = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView modelAndView = new ModelAndView("notifications.html");
        modelAndView.addObject("notifications", notificationService.findByUserId(userDTO.getId()));
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage(UserDTO userDTO) {
        return new ModelAndView("register.html");
    }

    @PostMapping("/register")
    public ModelAndView addUser(UserDTO userDTO) {
        try {
            System.out.println(userDTO.toString());
            userService.add(userDTO);
            return new ModelAndView("login.html");
        } catch (WebClientResponseException e) {
            return new ModelAndView("register.html");
        }
    }



}
