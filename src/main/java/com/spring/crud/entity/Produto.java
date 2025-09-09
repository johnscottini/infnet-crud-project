package com.spring.crud.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer quantidadeEstoque;

    @Column(length = 200)
    private String descricao;
}
