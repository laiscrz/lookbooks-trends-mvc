package com.leadtech.lookbooks.controller;

import com.leadtech.lookbooks.model.Lookbook;
import com.leadtech.lookbooks.model.enums.StyleType;
import com.leadtech.lookbooks.model.enums.TrendType;
import com.leadtech.lookbooks.service.LookbookService;
import com.leadtech.lookbooks.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ModelAndView showLookbookDetails(@PathVariable Long id) {
        Lookbook lookbook = lookbookService.findByIdLookbook(id);
        ModelAndView modelAndView = new ModelAndView("lookbook/details");
        modelAndView.addObject("lookbook", lookbook);
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView showCreateLookbookForm() {
        ModelAndView modelAndView = new ModelAndView("lookbook/form");
        modelAndView.addObject("lookbook", new Lookbook());
        modelAndView.addObject("products", productService.findAllProducts());
        return modelAndView;
    }

    @PostMapping
    public String saveLookbook(@Valid @ModelAttribute Lookbook lookbook) {
        lookbookService.saveLookbook(lookbook);
        return "redirect:/lookbooks";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditLookbookForm(@PathVariable Long id) {
        Lookbook lookbook = lookbookService.findByIdLookbook(id);
        ModelAndView modelAndView = new ModelAndView("lookbook/form");
        modelAndView.addObject("lookbook", lookbook);
        modelAndView.addObject("products", productService.findAllProducts());
        return modelAndView;
    }

    @PostMapping("/{id}")
    public String updateLookbook(@PathVariable Long id, @ModelAttribute Lookbook lookbook) {
        lookbook.setId(id);
        lookbookService.saveLookbook(lookbook);
        return "redirect:/lookbooks";
    }


    @GetMapping("/delete/{id}")
    public ModelAndView deleteLookbook(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("lookbook/list");
        try {
            lookbookService.deleteByIdILookbook(id);
            modelAndView.addObject("message", "Lookbook excluído com sucesso.");
        } catch (RuntimeException e) {
            modelAndView.addObject("error", e.getMessage());
        }
        modelAndView.addObject("lookbooks", lookbookService.findAllLookbooks());
        return modelAndView;
    }

    @ModelAttribute("estilo")
    public StyleType[] getEstilo(){
        return StyleType.values();
    }

    @ModelAttribute("tendencia")
    public TrendType[] getTendencia(){
        return TrendType.values();
    }
}
