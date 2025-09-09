package com.spring.crud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FornecedorRequestDTO {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @NotBlank(message = "O Contato é obrigatório")
    private String contato;
    @NotBlank(message = "O endereço é obrigatório")
    private String endereco;
    @Email(message = "O email deve ser válido")
    private String email;
}
