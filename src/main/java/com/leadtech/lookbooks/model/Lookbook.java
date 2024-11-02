package com.leadtech.lookbooks.model;

import com.leadtech.lookbooks.model.enums.StyleType;
import com.leadtech.lookbooks.model.enums.TrendType;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "lookbook")
public class Lookbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome do lookbook é obrigatório")
    private String nome;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDate dataCriacao;

    @ManyToMany
    @JoinTable(
            name = "lookbook_product", // Tabela associativa para n:n
            joinColumns = @JoinColumn(name = "lookbook_id"), // pk tabela Lookbook
            inverseJoinColumns = @JoinColumn(name = "product_id") // pk tabela product
    )
    @Valid
    @Size(min = 1, message = "É necessário selecionar pelo menos um produto")
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    private StyleType estilo;

    @Enumerated(EnumType.STRING)
    private TrendType tendencia;

    @Override
    public String toString() {
        return "Lookbook{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", estilo=" + estilo +
                ", tendencia=" + tendencia +
                '}';
    }

}
