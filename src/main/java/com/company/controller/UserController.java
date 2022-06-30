package com.company.controller;

import com.company.dtos.UserDTO;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("userPage")
    private String getUsersPage() {
        return "users/userPage.html";
    }

    @GetMapping("findAll")
    private ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView("users/findAllPage.html");
        modelAndView.addObject("users", service.findAll());
        return modelAndView;
    }

    @GetMapping("findById")
    private ModelAndView findById(@RequestParam(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("users/findByIdPage.html");
        modelAndView.addObject("userDTO", service.findById(id));
        return modelAndView;
    }

    @GetMapping("findByUsername")
    private ModelAndView findById(@RequestParam(value = "username") String username) {
        ModelAndView modelAndView = new ModelAndView("users/findByIdPage.html");
        modelAndView.addObject("userDTO", service.findByUsername(username));
        return modelAndView;
    }

    @GetMapping("update")
    private ModelAndView update() {
        ModelAndView modelAndView = new ModelAndView("users/updatePage.html");
        modelAndView.addObject("userDTO", service.update(new UserDTO()));
        return modelAndView;
    }

    @GetMapping("add")
    private ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("users/addPage.html");
        modelAndView.addObject("userDTO", service.add(new UserDTO()));
        return modelAndView;
    }

}
