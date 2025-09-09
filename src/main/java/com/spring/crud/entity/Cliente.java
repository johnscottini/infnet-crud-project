package com.spring.crud.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(length = 200)
    private String endereco;

    @Column(length = 100)
    private String email;
}
