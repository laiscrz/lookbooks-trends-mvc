package com.leadtech.lookbooks.controller;

import com.leadtech.lookbooks.model.Lookbook;
import com.leadtech.lookbooks.service.LookbookService;
import com.leadtech.lookbooks.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/lookbooks")
public class LookbookController {

    @Autowired
    private LookbookService lookbookService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView showListLookbooks() {
        List<Lookbook> lookbooks = lookbookService.findAllLookbooks();
        ModelAndView modelAndView = new ModelAndView("lookbook/list");
        modelAndView.addObject("lookbooks", lookbooks);
        return modelAndView;
    }
}
