package com.leadtech.lookbooks.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ComponentsController {



    @GetMapping("/explorar")
    public ModelAndView showViewExplore() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("explore");
        return modelAndView;
    }


}
