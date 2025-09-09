package com.spring.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoDTO {
    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidadeEstoque;
    private String descricao;
}
