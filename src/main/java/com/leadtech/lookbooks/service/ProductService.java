package com.leadtech.lookbooks.service;

import com.leadtech.lookbooks.model.Product;
import com.leadtech.lookbooks.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private IProductRepository productRepository;

    // Retorna todos
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    // Busca pelo ID
    public Product findByIdProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Cria ou atualiza
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    // Remove pelo ID
    public void deleteByIdProduct(Long id) {
        Product product = findByIdProduct(id);
        if (product != null && (product.getLookbooks()) != null && !product.getLookbooks().isEmpty()) {
            throw new RuntimeException("O produto está associado a um Lookbook. Remova-o do Lookbook antes de tentar excluí-lo.");
        }
        productRepository.deleteById(id);
    }
}
