package com.spring.crud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequestDTO {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @Size(min = 11, max = 11, message = "O CPF deve ter exatamente 11 dígitos")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas valores numéricos")
    private String cpf;
    private String endereco;
    @Email(message = "O Email deve ser válido")
    private String email;
}