package com.spring.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportadoraDTO {
    private Long id;
    private String nome;
    private String contato;
    private String endereco;
    private BigDecimal valorBaseFrete;
}
