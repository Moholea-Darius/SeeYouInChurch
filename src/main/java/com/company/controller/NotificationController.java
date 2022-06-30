package com.company.controller;

import com.company.dtos.NotificationDTO;
import com.company.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("notifications")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @GetMapping("notificationPage")
    private String getUsersPage(){
        return "notifications/notificationPage.html";
    }

    @GetMapping("findAll")
    private ModelAndView getCurrentSupervisorsPage(){
        ModelAndView modelAndView = new ModelAndView("notifications/findAllPage.html");
        modelAndView.addObject("notifications", service.findAll());
        return modelAndView;
    }

    @GetMapping("findById")
    private ModelAndView findById(@RequestParam(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("notifications/findByIdPage.html");
        modelAndView.addObject("notificationDTO", service.findById(id));
        return modelAndView;
    }

    @GetMapping("findByUserId")
    private ModelAndView findByUserId(@RequestParam(value = "userId") int userId) {
        ModelAndView modelAndView = new ModelAndView("notifications/findByUserIdPage.html");
        modelAndView.addObject("notifications", service.findByUserId(userId));
        return modelAndView;
    }

    @GetMapping("update")
    private ModelAndView update(@RequestParam(value = "messageId") int messageId) {
        ModelAndView modelAndView = new ModelAndView("notifications/updatePage.html");
        modelAndView.addObject("notificationDTO", service.update(messageId));
        return modelAndView;
    }

    @GetMapping("add")
    private ModelAndView add(@RequestParam(value = "userId") int userId) {
        ModelAndView modelAndView = new ModelAndView("notifications/addPage.html");
        modelAndView.addObject("notificationDTO", service.add(userId, new NotificationDTO()));
        return modelAndView;
    }

}
