package com.leadtech.lookbooks.model;

import com.leadtech.lookbooks.model.enums.CategoryType;
import com.leadtech.lookbooks.model.enums.DesignType;
import com.leadtech.lookbooks.model.enums.SeasonalityType;
import com.leadtech.lookbooks.model.enums.SizeType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidProduct() {
        Product product = new Product();
        product.setNome("Camisa");
        product.setCategoria(CategoryType.CAMISETA);
        product.setCor("Azul");
        product.setTamanho(SizeType.M);
        product.setMaterial("Algodão");
        product.setMarca("MarcaX");
        product.setPreco(99.99);
        product.setDesign(DesignType.LISO);
        product.setSazonalidade(SeasonalityType.VERAO);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertThat(violations).isEmpty();
    }

    @Test
    void testNomeNotEmpty() {
        Product product = new Product();
        product.setNome("");
        product.setCategoria(CategoryType.CAMISETA);
        product.setCor("Azul");
        product.setTamanho(SizeType.M);
        product.setMaterial("Algodão");
        product.setMarca("MarcaX");
        product.setPreco(99.99);
        product.setDesign(DesignType.LISO);
        product.setSazonalidade(SeasonalityType.VERAO);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Nome é obrigatório");
    }

    @Test
    void testCategoriaNotNull() {
        Product product = new Product();
        product.setNome("Camisa");
        product.setCor("Azul");
        product.setTamanho(SizeType.M);
        product.setMaterial("Algodão");
        product.setMarca("MarcaX");
        product.setPreco(99.99);
        product.setDesign(DesignType.LISO);
        product.setSazonalidade(SeasonalityType.VERAO);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Categoria é obrigatória");
    }

    @Test
    void testCorNotEmpty() {
        Product product = new Product();
        product.setNome("Camisa");
        product.setCategoria(CategoryType.CAMISETA);
        product.setCor("");
        product.setTamanho(SizeType.M);
        product.setMaterial("Algodão");
        product.setMarca("MarcaX");
        product.setPreco(99.99);
        product.setDesign(DesignType.LISO);
        product.setSazonalidade(SeasonalityType.VERAO);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Cor é obrigatória");
    }

    @Test
    void testMaterialNotBlank() {
        Product product = new Product();
        product.setNome("Camisa");
        product.setCategoria(CategoryType.CAMISETA);
        product.setCor("Azul");
        product.setTamanho(SizeType.M);
        product.setMaterial("");
        product.setMarca("MarcaX");
        product.setPreco(99.99);
        product.setDesign(DesignType.LISO);
        product.setSazonalidade(SeasonalityType.VERAO);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Material é obrigatório");
    }

    @Test
    void testMarcaNotBlank() {
        Product product = new Product();
        product.setNome("Camisa");
        product.setCategoria(CategoryType.CAMISETA);
        product.setCor("Azul");
        product.setTamanho(SizeType.M);
        product.setMaterial("Algodão");
        product.setMarca("");
        product.setPreco(99.99);
        product.setDesign(DesignType.LISO);
        product.setSazonalidade(SeasonalityType.VERAO);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Marca é obrigatória");
    }

    @Test
    void testPrecoPositive() {
        Product product = new Product();
        product.setNome("Camisa");
        product.setCategoria(CategoryType.CAMISETA);
        product.setCor("Azul");
        product.setTamanho(SizeType.M);
        product.setMaterial("Algodão");
        product.setMarca("MarcaX");
        product.setPreco(-10.0);
        product.setDesign(DesignType.LISO);
        product.setSazonalidade(SeasonalityType.VERAO);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("O preço deve ser um valor positivo");
    }
}
