package com.spring.crud.entity;

import com.spring.crud.entity.enums.Departamento;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "funcionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 50)
    private String cargo;

    @Column(nullable = false)
    private Double salario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Departamento departamento;
}
