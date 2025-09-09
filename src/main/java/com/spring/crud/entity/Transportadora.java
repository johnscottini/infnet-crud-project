package com.spring.crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "transportadoras")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transportadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 120)
    private String contato;

    @Column(length = 200)
    private String endereco;

    @Column(name = "valor_base_frete")
    private BigDecimal valorBaseFrete;
}