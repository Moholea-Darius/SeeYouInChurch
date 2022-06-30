package com.company.controller;

import com.company.dtos.GroupDTO;
import com.company.dtos.PrivateEventDTO;
import com.company.service.PrivateEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("privateEvents")
public class PrivateEventController {

    @Autowired
    private PrivateEventService service;

    @GetMapping("findAll")
    private ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView("privateEvents/findAllPage.html");
        modelAndView.addObject("privateEvents", service.findAll());
        return modelAndView;
    }

    @GetMapping("findById")
    private ModelAndView findById(@RequestParam(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("privateEvents/findByIdPage.html");
        modelAndView.addObject("privateEventDTO", service.findById(id));
        return modelAndView;
    }

    @GetMapping("findByGroupId")
    private ModelAndView findByGroupId(@RequestParam(value = "groupId") int groupId) {
        ModelAndView modelAndView = new ModelAndView("privateEvents/findByIdPage.html");
        modelAndView.addObject("privateEvents", service.findByGroupId(groupId));
        return modelAndView;
    }

    @GetMapping("add")
    private ModelAndView add(@RequestParam(value = "userId") int userId) {
        ModelAndView modelAndView = new ModelAndView("privateEvents/findByIdPage.html");
        modelAndView.addObject("privateEventDTO", service.add(userId, new PrivateEventDTO()));
        return modelAndView;
    }

    @GetMapping("update")
    private ModelAndView update(@RequestParam(value = "groupId") int groupId) {
        ModelAndView modelAndView = new ModelAndView("privateEvents/updatePage.html");
        modelAndView.addObject("privateEventDTO", service.update(groupId, new PrivateEventDTO()));
        return modelAndView;
    }




}
