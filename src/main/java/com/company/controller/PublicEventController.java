package com.company.controller;

import com.company.dtos.PublicEventDTO;
import com.company.dtos.UserDTO;
import com.company.service.PublicEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("publicEvents")
public class PublicEventController {

    @Autowired
    private PublicEventService service;

    @GetMapping("findAll")
    private ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView("publicEvents/findAllPage.html");
        modelAndView.addObject("publicEvents", service.findAll());
        return modelAndView;
    }

    @GetMapping("findById")
    private ModelAndView findById(@RequestParam(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("publicEvents/findByIdPage.html");
        modelAndView.addObject("publicEventDTO", service.findById(id));
        return modelAndView;
    }

    @GetMapping("findByGroupId")
    private ModelAndView findByGroupId(@RequestParam(value = "groupId") int groupId) {
        ModelAndView modelAndView = new ModelAndView("publicEvents/findByIdPage.html");
        modelAndView.addObject("publicEventDTO", service.findByGroupId(groupId));
        return modelAndView;
    }

    @GetMapping("add")
    private ModelAndView add(@RequestParam(value = "groupId") int groupId) {
        ModelAndView modelAndView = new ModelAndView("publicEvents/addPage.html");
        modelAndView.addObject("publicEventDTO", service.add(groupId, new PublicEventDTO()));
        return modelAndView;
    }



}
