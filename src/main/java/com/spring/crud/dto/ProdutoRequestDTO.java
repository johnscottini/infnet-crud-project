package com.spring.crud.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoRequestDTO {
    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;
    @Min(value = 0, message = "O preço deve ser maior ou igual a zero")
    @Max(value = 999999, message = "O preço deve ser menor ou igual a R$ 999.999,00")
    private Double preco;
    @Min(value = 0, message = "A quantidade deve ser maior ou igual a zero")
    private Integer quantidadeEstoque;
    private String descricao;
}