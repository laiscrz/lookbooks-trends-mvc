package com.leadtech.lookbooks.model;

import com.leadtech.lookbooks.model.enums.StyleType;
import com.leadtech.lookbooks.model.enums.TrendType;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LookbookTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidLookbook() {
        Lookbook lookbook = new Lookbook();
        lookbook.setNome("Look Verão");
        lookbook.setEstilo(StyleType.CASUAL);
        lookbook.setTendencia(TrendType.VINTAGE);

        // Preenchendo todos os atributos obrigatórios do Product
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

        lookbook.setProducts(List.of(product));

        Set<ConstraintViolation<Lookbook>> violations = validator.validate(lookbook);
        assertThat(violations).isEmpty();
    }

    @Test
    void testNomeNotEmpty() {
        Lookbook lookbook = new Lookbook();
        lookbook.setNome("");

        Set<ConstraintViolation<Lookbook>> violations = validator.validate(lookbook);
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Nome do lookbook é obrigatório");
    }

    @Test
    void testAtLeastOneProduct() {
        Lookbook lookbook = new Lookbook();
        lookbook.setNome("Look Primavera");

        lookbook.setProducts(new ArrayList<>()); // Nenhum produto

        Set<ConstraintViolation<Lookbook>> violations = validator.validate(lookbook);
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("É necessário selecionar pelo menos um produto");
    }
}
