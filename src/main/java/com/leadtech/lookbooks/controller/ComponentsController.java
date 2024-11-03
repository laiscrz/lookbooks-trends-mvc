package com.leadtech.lookbooks.controller;

import com.leadtech.lookbooks.model.Lookbook;
import com.leadtech.lookbooks.model.Product;
import com.leadtech.lookbooks.service.LookbookService;
import com.leadtech.lookbooks.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ComponentsController {

    @Autowired
    private LookbookService lookbookService;

    @Autowired
    private ProductService productService;

    @GetMapping("/explorar")
    public ModelAndView showViewExplore() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("explore");
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("query") String query) {
        ModelAndView modelAndView = new ModelAndView("search");

        // Busca Lookbooks e Produtos
        List<Lookbook> lookbookResults = lookbookService.findAllLookbooks();
        List<Product> productResults = productService.findAllProducts();

        // Filtrar resultados de Lookbooks e Produtos
        List<Lookbook> filteredLookbooks = lookbookResults.stream()
                .filter(lookbook -> lookbook.getNome().toLowerCase().contains(query.toLowerCase()))
                .toList();
        List<Product> filteredProducts = productResults.stream()
                .filter(product -> product.getNome().toLowerCase().contains(query.toLowerCase()))
                .toList();

        // Destacar as palavras na exibição
        String highlightedQuery = "<mark>" + query + "</mark>";

        // Criar listas de resultados com a palavra destacada usando Map
        List<Map<String, Object>> highlightedLookbooks = new ArrayList<>();
        for (Lookbook lookbook : filteredLookbooks) {
            Map<String, Object> lookbookMap = new HashMap<>();
            lookbookMap.put("nome", lookbook.getNome().replaceAll("(?i)(" + query + ")", highlightedQuery));
            lookbookMap.put("id", lookbook.getId());
            highlightedLookbooks.add(lookbookMap);
        }

        List<Map<String, Object>> highlightedProducts = new ArrayList<>();
        for (Product product : filteredProducts) {
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("nome", product.getNome().replaceAll("(?i)(" + query + ")", highlightedQuery));
            productMap.put("id", product.getId());
            highlightedProducts.add(productMap);
        }

        modelAndView.addObject("lookbookResults", highlightedLookbooks);
        modelAndView.addObject("productResults", highlightedProducts);
        modelAndView.addObject("query", query);

        return modelAndView;
    }



}
