package com.leadtech.lookbooks.service;

import com.leadtech.lookbooks.model.Product;
import com.leadtech.lookbooks.model.Lookbook;
import com.leadtech.lookbooks.repository.IProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private IProductRepository productRepository;

    @Test
    void testFindAllProducts_Success() {
        // Arrange
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> result = productService.findAllProducts();

        // Assert
        assertEquals(1, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdProduct_Success() {
        // Arrange
        Product product = new Product();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        // Act
        Product result = productService.findByIdProduct(1L);

        // Assert
        assertNotNull(result);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdProduct_Failure() {
        // Arrange
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        Product result = productService.findByIdProduct(1L);

        // Assert
        assertNull(result);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveProduct_Success() {
        // Arrange
        Product product = new Product();

        // Act
        productService.saveProduct(product);

        // Assert
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testDeleteByIdProduct_Success() {
        // Arrange
        Product product = new Product();
        product.setLookbooks(new ArrayList<>());
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        // Act
        productService.deleteByIdProduct(1L);

        // Assert
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteByIdProduct_Failure() {
        // Arrange
        Product product = new Product();
        List<Lookbook> lookbooks = new ArrayList<>();
        lookbooks.add(new Lookbook());
        product.setLookbooks(lookbooks);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.deleteByIdProduct(1L);
        });

        assertEquals("O produto está associado a um Lookbook. Remova-o do Lookbook antes de tentar excluí-lo.", exception.getMessage());
        verify(productRepository, never()).deleteById(anyLong());
    }
}
