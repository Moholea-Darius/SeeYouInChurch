package com.company.controller;

import com.company.dtos.AnnouncementDTO;
import com.company.dtos.GroupDTO;
import com.company.dtos.NotificationDTO;
import com.company.dtos.UserDTO;
import com.company.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

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
            UserDTO userDTO = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            modelAndView.addObject("unreadNotifications", notificationService.getUserUnreadNotifications(userDTO.getId()).size());
            modelAndView.addObject("announcements", announcementService.getLastThreeAnnouncements());
        } catch (WebClientResponseException e) {
            modelAndView.addObject("noAnnouncements", "There are no announcements");
        }
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView getUsersView() {
        ModelAndView modelAndView = new ModelAndView("users.html");
        UserDTO userDTO = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.addObject("unreadNotifications", notificationService.getUserUnreadNotifications(userDTO.getId()).size());
        modelAndView.addObject("users", userService.findAll());
        return modelAndView;
    }

    @GetMapping("/eventList")
    public ModelAndView getEventListView() {
        ModelAndView modelAndView = new ModelAndView("eventList.html");
        UserDTO userDTO = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        try {
            modelAndView.addObject("unreadNotifications", notificationService.getUserUnreadNotifications(userDTO.getId()).size());
            modelAndView.addObject("publicEvents", publicEventService.findAll());
            modelAndView.addObject("privateEvents", privateEventService.findAll());

        } catch (WebClientResponseException e) {
            modelAndView.addObject("noPublicEvents", "There are no public events");
            modelAndView.addObject("noPrivateEvents", "There are no private events");

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
    public ModelAndView getNotificationPage() {
        UserDTO userDTO = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<NotificationDTO> dtos = notificationService.getUserUnreadNotifications(userDTO.getId());
        ModelAndView modelAndView = new ModelAndView("notifications.html");
        modelAndView.addObject("unreadNotifications", dtos.size());
        List<NotificationDTO> notificationDTOS = notificationService.findByUserId(userDTO.getId());
        Collections.reverse(notificationDTOS);
        modelAndView.addObject("notifications", notificationDTOS);
        notificationService.updateMessages(dtos);
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

    @GetMapping("/addAnnouncement")
    public ModelAndView getAnnouncementPage(AnnouncementDTO announcementDTO) {
        UserDTO userDTO = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView modelAndView = new ModelAndView("addAnnouncement.html");
        modelAndView.addObject("unreadNotifications", notificationService.getUserUnreadNotifications(userDTO.getId()).size());
        return modelAndView;
    }

    @PostMapping("/addAnnouncement")
    public ModelAndView addAnnouncement(AnnouncementDTO announcementDTO) {
        ModelAndView modelAndView = new ModelAndView("home.html");
        UserDTO userDTO = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.addObject("unreadNotifications", notificationService.getUserUnreadNotifications(userDTO.getId()).size());
        try {
            announcementService.add(announcementDTO, userDTO.getName() + " " + userDTO.getSurname());
            modelAndView.addObject("announcements", announcementService.getLastThreeAnnouncements());
        } catch (WebClientResponseException e) {
            modelAndView.addObject("noAnnouncements", "There are no announcements");
        }
        return modelAndView;
    }

    @GetMapping("/myGroups")
    public ModelAndView getMyGroupsPage(GroupDTO groupDTO) {
        UserDTO userDTO = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView modelAndView = new ModelAndView("myGroups.html");
        modelAndView.addObject("unreadNotifications", notificationService.getUserUnreadNotifications(userDTO.getId()).size());
        modelAndView.addObject("groups", groupService.findByUserId(userDTO.getId()));
        return modelAndView;
    }

    private int groupId;

    @GetMapping("/aboutGroup")
    public ModelAndView getAboutGroupPage(GroupDTO groupDTO, @RequestParam("groupId") int groupId) {
        this.groupId = groupId;
        ModelAndView modelAndView = new ModelAndView("aboutGroup.html");
        GroupDTO groupDTO1 = groupService.findById(groupId);
        List<UserDTO> userDTOS = groupService.findUsersByGroupId(userService.findAll(), groupId);
        UserDTO userDTO = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.addObject("unreadNotifications", notificationService.getUserUnreadNotifications(userDTO.getId()).size());
        modelAndView.addObject("groupName", groupDTO1.getName());
        modelAndView.addObject("details", groupDTO1.getDescription());
        modelAndView.addObject("totalMembers", "Total number of this groups' members is " + userDTOS.size());
        modelAndView.addObject("users", groupService.findUsersByGroupId(userService.findAll(), groupId));
        return modelAndView;
    }

    @PostMapping("/aboutGroup")
    public ModelAndView aboutGroup(GroupDTO groupDTO) {
        groupDTO.setId(groupId);
        ModelAndView modelAndView = new ModelAndView("myGroups.html");
        groupService.update(groupDTO, userService.findAll());
        UserDTO userDTO = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.addObject("unreadNotifications", notificationService.getUserUnreadNotifications(userDTO.getId()).size());
        modelAndView.addObject("groups", groupService.findByUserId(userDTO.getId()));
        return modelAndView;
    }

    @GetMapping("/joinGroups")
    public ModelAndView getJoinGroupsPage(GroupDTO groupDTO) {
        UserDTO userDTO = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView modelAndView = new ModelAndView("joinGroups.html");
        modelAndView.addObject("unreadNotifications", notificationService.getUserUnreadNotifications(userDTO.getId()).size());
        modelAndView.addObject("groups", groupService.findGroups(userDTO.getId()));
        return modelAndView;
    }

    @GetMapping("/addGroups")
    public ModelAndView getAddGroupPage(GroupDTO groupDTO) {
        UserDTO userDTO = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView modelAndView = new ModelAndView("addGroups.html");
        modelAndView.addObject("unreadNotifications", notificationService.getUserUnreadNotifications(userDTO.getId()).size());
        return modelAndView;
    }

    @PostMapping("/addGroups")
    public ModelAndView addGroup(GroupDTO groupDTO) {
        UserDTO userDTO = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(groupDTO.toString());
        ModelAndView modelAndView = new ModelAndView("joinGroups.html");
        groupService.add(userDTO.getId(), groupDTO);
        modelAndView.addObject("unreadNotifications", notificationService.getUserUnreadNotifications(userDTO.getId()).size());
        modelAndView.addObject("groups", groupService.findGroups(userDTO.getId()));
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        UserDTO userDTO = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView modelAndView = new ModelAndView("/index.html");
        SecurityContextHolder.getContext().setAuthentication(null);
        return modelAndView;
    }


}
