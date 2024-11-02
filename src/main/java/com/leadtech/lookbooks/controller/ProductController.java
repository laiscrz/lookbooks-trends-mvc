package com.leadtech.lookbooks.controller;

import com.leadtech.lookbooks.model.Product;
import com.leadtech.lookbooks.model.enums.CategoryType;
import com.leadtech.lookbooks.model.enums.DesignType;
import com.leadtech.lookbooks.model.enums.SeasonalityType;
import com.leadtech.lookbooks.model.enums.SizeType;
import com.leadtech.lookbooks.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView showListProducts() {
        List<Product> products = productService.findAllProducts();
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView showProductDetails(@PathVariable Long id) {
        Product product = productService.findByIdProduct(id);
        ModelAndView modelAndView = new ModelAndView("product/details");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView showCreateProductForm() {
        ModelAndView modelAndView = new ModelAndView("product/form");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProductForm(@PathVariable Long id) {
        Product product = productService.findByIdProduct(id);
        ModelAndView modelAndView = new ModelAndView("product/form");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("product/list");
        try {
            productService.deleteByIdProduct(id);

            modelAndView.addObject("message", "Produto excluído com sucesso.");
        } catch (RuntimeException e) {
            modelAndView.addObject("error", e.getMessage());
        }
        modelAndView.addObject("products", productService.findAllProducts());
        return modelAndView;
    }

    @ModelAttribute("categoria")
    public CategoryType[] getCategoria(){
        return CategoryType.values();
    }

    @ModelAttribute("tamanho")
    public SizeType[] getTamanho(){
        return SizeType.values();
    }

    @ModelAttribute("design")
    public DesignType[] getDesign(){
        return DesignType.values();
    }


    @ModelAttribute("sazonalidade")
    public SeasonalityType[] getSazonalidade(){
        return SeasonalityType.values();
    }
}
