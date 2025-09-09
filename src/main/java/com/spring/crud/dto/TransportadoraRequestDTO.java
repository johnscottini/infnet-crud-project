package com.spring.crud.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportadoraRequestDTO {
    @NotNull(message = "O nome da transportadora é obrigatório")
    private String nome;
    @NotNull(message = "O contato da transportadora é obrigatório")
    private String contato;
    private String endereco;
    @NotNull(message = "O valor base do frete da transportadora é obrigatório")
    @Min(value = 0, message = "O valor mínimo de frete base deve ser R$ 0,00")
    private BigDecimal valorBaseFrete;
}
