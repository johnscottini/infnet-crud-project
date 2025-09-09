package com.spring.crud.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fornecedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 120)
    private String contato;

    @Column(length = 200)
    private String endereco;

    @Column(length = 100)
    private String email;
}
