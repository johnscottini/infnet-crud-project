package com.spring.crud.dto;

import com.spring.crud.entity.enums.Departamento;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncionarioDTO {
    private Long id;
    private String nome;
    private String cargo;
    private Double salario;
    private Departamento departamento;
}
