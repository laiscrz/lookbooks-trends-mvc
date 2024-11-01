package com.leadtech.lookbooks.model;

import com.leadtech.lookbooks.model.enums.CategoryType;
import com.leadtech.lookbooks.model.enums.DesignType;
import com.leadtech.lookbooks.model.enums.SeasonalityType;
import com.leadtech.lookbooks.model.enums.SizeType;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Categoria é obrigatória")
    @Enumerated(EnumType.STRING)
    private CategoryType categoria;

    @NotEmpty(message = "Cor é obrigatória")
    private String cor;

    @NotNull(message = "Tamanho é obrigatório")
    @Enumerated(EnumType.STRING)
    private SizeType tamanho;

    @Column(name = "imagem_url")
    private String imagemURL;

    @NotBlank(message = "Material é obrigatório")
    private String material;

    @NotBlank(message = "Marca é obrigatória")
    private String marca;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "O preço deve ser um valor positivo")
    private Double preco;

    @NotNull(message = "Design da roupa é obrigatório")
    @Enumerated(EnumType.STRING)
    private DesignType design;

    @CreationTimestamp
    @Column(name = "data_cadastro", updatable = false)
    private LocalDate dataCadrasto;

    @NotNull(message = "Sazonalidade é obrigatória")
    @Enumerated(EnumType.STRING)
    private SeasonalityType sazonalidade;

    @ManyToMany(mappedBy = "products")
    @Valid
    private List<Lookbook> lookbooks;

}
