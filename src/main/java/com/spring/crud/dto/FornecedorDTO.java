package com.spring.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FornecedorDTO {
    private Long id;
    private String nome;
    private String contato;
    private String endereco;
    private String email;
}
